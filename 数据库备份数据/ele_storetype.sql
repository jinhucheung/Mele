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
-- Table structure for table `storetype`
--

DROP TABLE IF EXISTS `storetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storetype` (
  `stypeid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `typename` varchar(10) DEFAULT NULL,
  `supertypeid` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`stypeid`),
  KEY `SuperTypeFK_idx` (`supertypeid`),
  CONSTRAINT `SuperTypeFK` FOREIGN KEY (`supertypeid`) REFERENCES `storetype` (`stypeid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storetype`
--

LOCK TABLES `storetype` WRITE;
/*!40000 ALTER TABLE `storetype` DISABLE KEYS */;
INSERT INTO `storetype` VALUES (1,'快餐便当',1),(2,'特色菜系',2),(3,'异国料理',3),(4,'小吃夜宵',4),(5,'甜品饮品',5),(6,'果蔬生鲜',6),(7,'鲜花蛋糕',7),(8,'盖浇饭',1),(9,'米粉面馆',1),(10,'汉堡',1),(11,'包子粥店',1),(12,'香锅砂锅',1),(13,'麻辣烫',1),(14,'饺子云吞',1),(15,'生煎锅贴',1),(16,'粤菜',2),(17,'川湘菜',2),(18,'东北菜',2),(19,'云南菜',2),(20,'江浙菜',2),(21,'西北菜',2),(22,'鲁菜',2),(23,'清真',2),(24,'火锅烤鱼',2),(25,'海鲜',2),(26,'日韩料理',3),(27,'披萨意面',3),(28,'西餐',3),(29,'东南亚菜',3),(30,'地方小吃',4),(31,'炸鸡炸串',4),(32,'小龙虾',4),(33,'零食',4),(34,'鸭脖卤味',4),(35,'烧烤',4),(36,'奶茶果汁',5),(37,'甜品',5),(38,'咖啡',5),(39,'水果',6),(40,'生鲜',6),(41,'蔬菜',6),(42,'鲜花',7),(43,'蛋糕',7),(44,'面包',7),(45,'其他菜系',2);
/*!40000 ALTER TABLE `storetype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-17 16:08:27
