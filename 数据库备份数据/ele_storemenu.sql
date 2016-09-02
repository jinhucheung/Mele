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
-- Table structure for table `storemenu`
--

DROP TABLE IF EXISTS `storemenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storemenu` (
  `storeid` bigint(20) unsigned NOT NULL,
  `foodid` int(10) unsigned NOT NULL,
  `foodname` varchar(30) DEFAULT NULL,
  `foodprices` float DEFAULT NULL,
  `foodtype` varchar(20) DEFAULT NULL,
  `foodic` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`storeid`,`foodid`),
  CONSTRAINT `storeidfk` FOREIGN KEY (`storeid`) REFERENCES `storeinfo` (`storeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storemenu`
--

LOCK TABLES `storemenu` WRITE;
/*!40000 ALTER TABLE `storemenu` DISABLE KEYS */;
INSERT INTO `storemenu` VALUES (1,1,'咸香棕',3,'美味潮汕粽','doujiayi_xianxiangzong.jpeg'),(1,2,'甜粽',3,'美味潮汕粽','doujiayi_xianxiangzong.jpeg'),(1,3,'双拼粽',3,'美味潮汕粽','doujiayi_xianxiangzong.jpeg'),(1,4,'墨西哥鸡肉卷',6.5,'西式快餐专区','doujiayi_jiroujuan.jpeg'),(1,5,'车仔面',5.5,'西式快餐专区','doujiayi_chezaimian.jpeg'),(1,6,'乌冬面',5.5,'西式快餐专区','doujiayi_wudongmian.jpeg'),(1,7,'美国大热狗',6,'汉堡','doujiayi_hotdog.jpeg'),(1,8,'香酥鸡扒堡',5.5,'汉堡','doujiayi_jipabao.jpeg'),(1,9,'香水柠檬绿茶',5,'夏日新饮品','doujiayi_xiangshuilingnengcha.jpeg'),(1,10,'柠檬乳酸菌',5,'夏日新饮品','doujiayi_lingnengrongshuangjiu.jpeg'),(1,11,'香酥鸡排',6,'风味小吃 ','doujiayi_jipa.png'),(1,12,'红豆双皮奶(4个)',6,'风味小吃 ','doujiayi_shuangpinai.png'),(2,1,'八宝冰	',8.8,'奶茶果汁','tangchaotianyuxian_babaobing.jpeg'),(4,1,'原味奶茶',9,'冷热饮类','	pengyouquanxianguoqie_yuanweinaicha.jpeg'),(4,2,'泡泡蛋奶茶',11,'冷热饮类','	pengyouquanxianguoqie_paopaodannaicha.jpeg'),(4,3,'百香果润',11,'冷热饮类','pengyouquanxianguoqie.jpeg'),(4,4,'烧仙草',11,'冷热饮类','pengyouquanxianguoqie.jpeg'),(4,5,'金桔柠檬',13,'冷热饮类','pengyouquanxianguoqie.jpeg'),(4,6,'芒果小乳酸',13,'冷热饮类','pengyouquanxianguoqie.jpeg'),(4,7,'	咸柠七',8,'冷热饮类','pengyouquanxianguoqie.jpeg'),(4,8,'	蜂蜜柚子茶',11,'冷热饮类','	pengyouquanxianguoqie.jpeg'),(4,9,'	红豆奶茶',10,'冷热饮类','	pengyouquanxianguoqie.jpeg'),(4,10,'水晶奶茶',10,'冷热饮类','pengyouquanxianguoqie.jpeg'),(4,11,'草莓西柚泡泡',13,'冷热饮类','	pengyouquanxianguoqie.jpeg'),(4,12,'朋友圈水果切',15.8,'水果切优惠套餐','	pengyouquanxianguoqie.jpeg'),(4,13,'金桔柠檬+芝士玉米饼',20,'水果切优惠套餐','	pengyouquanxianguoqie.jpeg'),(4,14,'葡萄奇异果+黄金薯条',22,'水果切优惠套餐','	pengyouquanxianguoqie.jpeg'),(4,15,'百香果润+小吃拼盘	',22,'水果切优惠套餐','	pengyouquanxianguoqie.jpeg'),(4,16,'	草莓西柚泡泡+鸡米花',20,'水果切优惠套餐','	pengyouquanxianguoqie.jpeg'),(4,17,'	黄金薯条',9,'小吃类','pengyouquanxianguoqie.jpeg'),(4,18,'笑脸薯条',9,'小吃类','pengyouquanxianguoqie.jpeg'),(4,19,'南瓜饼',11,'小吃类','pengyouquanxianguoqie.jpeg'),(4,20,'黄金香芋丸',9,'小吃类','	pengyouquanxianguoqie.jpeg'),(4,21,'桂圆红枣茶',9,'热饮类','pengyouquanxianguoqie.jpeg'),(4,22,'台式椰丝香麦',9,'热饮类','pengyouquanxianguoqie.jpeg'),(4,23,'鲜果西米露',2,'鲜果汁','pengyouquanxianguoqie.jpeg'),(4,24,'鲜果汁',9.9,'鲜果汁','pengyouquanxianguoqie.jpeg'),(5,1,'柠檬七喜',6,'奶茶类','maikunaijia_ningmengqixi.jpeg'),(5,2,'柠檬可乐',6,'奶茶类','maikunaijia_ningmengkele.jpeg'),(5,3,'玫瑰奶茶',5,'奶茶类','maikunaijia_meiguinaicha.jpeg'),(5,4,'咖啡奶茶',7,'奶茶类','maikunaijia_kafeinaicha.jpeg'),(5,5,'芒果芦荟优多',8,'奶茶类','maikunaijia_mangguoluhuiyouduo.jpeg'),(5,6,'芒果多多',9,'奶茶类','maikunaijia_mangguoduoduo.jpeg'),(5,7,'招牌奶茶',6,'奶茶类','maikunaijia_zhaopainaicha.jpeg'),(5,8,'鸡米花',6,'小吃类','maikunaijia_jimihua.jpeg'),(5,9,'鸡中翅(一对)',6,'小吃类','maikunaijia_jizhongchi.jpeg'),(5,10,'蜂蜜干翅(一对)',6,'小吃类','maikunaijia_fengmiqianchi.jpeg'),(5,11,'捞面',8,'小吃类','maikunaijia_laomian.jpeg'),(5,12,'骨肉相连(两串)',5,'小吃类','maikunaijia_gurouxianglian.jpeg'),(5,13,'单人套餐一',15,'套餐','maikunaijia_danrentaocanyi.jpeg'),(5,14,'单人套餐二',19,'套餐','maikunaijia_danrentaocaner.jpeg'),(6,1,'煎蛋',2,'热销榜','huanhuankejiacaiguan_jiandan.jpeg'),(6,2,'三杯鸡饭',8,'热销榜','huanhuankejiacaiguan_sanbeijipinyanjuji.jpeg'),(6,3,'三杯鸡拼葱油鸡',10,'热销榜','huanhuankejiacaiguan_sanbeijifan.jpeg'),(6,4,'三杯鸡拼葱油鸡',10,'热销榜','huanhuankejiacaiguan_sanbeijipincongyouji.jpeg'),(6,5,'三杯鸡拼三杯鸭',10,'热销榜','	huanhuankejiacaiguan_sanbeijipinsanbeiya.jpeg'),(6,7,'百事罐装',3,'热销榜','huanhuankejiacaiguan_baishiguanzhuang.jpeg'),(6,8,'可乐鸡腿+三杯鸭+青菜',10,'热销榜','	huanhuankejiacaiguan_kelejitui+sanbei.jpeg'),(6,9,'三杯鸡饭',8,'现炒快餐','	huanhuankejiacaiguan_kelejitui+sanbei.jpeg'),(6,10,'三杯鸭饭',8,'现炒快餐','huanhuankejiacaiguan_sanbeijifan.jpeg'),(6,11,'盐焗鸡饭',8,'现炒快餐','huanhuankejiacaiguan_yanjujifan.jpeg'),(6,12,'葱油鸡饭',9,'现炒快餐','	huanhuankejiacaiguan_congyoujifan.jpeg'),(6,13,'茄子饭',7,'现炒快餐','huanhuankejiacaiguan_qiezifan.jpeg'),(6,14,'茄子+土豆肉片+青菜',8,'现炒快餐',''),(6,15,'茄子+腐竹肉片+青菜',8,'现炒快餐',''),(6,16,'腐竹肉片',8,'现炒快餐',''),(6,18,'腐竹肉片+土豆肉片+青菜',8,'现炒快餐',''),(7,1,'雪碧',3,'饮料','lifenjiaolingwujidi_xuebi.jpeg'),(7,2,'可怜',3,'饮料','lifenjiaolingwujidi_kele.jpeg'),(7,3,'椰子汁',5,'饮料','lifenjiaolingwujidi_yezi.jpeg'),(7,4,'红牛',6,'饮料','lifenjiaolingwujidi_hongniu.jpeg'),(7,5,'易拉青岛',5,'饮料','lifenjiaolingwujidi_yilaqingdao.jpeg'),(7,6,'五及第煮粉面',12,'粉面类','lifenjiaolingwujidi_wujidizhufenmian.jpeg'),(7,7,'牛肉煮粉面',12,'粉面类','lifenjiaolingwujidi_niurouzhufenmian.jpeg'),(7,8,'客家牛筋圆煮粉面',12,'粉面类','lifenjiaolingwujidi_kejianiujinyuanzhufenmian.jpeg'),(7,9,'蕉岭肉圆煮粉面',12,'粉面类','lifenjiaolingwujidi_jiaolingrouyuanzhufenmian.jpeg'),(7,10,'三及第煮粉面',7,'粉面类','lifenjiaolingwujidi_sanjidizhufenmian.jpeg'),(7,11,'瘦肉煮粉面',7,'粉面类','lifenjiaolingwujidi_shourouzhufenmian.jpeg'),(8,1,'三十六味凉茶王',6,'凉茶类',NULL),(8,2,'酸梅汤',4,'凉茶类',NULL),(8,3,'椰子奶',4,'凉茶类',NULL),(8,4,'老药桔',15,'凉茶类',NULL),(8,5,'泡鸭爪',2,'凉茶类',NULL),(8,6,'银耳莲子糖水',4,'凉茶类',NULL),(8,7,'芦荟龟苓膏',4,'凉茶类',NULL),(8,8,'上品龟苓膏',4,'凉茶类',NULL),(8,9,'潮汕绿豆饼',8,'小吃类',NULL),(8,10,'红牛',6,'饮料',NULL),(8,11,'东鹏特饮',3,'饮料',NULL),(8,12,'火咖',5,'饮料',NULL),(8,13,'柠檬口味维他命',5,'饮料',NULL),(8,14,'优乐多',7,'饮料',NULL),(8,15,'纯生啤酒',6,'饮料',NULL),(9,1,'蘑菇鸡肉意粉',10,'意粉','shiyumeiwei_jirouyifen.jpeg'),(9,2,'肉酱意粉',10,'意粉','shiyumeiwei_roujiangyifen.jpeg'),(9,3,'培根奶油意粉',10,'意粉','shiyumeiwei_peigenyifen.jpeg'),(9,4,'杂果披萨',22,'披萨','shiyumeiwei_zhaguopisha.jpeg'),(9,5,'提拉米苏',8,'蛋糕','shiyumeiwei_tilamisu.jpeg');
/*!40000 ALTER TABLE `storemenu` ENABLE KEYS */;
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
