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
-- Table structure for table `storeinfo`
--

DROP TABLE IF EXISTS `storeinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeinfo` (
  `storeId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `storeName` varchar(28) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `citycode` char(7) DEFAULT NULL,
  `shophours` char(24) DEFAULT NULL,
  `latlng` varchar(30) DEFAULT NULL,
  `startprice` float unsigned DEFAULT NULL,
  `transportprice` float unsigned DEFAULT NULL,
  `storelogo` varchar(50) DEFAULT NULL,
  `notice` varchar(50) DEFAULT NULL,
  `cheapennotice` varchar(50) DEFAULT NULL,
  `newusernotice` varchar(50) DEFAULT NULL,
  `tel` char(11) DEFAULT NULL,
  `uaccount` char(16) NOT NULL,
  PRIMARY KEY (`storeId`),
  KEY `citycodefk` (`citycode`),
  KEY `uaccountfk` (`uaccount`),
  CONSTRAINT `citycodefk` FOREIGN KEY (`citycode`) REFERENCES `cities` (`citycode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uaccountfk` FOREIGN KEY (`uaccount`) REFERENCES `user` (`account`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeinfo`
--

LOCK TABLES `storeinfo` WRITE;
/*!40000 ALTER TABLE `storeinfo` DISABLE KEYS */;
INSERT INTO `storeinfo` VALUES (1,'豆加一','梅州市梅江区嘉应大学校道中段','0753','07:00-22:00','116.1231610000,24.3231070000',10,0,'doujiayi.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满10减1','新用户下单立减6元','18813973111','Jack'),(2,'糖朝天芋仙','嘉应大学','0753','09:00-22:30','116.1292550000,24.3276430000',15,0,'tangchaotianyuxian.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813973122','Bao'),(3,'Cc蛋糕奶咖','嘉应大学','0753','09:00-22:00','116.1292550000,24.3276430000',20,0,'ccdangao.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813973133','Hi1'),(4,'朋友圈鲜果切','广东省梅州市梅江区东山大道132号','0753','10:00-22:00','116.1228060000,24.3197200000',20,0,'pengyouquanxianguoqie.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813973144','Hi2'),(5,'麦酷奶咖','梅州市梅江区二路','0753','09:30-22:00','116.1166160000,24.3103840000',35,0,'maikunaika.jpeg','我们会用最快的速度，送到你的手里！','在线支付满20减3，满40减6','新用户下单立减6元','18813973155','Hi3'),(6,'欢欢客家菜馆','广东省梅州市梅江区月梅路','0753','10:00-23:30','116.1243260000,24.3264310000',8,0,'huanhuankejia.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813973166','Hi4'),(7,'丽芬蕉岭五及第','梅江区江边路E2区123号店','0753','10:00-22:00','116.1266640000,24.3080630000',15,0,'lifenjiaolingwujidi.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813973177','Hi5'),(8,'不二碗凉茶','梅州市梅松路劳服大楼一楼','0753','08:00-23:00','116.1228650000,24.3225620000',10,0,'buerwanliangcha.jpeg','致嘉大好青年：抢红包、赢好运','在线支付满20减3，满40减6','新用户下单立减6元','18813973188','Hi6'),(9,'时遇美味','梅州市梅县东镇石下村挖子里','0753','10:30-21:00','116.1423944379,24.3764902961',10,0,'shiyumeiwei.png','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813973199','Hi7'),(10,'符大大紫菜饭团烧','嘉应学院','0753','08:00-23:00','116.1292550000,24.3276430000',7,0,'fudada.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813973110','Hi8'),(11,'真功夫(龙城乐购)','深圳市龙岗区龙岗街道平南社区龙河路榭丽花园三期地上一层S22','0755','10:00-20:00','114.2550070995,22.7187011439',0,5,'23d537aaf7944521a73518d11c6f6.jpeg','真功夫，五一5折套餐活动每天单套餐仅限十份，抢完为止。','在线支付满20减3，满40减6','新用户下单立减6元','18813877123','zhengongfu'),(12,'我家小厨','番禺区大学城北一路GOGO新天地超市3楼3B038号铺','020','09:30-21:00','113.3921444758,23.0607689626',10,5,'guangz.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813877122','Hi1000'),(13,'回味汤粉王','南山区桂庙新村西部百货校园美食街一楼','0755','00:00-24:00','113.9333213444,22.5266651351',12,3,'shenzun.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813877121','Hi1001'),(14,'至城水果','大岭山镇国际农贸城水果区横二路16-18号','0769','00:00-24:00','113.6488118106,23.0992493330',11,5,'dongg.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813877120','Hi1002'),(15,'成都小吃','海淀区树村厢白旗箭亭14号平房','010','00:00-24:00','116.3197915827,40.0139069297',0,5,'beijin.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813877119','Hi1003'),(16,'简.西餐','上海市徐汇区纷阳路64弄3-1','021','09:30-20:30','121.4540517309,31.2115657113',0,5,'shanghai.jpeg','欢迎光临，用餐高峰期请提前下单，谢谢。','在线支付满20减3，满40减6','新用户下单立减6元','18813877118','Hi1004');
/*!40000 ALTER TABLE `storeinfo` ENABLE KEYS */;
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
