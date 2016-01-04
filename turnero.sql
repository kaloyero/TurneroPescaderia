-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1:3308
-- Tiempo de generación: 04-01-2016 a las 11:54:07
-- Versión del servidor: 5.5.32
-- Versión de PHP: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `turnero`
--
CREATE DATABASE IF NOT EXISTS `turnero` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `turnero`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE IF NOT EXISTS `configuracion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tiempopngjpg` int(11) NOT NULL,
  `tiempogif` int(11) NOT NULL,
  `directorio` varchar(100) NOT NULL,
  `feed` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `tiempopngjpg`, `tiempogif`, `directorio`, `feed`) VALUES
(1, 22, 22, 'C:\\ProyectosAle\\KaizenPescaderia\\SAT-Mostrador\\WebContent\\archivos', 'cdn01.ib.infobae.com/adjuntos/162/rss/Infobae.xml');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controlremoto`
--

CREATE TABLE IF NOT EXISTS `controlremoto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `controlremoto`
--

INSERT INTO `controlremoto` (`id`, `codigo`) VALUES
(1, '34'),
(2, '12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controlremoto_sector`
--

CREATE TABLE IF NOT EXISTS `controlremoto_sector` (
  `idControl` int(11) NOT NULL,
  `idSector` int(11) NOT NULL,
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `controlremoto_sector`
--

INSERT INTO `controlremoto_sector` (`idControl`, `idSector`, `Id`) VALUES
(1, 2, 2),
(2, 3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE IF NOT EXISTS `perfil` (
  `id_perfil` int(11) NOT NULL AUTO_INCREMENT,
  `perfil` int(1) NOT NULL,
  `nom_perfil` varchar(15) NOT NULL,
  PRIMARY KEY (`id_perfil`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `perfil`
--

INSERT INTO `perfil` (`id_perfil`, `perfil`, `nom_perfil`) VALUES
(1, 2, 'Administrador'),
(2, 2, 'Operador'),
(3, 3, 'Estadistica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puesto`
--

CREATE TABLE IF NOT EXISTS `puesto` (
  `id_puesto` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(15) NOT NULL,
  `Habilitado` varchar(2) NOT NULL,
  `nro_puesto` int(2) NOT NULL,
  PRIMARY KEY (`id_puesto`),
  UNIQUE KEY `UK_ip` (`ip`),
  UNIQUE KEY `UK_nro_puesto` (`nro_puesto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Disparadores `puesto`
--
DROP TRIGGER IF EXISTS `SAT_Tpuesto_delete`;
DELIMITER //
CREATE TRIGGER `SAT_Tpuesto_delete` AFTER DELETE ON `puesto`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Hpuesto
    SET fecha_audit = NOW(),
		accion_usuario = 'DELETE',
		id_puesto = OLD.id_puesto,
		new_ip = OLD.ip,
		new_Habilitado = OLD.Habilitado,
		new_nro_puesto = OLD.nro_puesto;
    
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `SAT_Tpuesto_insert`;
DELIMITER //
CREATE TRIGGER `SAT_Tpuesto_insert` AFTER INSERT ON `puesto`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Hpuesto
    SET fecha_audit = NOW(),
		accion_usuario = 'INSERT',
		id_puesto = NEW.id_puesto,
		new_ip = NEW.ip,
		new_Habilitado = NEW.Habilitado,
		new_nro_puesto = NEW.nro_puesto;
    
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `SAT_Tpuesto_update`;
DELIMITER //
CREATE TRIGGER `SAT_Tpuesto_update` AFTER UPDATE ON `puesto`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Hpuesto
    SET fecha_audit = NOW(),
        accion_usuario = 'UPDATE',
	    id_puesto = NEW.id_puesto,
		new_ip = NEW.ip,
		new_Habilitado = NEW.Habilitado,
		new_nro_puesto = NEW.nro_puesto;
    
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sat`
--

CREATE TABLE IF NOT EXISTS `sat` (
  `id_sat` int(11) NOT NULL AUTO_INCREMENT,
  `cod_sucursal` varchar(5) NOT NULL DEFAULT '00001',
  `nom_sucursal` varchar(30) NOT NULL DEFAULT 'Centro',
  `versionSAT` varchar(8) NOT NULL DEFAULT '1.0',
  `fech_act` datetime NOT NULL,
  `IP` varchar(20) NOT NULL,
  `Totem` int(4) unsigned NOT NULL,
  PRIMARY KEY (`id_sat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Disparadores `sat`
--
DROP TRIGGER IF EXISTS `SAT_Tsat_delete`;
DELIMITER //
CREATE TRIGGER `SAT_Tsat_delete` AFTER DELETE ON `sat`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Hsat
    SET fecha_audit = NOW(),
		accion_usuario = 'DELETE',
		id_sat = OLD.id_sat,
		new_cod_sucursal = OLD.cod_sucursal,
		new_nom_sucursal = OLD.nom_sucursal,
		new_versionSAT	= OLD.versionSAT,
		new_fech_act = OLD.fech_act,
		new_IP = OLD.IP,
		new_Totem = OLD.Totem;
    
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `SAT_Tsat_insert`;
DELIMITER //
CREATE TRIGGER `SAT_Tsat_insert` AFTER INSERT ON `sat`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Hsat
    SET fecha_audit = NOW(),
		accion_usuario = 'INSERT',
		id_sat = new.id_sat,
		new_cod_sucursal = new.cod_sucursal,
		new_nom_sucursal = new.nom_sucursal,
		new_versionSAT	= new.versionSAT,
		new_fech_act = new.fech_act,
		new_IP = new.IP,
		new_Totem = new.Totem;
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `SAT_Tsat_update`;
DELIMITER //
CREATE TRIGGER `SAT_Tsat_update` AFTER UPDATE ON `sat`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Hsat
    SET fecha_audit = NOW(),
		accion_usuario = 'UPDATE',
		id_sat = new.id_sat,
		new_cod_sucursal = NEW.cod_sucursal,
		new_nom_sucursal = NEW.nom_sucursal,
		new_versionSAT	= NEW.versionSAT,
		new_fech_act = NEW.fech_act,
		new_IP = NEW.IP,
		new_Totem = NEW.Totem;
    
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sat_hpuesto`
--

CREATE TABLE IF NOT EXISTS `sat_hpuesto` (
  `id_Hpuesto` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_audit` datetime NOT NULL,
  `accion_usuario` varchar(6) NOT NULL,
  `id_puesto` int(11) NOT NULL,
  `new_ip` varchar(15) DEFAULT NULL,
  `new_Habilitado` varchar(2) DEFAULT NULL,
  `new_nro_puesto` int(2) DEFAULT NULL,
  PRIMARY KEY (`id_Hpuesto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sat_hsat`
--

CREATE TABLE IF NOT EXISTS `sat_hsat` (
  `id_Hsat` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_audit` datetime NOT NULL,
  `accion_usuario` varchar(6) NOT NULL,
  `id_sat` int(11) NOT NULL,
  `new_cod_sucursal` varchar(5) DEFAULT NULL,
  `new_nom_sucursal` varchar(30) DEFAULT NULL,
  `new_versionSAT` varchar(8) DEFAULT NULL,
  `new_fech_act` datetime DEFAULT NULL,
  `new_IP` varchar(20) DEFAULT NULL,
  `new_Totem` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_Hsat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sat_hsector`
--

CREATE TABLE IF NOT EXISTS `sat_hsector` (
  `id_Hsector` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_audit` datetime NOT NULL,
  `accion_usuario` varchar(6) NOT NULL,
  `id_sector` int(11) NOT NULL,
  `new_cod_sector` varchar(1) DEFAULT NULL,
  `new_nom_sector` varchar(15) DEFAULT NULL,
  `new_habilitado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id_Hsector`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Volcado de datos para la tabla `sat_hsector`
--

INSERT INTO `sat_hsector` (`id_Hsector`, `fecha_audit`, `accion_usuario`, `id_sector`, `new_cod_sector`, `new_nom_sector`, `new_habilitado`) VALUES
(1, '2015-11-19 12:03:14', 'INSERT', 1, 'A', 'Sector1', 'SI'),
(2, '2015-11-19 12:03:14', 'INSERT', 2, 'B', 'Sector2', 'SI'),
(3, '2015-11-19 17:04:41', 'UPDATE', 1, 'C', 'Sector1', 'SI'),
(4, '2015-11-23 12:10:19', 'INSERT', 3, 'X', 'Ale', 'SI'),
(5, '2015-11-24 16:45:15', 'INSERT', 5, 'N', 'Alex', 'SI'),
(6, '2015-11-25 17:43:45', 'UPDATE', 2, 'B', 'Sector2', 'SI'),
(7, '2015-11-25 17:56:48', 'DELETE', 1, 'C', 'Sector1', 'SI'),
(8, '2015-11-25 17:57:10', 'INSERT', 6, 'J', 'j', 'SI'),
(9, '2015-11-25 17:57:16', 'DELETE', 6, 'J', 'j', 'SI'),
(10, '2015-12-20 18:56:10', 'DELETE', 5, 'N', 'Alex', 'SI'),
(11, '2015-12-20 18:56:15', 'UPDATE', 2, 'B', 'Embutidos', 'SI'),
(12, '2015-12-20 18:56:20', 'UPDATE', 3, 'X', 'Frios', 'SI'),
(13, '2015-12-20 18:56:25', 'UPDATE', 2, 'E', 'Embutidos', 'SI'),
(14, '2015-12-20 18:56:29', 'UPDATE', 3, 'C', 'Frios', 'SI');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sat_husuario`
--

CREATE TABLE IF NOT EXISTS `sat_husuario` (
  `id_Husuario` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_audit` datetime DEFAULT NULL,
  `accion_usuario` varchar(6) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `new_nom_usuario` varchar(15) DEFAULT NULL,
  `new_password` varchar(15) DEFAULT NULL,
  `new_id_perfil` int(1) DEFAULT NULL,
  `new_Habilitado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id_Husuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `sat_husuario`
--

INSERT INTO `sat_husuario` (`id_Husuario`, `fecha_audit`, `accion_usuario`, `id_usuario`, `new_nom_usuario`, `new_password`, `new_id_perfil`, `new_Habilitado`) VALUES
(1, '2015-11-19 11:58:09', 'INSERT', 1, 'admin', '1234', 1, 'SI'),
(2, '2015-11-19 11:58:51', 'UPDATE', 1, 'admin', '1234', 1, 'SI');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sat_husuario_sector`
--

CREATE TABLE IF NOT EXISTS `sat_husuario_sector` (
  `id_Husuario_sector` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_audit` datetime NOT NULL,
  `accion_usuario` varchar(6) NOT NULL,
  `Id` int(11) NOT NULL,
  `new_ID_USUARIO` int(11) DEFAULT NULL,
  `new_ID_SECTOR` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_Husuario_sector`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sector`
--

CREATE TABLE IF NOT EXISTS `sector` (
  `id_sector` int(11) NOT NULL AUTO_INCREMENT,
  `cod_sector` varchar(1) NOT NULL,
  `nom_sector` varchar(15) NOT NULL,
  `habilitado` varchar(2) NOT NULL,
  PRIMARY KEY (`id_sector`),
  UNIQUE KEY `UK_cod_sector` (`cod_sector`),
  UNIQUE KEY `UK_nom_sector` (`nom_sector`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `sector`
--

INSERT INTO `sector` (`id_sector`, `cod_sector`, `nom_sector`, `habilitado`) VALUES
(2, 'E', 'Embutidos', 'SI'),
(3, 'C', 'Frios', 'SI');

--
-- Disparadores `sector`
--
DROP TRIGGER IF EXISTS `SAT_Tsector_delete`;
DELIMITER //
CREATE TRIGGER `SAT_Tsector_delete` AFTER DELETE ON `sector`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Hsector
    SET fecha_audit = NOW(),
		accion_usuario = 'DELETE',
		id_sector = OLD.id_sector,
		new_cod_sector = OLD.cod_sector,
		new_nom_sector = OLD.nom_sector,
		new_habilitado = OLD.habilitado;

    
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `SAT_Tsector_insert`;
DELIMITER //
CREATE TRIGGER `SAT_Tsector_insert` AFTER INSERT ON `sector`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Hsector
    SET fecha_audit = NOW(),
		accion_usuario = 'INSERT',
		id_sector = NEW.id_sector,
		new_cod_sector = NEW.cod_sector,
		new_nom_sector = NEW.nom_sector,
		new_habilitado = NEW.habilitado;
    
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `SAT_Tsector_update`;
DELIMITER //
CREATE TRIGGER `SAT_Tsector_update` AFTER UPDATE ON `sector`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Hsector
    SET fecha_audit = NOW(),
		accion_usuario = 'UPDATE',
		id_sector = NEW.id_sector,
		new_cod_sector = NEW.cod_sector,
		new_nom_sector = NEW.nom_sector,
		new_habilitado = NEW.habilitado;
    
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turno`
--

CREATE TABLE IF NOT EXISTS `turno` (
  `id_turno` int(11) NOT NULL AUTO_INCREMENT,
  `id_sector` int(1) NOT NULL,
  `numero_turno` int(2) NOT NULL,
  `id_cod_sector` varchar(1) NOT NULL,
  `idControl` int(11) NOT NULL DEFAULT '0',
  `fecha_ticket` datetime NOT NULL,
  `fecha_atencion` datetime NOT NULL,
  `fecha_fin` datetime NOT NULL,
  `id_usuario` int(5) NOT NULL,
  `id_puesto` int(2) NOT NULL,
  `atendido` varchar(2) NOT NULL,
  `llamado` varchar(2) NOT NULL,
  PRIMARY KEY (`id_turno`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`id_turno`, `id_sector`, `numero_turno`, `id_cod_sector`, `idControl`, `fecha_ticket`, `fecha_atencion`, `fecha_fin`, `id_usuario`, `id_puesto`, `atendido`, `llamado`) VALUES
(1, 3, 1, 'X', 6, '2015-11-29 19:07:58', '2015-11-29 21:34:07', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(2, 3, 1, 'X', 6, '2015-11-29 20:35:02', '2015-11-29 21:20:51', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(3, 2, 1, 'B', 0, '2015-11-29 20:35:50', '2015-11-29 00:00:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(4, 3, 2, 'X', 6, '2015-11-29 21:35:56', '2015-11-29 21:36:19', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(5, 3, 3, 'X', 6, '2015-11-29 21:40:21', '2015-11-29 21:40:33', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(6, 3, 4, 'X', 6, '2015-11-29 21:40:47', '2015-11-29 21:40:57', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(7, 2, 1, 'B', 0, '2015-12-03 06:30:22', '2015-11-29 00:00:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(8, 2, 1, 'E', 0, '2015-12-20 19:04:17', '2015-11-29 00:00:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(9, 3, 1, 'C', 0, '2015-12-20 19:04:35', '2015-12-20 00:00:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(10, 3, 2, 'C', 0, '2015-12-20 19:04:45', '2015-12-20 00:00:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(11, 3, 3, 'C', 0, '2015-12-20 19:04:56', '2015-12-20 00:00:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(12, 3, 4, 'C', 0, '2015-12-20 19:05:06', '2015-12-20 00:00:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(13, 3, 5, 'C', 0, '2015-12-20 19:05:16', '2015-12-20 00:00:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(14, 3, 6, 'C', 0, '2015-12-20 19:05:37', '2015-12-20 21:40:57', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(15, 3, 7, 'C', 0, '2015-12-20 19:06:13', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'NO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nom_usuario` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `id_perfil` int(1) NOT NULL,
  `fecha` datetime NOT NULL,
  `Habilitado` varchar(2) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_nom_usuario` (`nom_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nom_usuario`, `password`, `id_perfil`, `fecha`, `Habilitado`) VALUES
(1, 'admin', '1234', 1, '2015-11-19 00:00:00', 'SI');

--
-- Disparadores `usuario`
--
DROP TRIGGER IF EXISTS `SAT_Tusuario_delete`;
DELIMITER //
CREATE TRIGGER `SAT_Tusuario_delete` AFTER DELETE ON `usuario`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Husuario
    SET fecha_audit = NOW(),
		accion_usuario = 'DELETE',
		id_usuario = OLD.id_usuario,
		new_nom_usuario = OLD.nom_usuario,
		new_password = OLD.password,
		new_id_perfil = OLD.id_perfil,
		new_Habilitado = OLD.Habilitado;
    
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `SAT_Tusuario_insert`;
DELIMITER //
CREATE TRIGGER `SAT_Tusuario_insert` AFTER INSERT ON `usuario`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Husuario
    SET fecha_audit = NOW(),
		accion_usuario = 'INSERT',
		id_usuario = NEW.id_usuario,
		new_nom_usuario = NEW.nom_usuario,
		new_password = NEW.password,
		new_id_perfil = NEW.id_perfil,
		new_Habilitado = NEW.Habilitado;
    
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `SAT_Tusuario_update`;
DELIMITER //
CREATE TRIGGER `SAT_Tusuario_update` AFTER UPDATE ON `usuario`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Husuario
    SET fecha_audit = NOW(),
		accion_usuario = 'UPDATE',
		id_usuario = NEW.id_usuario,
		new_nom_usuario = NEW.nom_usuario,
		new_password = NEW.password,
		new_id_perfil = NEW.id_perfil,
		new_Habilitado = NEW.Habilitado;
    
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_sector`
--

CREATE TABLE IF NOT EXISTS `usuario_sector` (
  `ID_USUARIO` int(11) NOT NULL,
  `ID_SECTOR` int(11) NOT NULL,
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Usuario_sector_UK` (`ID_USUARIO`,`ID_SECTOR`),
  KEY `usuariosector_sector` (`ID_SECTOR`),
  KEY `usuariosector_usuario` (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Disparadores `usuario_sector`
--
DROP TRIGGER IF EXISTS `SAT_Tusuario_sector_delete`;
DELIMITER //
CREATE TRIGGER `SAT_Tusuario_sector_delete` AFTER DELETE ON `usuario_sector`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Husuario_sector
    SET fecha_audit = NOW(),
		accion_usuario = 'DELETE',
		id = OLD.id,
		new_ID_USUARIO = OLD.ID_USUARIO,
		new_ID_SECTOR = OLD.ID_SECTOR;
    
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `SAT_Tusuario_sector_insert`;
DELIMITER //
CREATE TRIGGER `SAT_Tusuario_sector_insert` AFTER INSERT ON `usuario_sector`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Husuario_sector
    SET fecha_audit = NOW(),
		accion_usuario = 'INSERT',
		id = NEW.id,
		new_ID_USUARIO = NEW.ID_USUARIO,
		new_ID_SECTOR = NEW.ID_SECTOR;
    
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `SAT_Tusuario_sector_update`;
DELIMITER //
CREATE TRIGGER `SAT_Tusuario_sector_update` AFTER UPDATE ON `usuario_sector`
 FOR EACH ROW BEGIN
    
    INSERT INTO SAT_Husuario_sector
    SET fecha_audit = NOW(),
		accion_usuario = 'UPDATE',
		id = NEW.id,
		new_ID_USUARIO = NEW.ID_USUARIO,
		new_ID_SECTOR = NEW.ID_SECTOR;
    
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vista_turnos_estadistica`
--
CREATE TABLE IF NOT EXISTS `vista_turnos_estadistica` (
`id_sat` int(11)
,`cod_sucursal` varchar(5)
,`nom_sucursal` varchar(30)
,`id_turno` int(11)
,`id_cod_sector` varchar(1)
,`nom_sector` varchar(15)
,`numero_turno` int(2)
,`fecha_ticket` datetime
,`llamado` varchar(2)
,`fecha_atencion` datetime
,`nro_puesto` int(2)
,`nom_usuario` varchar(15)
,`atendido` varchar(2)
,`fecha_fin` datetime
);
-- --------------------------------------------------------

--
-- Estructura para la vista `vista_turnos_estadistica`
--
DROP TABLE IF EXISTS `vista_turnos_estadistica`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_turnos_estadistica` AS select `id_sat` AS `id_sat`,`cod_sucursal` AS `cod_sucursal`,`nom_sucursal` AS `nom_sucursal`,`turno`.`id_turno` AS `id_turno`,`turno`.`id_cod_sector` AS `id_cod_sector`,`sector`.`nom_sector` AS `nom_sector`,`turno`.`numero_turno` AS `numero_turno`,`turno`.`fecha_ticket` AS `fecha_ticket`,`turno`.`llamado` AS `llamado`,`turno`.`fecha_atencion` AS `fecha_atencion`,`puesto`.`nro_puesto` AS `nro_puesto`,`usuario`.`nom_usuario` AS `nom_usuario`,`turno`.`atendido` AS `atendido`,`turno`.`fecha_fin` AS `fecha_fin` from ((`sat` join ((`turno` join `sector` on((`turno`.`id_sector` = `sector`.`id_sector`))) left join `usuario` on((`turno`.`id_usuario` = `usuario`.`id_usuario`)))) left join `puesto` on((`turno`.`id_puesto` = `puesto`.`id_puesto`))) order by `id_sat`,`turno`.`id_turno`;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `usuario_sector`
--
ALTER TABLE `usuario_sector`
  ADD CONSTRAINT `usuario_sector_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `usuario_sector_ibfk_2` FOREIGN KEY (`ID_SECTOR`) REFERENCES `sector` (`id_sector`),
  ADD CONSTRAINT `usuario_sector_ibfk_3` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuario` (`id_usuario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
