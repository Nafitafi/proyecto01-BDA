-- Datitos prueba
-- Direcciones
INSERT INTO direcciones_clientes (calle, numero, colonia, codigo_postal, ciudad) VALUES
('Aguila', '502', 'Nueva Palmira', '85190', 'Ciudad Obregón'),
('Calle Guerrero', '456', 'Torres De Paris', '85160', 'Ciudad Obregón'),
('Av. Miguel Alemán', '789', 'Villa Bonita', '85090', 'Ciudad Obregón');

-- Clientes (Contraseñas en texto plano POR AHORA)
INSERT INTO clientes (nombres, apellido_paterno, apellido_materno, fecha_nacimiento, correo, contrasena, id_direccion) VALUES
('Emily', 'Lara', 'García', '2006-04-15', 'emily@gmail.com', '123456', 1),
('Oliver', 'Robles', 'Cota', '2006-05-12', 'oliver@gmail.com', '123456', 2),
('Nahomi', 'Figueroa', 'Briones', '2006-07-14', 'nahomi@gmail.com', '123456', 3);

INSERT INTO cuentas (numero_cuenta, fecha_apertura, saldo, estado, id_cliente) VALUES
('123-456-7890', '2023-01-10', 5000.00, 'activa', 1),
('234-567-8901', '2023-02-15', 300.50, 'activa', 2),
('345-678-9012', '2023-03-20', 10000.00, 'activa', 3);


-- Busquedas de prueba
SELECT numero_cuenta, saldo, estado, fecha_apertura, id_cliente
FROM cuentas WHERE numero_cuenta = '234-567-8901';

SELECT numero_cuenta, saldo, estado, fecha_apertura, id_cliente
FROM cuentas;

SELECT *
FROM operaciones;

SELECT *
FROM clientes;

SELECT *
FROM retiros_sin_cuenta;


-- Prueba de los stored procedured
-- realizar_transferencia
SET @resultado_operacion = 0;
CALL realizar_transferencia("123-456-7890", "234-567-8901", 300, @resultado_operacion);
SELECT * FROM cuentas;
SELECT * FROM operaciones;
SELECT * FROM transferencias;

-- registrar_retiro_sin_cuenta
SET @resultado_operacion = 0;
CALL registrar_retiro_sin_cuenta('234-567-8901', '12345678', 100,  @resultado_operacion);
SELECT * FROM cuentas;
SELECT * FROM operaciones;
SELECT * FROM retiros_sin_cuenta;

-- realizar_retiro_sin_cuenta
SET @resultado_operacion = 0;
CALL realizar_retiro_sin_cuenta(10000000, 100, @resultado_operacion);
SELECT * FROM cuentas;
SELECT * FROM operaciones;
SELECT * FROM retiros_sin_cuenta;

SELECT VERSION();

