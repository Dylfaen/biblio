-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: biblio
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrateur`
--

DROP TABLE IF EXISTS `administrateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrateur` (
  `ref_admin` int(11) NOT NULL,
  PRIMARY KEY (`ref_admin`),
  CONSTRAINT `administrateur_user_idUser_fk` FOREIGN KEY (`ref_admin`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrateur`
--

LOCK TABLES `administrateur` WRITE;
/*!40000 ALTER TABLE `administrateur` DISABLE KEYS */;
INSERT INTO `administrateur` VALUES (3);
/*!40000 ALTER TABLE `administrateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auteur`
--

DROP TABLE IF EXISTS `auteur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auteur` (
  `ref_auteur` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `prenom` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `naissance` date DEFAULT NULL,
  `nationalite` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ref_auteur`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auteur`
--

LOCK TABLES `auteur` WRITE;
/*!40000 ALTER TABLE `auteur` DISABLE KEYS */;
INSERT INTO `auteur` VALUES (1,'Tolkien','J.R.R',NULL,'Britannique'),(2,'Rowling','J.K.',NULL,'Britannique'),(3,'Hugo','Victor',NULL,'Française'),(4,'Camus','Albert',NULL,'Française');
/*!40000 ALTER TABLE `auteur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `ref_client` int(11) NOT NULL,
  PRIMARY KEY (`ref_client`),
  CONSTRAINT `client_user_idUser_fk` FOREIGN KEY (`ref_client`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1),(4);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprunt`
--

DROP TABLE IF EXISTS `emprunt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emprunt` (
  `id_user` int(11) NOT NULL,
  `id_exemplaire` int(11) NOT NULL,
  `dateEmprunt` date NOT NULL,
  PRIMARY KEY (`id_user`,`id_exemplaire`,`dateEmprunt`),
  KEY `exemplaire___fk` (`id_exemplaire`),
  CONSTRAINT `exemplaire___fk` FOREIGN KEY (`id_exemplaire`) REFERENCES `exemplaire` (`id_exemplaire`),
  CONSTRAINT `user___fk` FOREIGN KEY (`id_user`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprunt`
--

LOCK TABLES `emprunt` WRITE;
/*!40000 ALTER TABLE `emprunt` DISABLE KEYS */;
/*!40000 ALTER TABLE `emprunt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exemplaire`
--

DROP TABLE IF EXISTS `exemplaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exemplaire` (
  `id_exemplaire` int(11) NOT NULL AUTO_INCREMENT,
  `id_oeuvre` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_exemplaire`),
  KEY `oeuvre___fk` (`id_oeuvre`),
  CONSTRAINT `oeuvre___fk` FOREIGN KEY (`id_oeuvre`) REFERENCES `oeuvre` (`ref_oeuvre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exemplaire`
--

LOCK TABLES `exemplaire` WRITE;
/*!40000 ALTER TABLE `exemplaire` DISABLE KEYS */;
INSERT INTO `exemplaire` VALUES (1,2),(2,2);
/*!40000 ALTER TABLE `exemplaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livre`
--

DROP TABLE IF EXISTS `livre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livre` (
  `ref_livre` int(11) NOT NULL,
  `Editeur` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ref_livre`),
  CONSTRAINT `livre_oeuvre_ref_oeuvre_fk` FOREIGN KEY (`ref_livre`) REFERENCES `oeuvre` (`ref_oeuvre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livre`
--

LOCK TABLES `livre` WRITE;
/*!40000 ALTER TABLE `livre` DISABLE KEYS */;
/*!40000 ALTER TABLE `livre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oeuvre`
--

DROP TABLE IF EXISTS `oeuvre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oeuvre` (
  `ref_oeuvre` int(11) NOT NULL AUTO_INCREMENT,
  `ref_auteur` int(11) DEFAULT NULL,
  `titre` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ref_oeuvre`),
  KEY `auteur___fk` (`ref_auteur`),
  CONSTRAINT `auteur___fk` FOREIGN KEY (`ref_auteur`) REFERENCES `auteur` (`ref_auteur`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oeuvre`
--

LOCK TABLES `oeuvre` WRITE;
/*!40000 ALTER TABLE `oeuvre` DISABLE KEYS */;
INSERT INTO `oeuvre` VALUES (2,1,'LSDA'),(3,2,'HP'),(4,3,'Les Fleurs du Mal'),(5,4,'L\'Etranger'),(6,3,'Le bossu de Notre Dame'),(7,2,'L\'Appel du Coucou'),(8,4,'Le Mythe de Sisyphe'),(9,3,'Les Miserables');
/*!40000 ALTER TABLE `oeuvre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `identifiant` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `nom` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `prenom` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `naissance` datetime DEFAULT NULL,
  `adresse` varchar(90) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'CCC','alexlafripouille','De carvalho','Alexandre',NULL,NULL),(3,'AAA','6534c3556e922bf3cc9cf5199762a1320e585a9b8d19ddea9d54f7b14be5a8751816e2eaba8e2ab17a5bd4a87277bdaa028ca7e9b10874b772cd3afe34a793be','Fraisseix','Cesar',NULL,'30 rue de la paquerette'),(4,'BBB','gwenlebarbu','Rio','Gwenael',NULL,'1 rue du démon');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-16 17:08:09
