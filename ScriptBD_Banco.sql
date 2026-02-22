DROP DATABASE IF EXISTS banco;
CREATE DATABASE banco;
USE banco;

-- Tablas
CREATE TABLE direcciones_clientes (
    id_direccion INT AUTO_INCREMENT PRIMARY KEY,
    calle VARCHAR(50) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    colonia VARCHAR(50) NOT NULL,
    codigo_postal VARCHAR(10) NOT NULL,
    ciudad VARCHAR(50) NOT NULL
);

CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(50) NOT NULL,
    apellido_paterno VARCHAR(50) NOT NULL,
    apellido_materno VARCHAR(50) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    edad INT, -- Se calcula con Trigger
    correo VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    id_direccion INT NOT NULL,
    FOREIGN KEY (id_direccion) REFERENCES direcciones_clientes(id_direccion)
);

CREATE TABLE cuentas (
    numero_cuenta VARCHAR(12) PRIMARY KEY,
    fecha_apertura DATE NOT NULL,
    saldo DECIMAL(10,2) NOT NULL CHECK (saldo >= 0),
    estado ENUM('activa', 'bloqueada', 'cerrada') NOT NULL,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

CREATE TABLE operaciones (
    folio_operacion INT AUTO_INCREMENT PRIMARY KEY,
    tipo_operacion ENUM('transferencia', 'retiro sin cuenta', 'alta de cuenta') NOT NULL,
    fecha_operacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    descripcion VARCHAR(100),
    numero_cuenta VARCHAR(12) NOT NULL,
    FOREIGN KEY (numero_cuenta) REFERENCES cuentas(numero_cuenta)
) AUTO_INCREMENT = 10000000;

CREATE TABLE transferencias (
	folio_operacion INT PRIMARY KEY,
    cuenta_destino VARCHAR(12) NOT NULL,
    monto DECIMAL(10,2) NOT NULL CHECK (monto > 0),
    fecha_transferencia DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_operacion) REFERENCES operaciones(folio_operacion),
    FOREIGN KEY (cuenta_destino) REFERENCES cuentas(numero_cuenta)
);

CREATE TABLE retiros_sin_cuenta (
    folio_operacion INT PRIMARY KEY,
    contrasena_retiro VARCHAR(8) NOT NULL,
    monto DECIMAL(10,2) NOT NULL CHECK (monto > 0),
    fecha_retiro DATETIME NOT NULL,
    estado_retiro ENUM('pendiente', 'cobrado', 'cancelado') NOT NULL,
    FOREIGN KEY (folio_operacion) REFERENCES operaciones(folio_operacion)
);

-- Triggers
-- Calcular Edad automáticamente al insertar cliente
DELIMITER $$
CREATE TRIGGER calcular_edad_insert
BEFORE INSERT ON clientes
FOR EACH ROW
BEGIN
    SET NEW.edad = TIMESTAMPDIFF(YEAR, NEW.fecha_nacimiento, CURDATE());
END$$
DELIMITER ;

-- Stored procedure realizar transferencia
DELIMITER $$
CREATE PROCEDURE realizar_transferencia (
	IN p_cuenta_origen VARCHAR(12),
    IN p_cuenta_destino VARCHAR(12),
    IN p_monto DECIMAL(10,2),
    OUT p_folio_generado INT
)
BEGIN
	
    -- Declaramos las variables para uso local
    DECLARE v_id_op INT;
    DECLARE v_saldo_actual DECIMAL(10,2);
    
    -- En caso de fallo hacemos un rollback y seteamos el mensage de error
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
	 	ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "ERROR: No fue posible completar la transferencia.";
	END;
    
    -- Iniciamos la transaccion
    START TRANSACTION;
    
    -- Guardamos el saldo actual de la cuenta origen en la variable creada
    -- Ademas secuentramos la fila de la cuenta origen para que ningun otro proceso pueda modificarla hasta que esta transacción termine.
    SELECT saldo INTO v_saldo_actual FROM cuentas WHERE numero_cuenta = p_cuenta_origen FOR UPDATE;
    
    -- Procedemos solo si la cuenta origen tiene el saldo suficiente para transferir.
    IF v_saldo_actual >= p_monto THEN
		
        -- Restamos el monto a la cuenta origen. 
		UPDATE cuentas SET saldo = saldo - p_monto WHERE numero_cuenta = p_cuenta_origen;
        
        -- Incrementamos el monto a la cuenta destino.
        UPDATE cuentas SET saldo = saldo + p_monto WHERE numero_cuenta = p_cuenta_destino;
        
        -- Creamos el registro de la operacion 
        -- NOW proporciona fecha y la hora
        INSERT INTO operaciones (tipo_operacion, fecha_operacion, descripcion, numero_cuenta)
        VALUES ('transferencia', NOW(), CONCAT("Transferencia enviada a la cuenta: ", p_cuenta_destino), p_cuenta_origen);
        
        SET v_id_op = LAST_INSERT_ID();
        
        -- Creamos el registro de la transferencia
        INSERT INTO transferencias (folio_operacion, cuenta_destino, monto, fecha_transferencia)
        VALUES (v_id_op, p_cuenta_destino, p_monto, NOW());
        
	    -- Ejecutamos la sentencia completa
        COMMIT;
        
        -- Asignamos el folio al parametro de salida
        SET p_folio_generado = v_id_op;
        
	ELSE
		SIGNAL SQLSTATE '45000';
		ROLLBACK;
    END IF;
END$$
DELIMITER ;

