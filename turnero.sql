-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-02-2016 a las 23:14:52
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `turnero`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE IF NOT EXISTS `configuracion` (
`id` int(11) NOT NULL,
  `tiempopngjpg` int(11) NOT NULL,
  `tiempogif` int(11) NOT NULL,
  `directorio` varchar(100) NOT NULL,
  `feed` varchar(100) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `tiempopngjpg`, `tiempogif`, `directorio`, `feed`) VALUES
(1, 22, 22, 'C:\\Users\\Ale\\Desktop\\Monelecsa-Enconstrucciòn\\soonmf2\\images', 'cdn01.ib.infobae.com/adjuntos/162/rss/Infobae.xml');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controlremoto`
--

CREATE TABLE IF NOT EXISTS `controlremoto` (
`id` int(11) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `anterior` int(2) NOT NULL,
  `siguiente` int(2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `controlremoto`
--

INSERT INTO `controlremoto` (`id`, `codigo`, `anterior`, `siguiente`) VALUES
(1, '34', 1, 2),
(2, '12', 3, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controlremoto_sector`
--

CREATE TABLE IF NOT EXISTS `controlremoto_sector` (
  `idControl` int(11) NOT NULL,
  `idSector` int(11) NOT NULL,
`Id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

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
`id_perfil` int(11) NOT NULL,
  `perfil` int(1) NOT NULL,
  `nom_perfil` varchar(15) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `perfil`
--

INSERT INTO `perfil` (`id_perfil`, `perfil`, `nom_perfil`) VALUES
(1, 2, 'Administrador'),
(2, 2, 'Operador'),
(3, 3, 'Estadistica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sat`
--

CREATE TABLE IF NOT EXISTS `sat` (
`id_sat` int(11) NOT NULL,
  `cod_sucursal` varchar(5) NOT NULL DEFAULT '00001',
  `nom_sucursal` varchar(30) NOT NULL DEFAULT 'Centro',
  `versionSAT` varchar(8) NOT NULL DEFAULT '1.0',
  `fech_act` datetime NOT NULL,
  `IP` varchar(20) NOT NULL,
  `Totem` int(4) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Disparadores `sat`
--
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
-- Estructura de tabla para la tabla `sat_hsat`
--

CREATE TABLE IF NOT EXISTS `sat_hsat` (
`id_Hsat` int(11) NOT NULL,
  `fecha_audit` datetime NOT NULL,
  `accion_usuario` varchar(6) NOT NULL,
  `id_sat` int(11) NOT NULL,
  `new_cod_sucursal` varchar(5) DEFAULT NULL,
  `new_nom_sucursal` varchar(30) DEFAULT NULL,
  `new_versionSAT` varchar(8) DEFAULT NULL,
  `new_fech_act` datetime DEFAULT NULL,
  `new_IP` varchar(20) DEFAULT NULL,
  `new_Totem` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sat_hsector`
--

CREATE TABLE IF NOT EXISTS `sat_hsector` (
`id_Hsector` int(11) NOT NULL,
  `fecha_audit` datetime NOT NULL,
  `accion_usuario` varchar(6) NOT NULL,
  `id_sector` int(11) NOT NULL,
  `new_cod_sector` varchar(1) DEFAULT NULL,
  `new_nom_sector` varchar(15) DEFAULT NULL,
  `new_habilitado` varchar(2) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sat_husuario`
--

CREATE TABLE IF NOT EXISTS `sat_husuario` (
`id_Husuario` int(11) NOT NULL,
  `fecha_audit` datetime DEFAULT NULL,
  `accion_usuario` varchar(6) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `new_nom_usuario` varchar(15) DEFAULT NULL,
  `new_password` varchar(15) DEFAULT NULL,
  `new_id_perfil` int(1) DEFAULT NULL,
  `new_Habilitado` varchar(2) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
`id_Husuario_sector` int(11) NOT NULL,
  `fecha_audit` datetime NOT NULL,
  `accion_usuario` varchar(6) NOT NULL,
  `Id` int(11) NOT NULL,
  `new_ID_USUARIO` int(11) DEFAULT NULL,
  `new_ID_SECTOR` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sector`
--

CREATE TABLE IF NOT EXISTS `sector` (
`id_sector` int(11) NOT NULL,
  `cod_sector` varchar(1) NOT NULL,
  `nom_sector` varchar(15) NOT NULL,
  `habilitado` varchar(2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sector`
--

INSERT INTO `sector` (`id_sector`, `cod_sector`, `nom_sector`, `habilitado`) VALUES
(2, 'E', 'Embutidos', 'SI'),
(3, 'C', 'Frios', 'SI');

--
-- Disparadores `sector`
--
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
`id_turno` int(11) NOT NULL,
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
  `llamado` varchar(2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `turno`
--

INSERT INTO `turno` (`id_turno`, `id_sector`, `numero_turno`, `id_cod_sector`, `idControl`, `fecha_ticket`, `fecha_atencion`, `fecha_fin`, `id_usuario`, `id_puesto`, `atendido`, `llamado`) VALUES
(16, 2, 1, 'E', 1, '2016-02-04 17:54:25', '2016-02-04 18:14:31', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(17, 3, 1, 'C', 2, '2016-02-04 18:21:05', '2016-02-04 19:08:18', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(18, 3, 2, 'C', 0, '2016-02-04 18:21:27', '2016-02-04 19:08:15', '0000-00-00 00:00:00', 0, 0, 'NO', 'NO'),
(19, 2, 2, 'E', 1, '2016-02-04 18:21:35', '2016-02-04 19:01:22', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(20, 2, 3, 'E', 1, '2016-02-04 18:21:42', '2016-02-04 19:01:49', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(21, 2, 4, 'E', 1, '2016-02-04 18:21:49', '2016-02-04 19:01:52', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(22, 3, 3, 'C', 0, '2016-02-04 18:21:57', '2016-02-04 19:08:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'NO'),
(23, 3, 4, 'C', 0, '2016-02-04 18:22:04', '2016-02-04 19:07:56', '0000-00-00 00:00:00', 0, 0, 'NO', 'NO'),
(24, 2, 5, 'E', 1, '2016-02-04 18:29:41', '2016-02-04 19:02:06', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(25, 3, 5, 'C', 0, '2016-02-04 18:29:49', '2016-02-04 19:06:46', '0000-00-00 00:00:00', 0, 0, 'NO', 'NO'),
(26, 2, 6, 'E', 1, '2016-02-04 18:29:57', '2016-02-04 19:06:44', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(27, 2, 7, 'E', 1, '2016-02-04 19:06:10', '2016-02-04 19:07:55', '0000-00-00 00:00:00', 0, 0, 'NO', 'SI'),
(28, 3, 6, 'C', 0, '2016-02-04 19:06:18', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'NO'),
(29, 3, 7, 'C', 0, '2016-02-04 19:06:25', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 0, 0, 'NO', 'NO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
`id_usuario` int(11) NOT NULL,
  `nom_usuario` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `id_perfil` int(1) NOT NULL,
  `fecha` datetime NOT NULL,
  `Habilitado` varchar(2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nom_usuario`, `password`, `id_perfil`, `fecha`, `Habilitado`) VALUES
(1, 'admin', '1234', 1, '2015-11-19 00:00:00', 'SI');

--
-- Disparadores `usuario`
--
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
`Id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Disparadores `usuario_sector`
--
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

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `controlremoto`
--
ALTER TABLE `controlremoto`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `UQ_ANT_SIG` (`anterior`,`siguiente`), ADD UNIQUE KEY `UQ_ANT` (`anterior`), ADD UNIQUE KEY `UQ_SIG` (`siguiente`);

--
-- Indices de la tabla `controlremoto_sector`
--
ALTER TABLE `controlremoto_sector`
 ADD PRIMARY KEY (`Id`);

--
-- Indices de la tabla `perfil`
--
ALTER TABLE `perfil`
 ADD PRIMARY KEY (`id_perfil`);

--
-- Indices de la tabla `sat`
--
ALTER TABLE `sat`
 ADD PRIMARY KEY (`id_sat`);

--
-- Indices de la tabla `sat_hsat`
--
ALTER TABLE `sat_hsat`
 ADD PRIMARY KEY (`id_Hsat`);

--
-- Indices de la tabla `sat_hsector`
--
ALTER TABLE `sat_hsector`
 ADD PRIMARY KEY (`id_Hsector`);

--
-- Indices de la tabla `sat_husuario`
--
ALTER TABLE `sat_husuario`
 ADD PRIMARY KEY (`id_Husuario`);

--
-- Indices de la tabla `sat_husuario_sector`
--
ALTER TABLE `sat_husuario_sector`
 ADD PRIMARY KEY (`id_Husuario_sector`);

--
-- Indices de la tabla `sector`
--
ALTER TABLE `sector`
 ADD PRIMARY KEY (`id_sector`), ADD UNIQUE KEY `UK_cod_sector` (`cod_sector`), ADD UNIQUE KEY `UK_nom_sector` (`nom_sector`);

--
-- Indices de la tabla `turno`
--
ALTER TABLE `turno`
 ADD PRIMARY KEY (`id_turno`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
 ADD PRIMARY KEY (`id_usuario`), ADD UNIQUE KEY `UK_nom_usuario` (`nom_usuario`);

--
-- Indices de la tabla `usuario_sector`
--
ALTER TABLE `usuario_sector`
 ADD PRIMARY KEY (`Id`), ADD UNIQUE KEY `Usuario_sector_UK` (`ID_USUARIO`,`ID_SECTOR`), ADD KEY `usuariosector_sector` (`ID_SECTOR`), ADD KEY `usuariosector_usuario` (`ID_USUARIO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `controlremoto`
--
ALTER TABLE `controlremoto`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `controlremoto_sector`
--
ALTER TABLE `controlremoto_sector`
MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
MODIFY `id_perfil` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `sat`
--
ALTER TABLE `sat`
MODIFY `id_sat` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `sat_hsat`
--
ALTER TABLE `sat_hsat`
MODIFY `id_Hsat` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `sat_hsector`
--
ALTER TABLE `sat_hsector`
MODIFY `id_Hsector` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `sat_husuario`
--
ALTER TABLE `sat_husuario`
MODIFY `id_Husuario` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `sat_husuario_sector`
--
ALTER TABLE `sat_husuario_sector`
MODIFY `id_Husuario_sector` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `sector`
--
ALTER TABLE `sector`
MODIFY `id_sector` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `turno`
--
ALTER TABLE `turno`
MODIFY `id_turno` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `usuario_sector`
--
ALTER TABLE `usuario_sector`
MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
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
