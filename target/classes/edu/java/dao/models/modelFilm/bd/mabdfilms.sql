--
-- Base de données : `umbdfilms`
--
CREATE DATABASE IF NOT EXISTS `mabdfilms` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `mabdfilms`;

--
-- Structure de la table `films`
--

CREATE TABLE `films` (
  `idf` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `duree` int(11) NOT NULL,
  `res` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `pochette` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  CONSTRAINT `films_idf_FK` PRIMARY KEY(`idf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
