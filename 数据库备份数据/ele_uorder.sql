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
-- Table structure for table `uorder`
--

DROP TABLE IF EXISTS `uorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uorder` (
  `uaccount` char(16) NOT NULL,
  `ordercode` char(16) NOT NULL,
  `storeId` bigint(20) unsigned DEFAULT NULL,
  `uaddressid` int(10) unsigned DEFAULT NULL,
  `orderfoodid` varchar(25) DEFAULT NULL,
  `orderfoodpricenum` varchar(50) DEFAULT NULL,
  `uNote` char(50) DEFAULT NULL,
  `orderdate` datetime DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uaccount`,`ordercode`),
  UNIQUE KEY `ordercode_UNIQUE` (`ordercode`),
  KEY `storeidfk_idx` (`storeId`),
  KEY `uaddressidfk_idx` (`uaddressid`),
  CONSTRAINT `sstoreidfk` FOREIGN KEY (`storeId`) REFERENCES `storeinfo` (`storeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uaccountidfk` FOREIGN KEY (`uaccount`) REFERENCES `user` (`account`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uaddressidfk` FOREIGN KEY (`uaddressid`) REFERENCES `uaddress` (`uaddressid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uorder`
--

LOCK TABLES `uorder` WRITE;
/*!40000 ALTER TABLE `uorder` DISABLE KEYS */;
INSERT INTO `uorder` VALUES ('HelloWorld','2016060712000123',1,2,'1;2;3;','3x1;3x2;3x1;','短号638333','2016-06-07 12:00:00','还好'),('HelloWorld','2016060716301122',1,3,'1;6;7;','3x1;5.5x2;6x1;','短号638223','2016-06-07 16:30:00','挺好吃的'),('HelloWorld','2016061613274212',9,1,'3;4','10x1;22x2;',NULL,'2016-06-16 13:27:42','可以~'),('HelloWorld','2016061714062164',1,1,'1;2;','3x2;3x2;','','2016-06-17 14:06:21',''),('HelloWorld','2016061715054330',1,1,'2;3;','3x3;3x3;','','2016-06-17 15:05:43',''),('HelloWorld','2016061715493453',1,1,'1;2;','3x4;3x3;','','2016-06-17 15:49:34',''),('HiMele','2016060812112111',9,1,'4;','22x1;','请尽快送','2016-06-08 12:11:00','不错:)'),('HiMele','2016061212301242',9,1,'4;','22x2;','短号638444','2016-06-12 12:30:00',':)');
/*!40000 ALTER TABLE `uorder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-17 16:08:29
