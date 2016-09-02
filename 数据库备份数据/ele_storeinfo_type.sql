-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: ele
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `storeinfo_type`
--

DROP TABLE IF EXISTS `storeinfo_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeinfo_type` (
  `storeid` bigint(20) unsigned NOT NULL,
  `stypeid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`storeid`,`stypeid`),
  KEY `typefk_idx` (`stypeid`),
  CONSTRAINT `infofk` FOREIGN KEY (`storeid`) REFERENCES `storeinfo` (`storeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `typefk` FOREIGN KEY (`stypeid`) REFERENCES `storetype` (`stypeid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeinfo_type`
--

LOCK TABLES `storeinfo_type` WRITE;
/*!40000 ALTER TABLE `storeinfo_type` DISABLE KEYS */;
INSERT INTO `storeinfo_type` VALUES (11,1),(11,2),(6,8),(10,8),(12,8),(15,8),(7,9),(13,9),(1,11),(13,13),(6,16),(11,16),(12,16),(15,17),(15,25),(9,27),(16,27),(10,28),(16,28),(3,30),(7,30),(10,30),(13,30),(15,30),(4,33),(14,33),(1,36),(2,36),(3,36),(4,36),(5,36),(8,36),(9,36),(16,36),(7,37),(9,37),(16,37),(4,39),(14,39),(3,43),(3,44);
/*!40000 ALTER TABLE `storeinfo_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-17 16:08:28
