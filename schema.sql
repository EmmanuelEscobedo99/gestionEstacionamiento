-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS `estacionamiento`;
USE `estacionamiento`;

-- 1. Tabla Usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `code_usuario` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) DEFAULT NULL,
  `apellido` VARCHAR(255) DEFAULT NULL,
  `email` VARCHAR(255) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  `telefono` VARCHAR(255) DEFAULT NULL,
  `rol` VARCHAR(50) DEFAULT NULL,
  `fecha_registro` DATETIME(6) DEFAULT NULL,
  PRIMARY KEY (`code_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. Tabla Estacionamiento
CREATE TABLE IF NOT EXISTS `estacionamiento` (
  `code_estacionamiento` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) DEFAULT NULL,
  `direccion` VARCHAR(255) DEFAULT NULL,
  `ciudad` VARCHAR(255) DEFAULT NULL,
  `capacidad_total` INT DEFAULT NULL,
  `tarifa_hora` DECIMAL(38,2) DEFAULT NULL,
  `activo` BIT(1) NOT NULL,
  PRIMARY KEY (`code_estacionamiento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. Tabla Espacio
CREATE TABLE IF NOT EXISTS `espacio` (
  `code_espacio` BIGINT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(255) DEFAULT NULL,
  `tipo` VARCHAR(50) DEFAULT NULL,
  `disponible` BIT(1) NOT NULL,
  `estacionamiento_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`code_espacio`),
  CONSTRAINT `fk_espacio_estacionamiento` FOREIGN KEY (`estacionamiento_id`) REFERENCES `estacionamiento` (`code_estacionamiento`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. Tabla Vehiculo
CREATE TABLE IF NOT EXISTS `vehiculo` (
  `code_vehiculo` BIGINT NOT NULL AUTO_INCREMENT,
  `placas` VARCHAR(255) DEFAULT NULL,
  `marca` VARCHAR(255) DEFAULT NULL,
  `modelo` VARCHAR(255) DEFAULT NULL,
  `color` VARCHAR(255) DEFAULT NULL,
  `tipo` VARCHAR(255) DEFAULT NULL,
  `usuario_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`code_vehiculo`),
  CONSTRAINT `fk_vehiculo_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`code_usuario`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. Tabla EntradaSalida
CREATE TABLE IF NOT EXISTS `entrada_salida` (
  `code_entrada_salida` BIGINT NOT NULL AUTO_INCREMENT,
  `fecha_entrada` DATETIME(6) DEFAULT NULL,
  `fecha_salida` DATETIME(6) DEFAULT NULL,
  `horas_consumidas` DECIMAL(38,2) DEFAULT NULL,
  `total_pagar` DECIMAL(38,2) DEFAULT NULL,
  `estado` VARCHAR(50) DEFAULT NULL,
  `vehiculo_id` BIGINT DEFAULT NULL,
  `espacio_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`code_entrada_salida`),
  CONSTRAINT `fk_entrada_vehiculo` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`code_vehiculo`) ON DELETE SET NULL,
  CONSTRAINT `fk_entrada_espacio` FOREIGN KEY (`espacio_id`) REFERENCES `espacio` (`code_espacio`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6. Tabla Pago
CREATE TABLE IF NOT EXISTS `pago` (
  `code_pago` BIGINT NOT NULL AUTO_INCREMENT,
  `monto` DECIMAL(38,2) DEFAULT NULL,
  `fecha_pago` DATETIME(6) DEFAULT NULL,
  `metodo_pago` VARCHAR(50) DEFAULT NULL,
  `entrada_salida_id` BIGINT DEFAULT NULL,
  PRIMARY KEY (`code_pago`),
  UNIQUE KEY `uk_pago_entrada_salida` (`entrada_salida_id`),
  CONSTRAINT `fk_pago_entrada_salida` FOREIGN KEY (`entrada_salida_id`) REFERENCES `entrada_salida` (`code_entrada_salida`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
