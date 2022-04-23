-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: oee-db
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `line1`
--

DROP TABLE IF EXISTS `line1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `line1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` varchar(255) DEFAULT NULL,
  `counterout` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `downtime` varchar(255) DEFAULT NULL,
  `line` varchar(255) DEFAULT NULL,
  `oee` varchar(255) DEFAULT NULL,
  `performance` varchar(255) DEFAULT NULL,
  `quanlity` varchar(255) DEFAULT NULL,
  `runtime` varchar(255) DEFAULT NULL,
  `shift` varchar(255) DEFAULT NULL,
  `speed` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `speedstandard` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line1`
--

LOCK TABLES `line1` WRITE;
/*!40000 ALTER TABLE `line1` DISABLE KEYS */;
INSERT INTO `line1` VALUES (1,'0','44','2022-04-23','467.0','1','0','110','100','1.0','shift 2','0','2','5000','21:48:16.087','40'),(2,'0','44','2022-04-23','467.0','1','0','110','100','1.0','shift 2','0','1','5000','21:48:18.112','40'),(3,'0','45','2022-04-23','467.0','1','0','112','100','1.0','shift 2','0','1','5000','21:48:19.040','40'),(4,'0','45','2022-04-23','467.0','1','0','112','100','1.0','shift 2','0','1','5000','21:48:19.915','40'),(5,'0','46','2022-04-23','467.0','1','0','115','100','1.0','shift 2','0','1','5000','21:48:20.813','40'),(6,'0','46','2022-04-23','467.0','1','0','115','100','1.0','shift 2','0','1','5000','21:48:21.698','40'),(7,'0','47','2022-04-23','467.0','1','0','117','100','1.0','shift 2','0','1','5000','21:48:22.556','40'),(8,'0','48','2022-04-23','467.0','1','0','120','100','1.0','shift 2','0','1','5000','21:48:23.425','40'),(9,'0','48','2022-04-23','467.0','1','0','120','100','1.0','shift 2','0','1','5000','21:48:24.284','40'),(10,'0','49','2022-04-23','467.0','1','0','122','100','1.0','shift 2','0','1','5000','21:48:25.144','40'),(11,'0','49','2022-04-23','467.0','1','0','122','100','1.0','shift 2','0','1','5000','21:48:26.006','40'),(12,'0','50','2022-04-23','467.0','1','0','125','100','1.0','shift 2','0','1','5000','21:48:26.878','40'),(13,'0','50','2022-04-23','467.0','1','0','125','100','1.0','shift 2','0','2','5000','21:48:27.748','40'),(14,'0','50','2022-04-23','467.0','1','0','125','100','1.0','shift 2','0','2','5000','21:48:28.594','40'),(15,'0','50','2022-04-23','467.0','1','0','125','100','1.0','shift 2','0','2','5000','21:48:29.447','40'),(16,'0','50','2022-04-23','467.0','1','0','125','100','1.0','shift 2','0','2','5000','21:48:30.313','40'),(17,'0','50','2022-04-23','467.0','1','0','125','100','1.0','shift 2','0','2','5000','21:48:31.178','40'),(18,'0','50','2022-04-23','467.0','1','0','125','100','1.0','shift 2','0','2','5000','21:48:32.032','40'),(19,'0','50','2022-04-23','467.0','1','0','125','100','1.0','shift 2','0','1','5000','21:48:32.872','40'),(20,'0','50','2022-04-23','467.0','1','0','125','100','1.0','shift 2','0','1','5000','21:48:33.712','40'),(21,'0','51','2022-04-23','467.0','1','0','127','100','1.0','shift 2','0','1','5000','21:48:34.554','40'),(22,'0','52','2022-04-23','467.0','1','0','130','100','1.0','shift 2','0','1','5000','21:48:35.652','40'),(23,'0','52','2022-04-23','467.0','1','0','130','100','1.0','shift 2','0','1','5000','21:48:36.725','40'),(24,'0','53','2022-04-23','467.0','1','0','132','100','1.0','shift 2','0','1','5000','21:48:37.777','40'),(25,'0','54','2022-04-23','467.0','1','0','135','100','1.0','shift 2','0','1','5000','21:48:38.624','40'),(26,'0','54','2022-04-23','467.0','1','0','135','100','1.0','shift 2','0','1','5000','21:48:39.474','40'),(27,'0','55','2022-04-23','467.0','1','0','137','100','1.0','shift 2','0','1','5000','21:48:40.317','40');
/*!40000 ALTER TABLE `line1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-23 21:48:40
