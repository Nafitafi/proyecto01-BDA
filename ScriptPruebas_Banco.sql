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

-- Transferenecnias de prueba
UPDATE cuentas
SET saldo = saldo - 10
WHERE numero_cuenta = '123-456-7890';

UPDATE cuentas
SET saldo = saldo + 10
WHERE numero_cuenta = '345-678-9012';

INSERT INTO operaciones (tipo_operacion, monto, descripcion, numero_cuenta)
VALUES ("transferencia", 10, "Operacion exitosa",'234-567-8901');

-- Busquedas de prueba
SELECT numero_cuenta, saldo, estado, fecha_apertura, id_cliente
FROM cuentas WHERE numero_cuenta = '234-567-8901';

SELECT numero_cuenta, saldo, estado, fecha_apertura, id_cliente
FROM cuentas;

SELECT *
FROM operaciones;

-- Prueba del stored procedured
SET @resultado_operacion = 0;
CALL realizar_transferencia("123-456-7890", "234-567-8901", 300, @resultado_operacion);
SELECT * FROM cuentas;
SELECT * FROM operaciones;
SELECT * FROM transferencias;

SELECT saldo FROM cuentas WHERE numero_cuenta = "123-456-7890";

SELECT VERSION();

