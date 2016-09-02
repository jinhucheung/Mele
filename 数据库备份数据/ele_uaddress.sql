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
-- Table structure for table `uaddress`
--

DROP TABLE IF EXISTS `uaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uaddress` (
  `uaddressid` int(10) unsigned NOT NULL,
  `uaccount` char(16) NOT NULL,
  `ucontact` varchar(12) DEFAULT NULL,
  `usex` int(11) DEFAULT NULL,
  `utel` char(11) DEFAULT NULL,
  `uaddress` varchar(20) DEFAULT NULL,
  `utag` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`uaddressid`,`uaccount`),
  KEY `accountfk` (`uaccount`),
  CONSTRAINT `accountfk` FOREIGN KEY (`uaccount`) REFERENCES `user` (`account`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uaddress`
--

LOCK TABLES `uaddress` WRITE;
/*!40000 ALTER TABLE `uaddress` DISABLE KEYS */;
INSERT INTO `uaddress` VALUES (1,'Bao','陈红',2,'18813973331','嘉应学院南区5栋','宿舍'),(1,'HelloWorld','王小明',1,'18813973333','梅州梅江区江边路11号','家里'),(1,'HiKu','王小明',1,'18813973333','嘉应学院南区7栋','宿舍'),(2,'HelloWorld','李杰',1,'18813972222','嘉应学院南区7栋','宿舍'),(2,'HiKu','李杰',1,'18813972222','嘉应学院南区7栋','宿舍'),(3,'HelloWorld','王小明',1,'18813973333','嘉应学院南区7栋','宿舍'),(3,'HiKu','王小明',1,'18813973333','梅州梅江区江边路11号','家里');
/*!40000 ALTER TABLE `uaddress` ENABLE KEYS */;
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
