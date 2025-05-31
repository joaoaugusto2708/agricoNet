-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sensores
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alarme`
--

DROP TABLE IF EXISTS `alarme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarme` (
  `id` int NOT NULL AUTO_INCREMENT,
  `siloId` int NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `dataHoraAlarme` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `siloId` (`siloId`),
  CONSTRAINT `alarme_ibfk_1` FOREIGN KEY (`siloId`) REFERENCES `silo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarme`
--

LOCK TABLES `alarme` WRITE;
/*!40000 ALTER TABLE `alarme` DISABLE KEYS */;
/*!40000 ALTER TABLE `alarme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `silo`
--

DROP TABLE IF EXISTS `silo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `silo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `localidade` varchar(100) NOT NULL,
  `modelo` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `silo`
--

LOCK TABLES `silo` WRITE;
/*!40000 ALTER TABLE `silo` DISABLE KEYS */;
INSERT INTO `silo` VALUES (1,'Silo Alfa','São Paulo','Modelo A'),(2,'Silo Beta','Campinas','Modelo B'),(3,'Silo Gama','Ribeirão Preto','Modelo C'),(4,'Silo Delta','Sorocaba','Modelo A'),(5,'Silo Épsilon','Santos','Modelo B');
/*!40000 ALTER TABLE `silo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temperatura`
--

DROP TABLE IF EXISTS `temperatura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temperatura` (
  `id` int NOT NULL AUTO_INCREMENT,
  `siloId` int NOT NULL,
  `temperaturaSilo` float NOT NULL,
  `dataMedicao` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `siloId` (`siloId`),
  CONSTRAINT `temperatura_ibfk_1` FOREIGN KEY (`siloId`) REFERENCES `silo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temperatura`
--

LOCK TABLES `temperatura` WRITE;
/*!40000 ALTER TABLE `temperatura` DISABLE KEYS */;
INSERT INTO `temperatura` VALUES (1,1,23.1,'2025-05-25 08:30:00'),(2,2,24.5,'2025-05-25 08:30:00'),(3,3,20,'2025-05-25 08:30:00');
/*!40000 ALTER TABLE `temperatura` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-28 23:51:14
