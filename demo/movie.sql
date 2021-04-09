# Host: localhost  (Version: 5.7.26)
# Date: 2020-06-15 23:19:06
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "classify"
#

DROP TABLE IF EXISTS `classify`;
CREATE TABLE `classify` (
  `classid` int(11) NOT NULL AUTO_INCREMENT,
  `class` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`classid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "classify"
#

/*!40000 ALTER TABLE `classify` DISABLE KEYS */;
/*!40000 ALTER TABLE `classify` ENABLE KEYS */;

#
# Structure for table "lang"
#

DROP TABLE IF EXISTS `lang`;
CREATE TABLE `lang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

#
# Data for table "lang"
#

/*!40000 ALTER TABLE `lang` DISABLE KEYS */;
INSERT INTO `lang` VALUES (1,'国语');
/*!40000 ALTER TABLE `lang` ENABLE KEYS */;

#
# Structure for table "movie"
#

DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `title_sub` varchar(255) DEFAULT NULL,
  `story` varchar(255) DEFAULT NULL,
  `classid` varchar(255) DEFAULT NULL,
  `releasetime` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `regionid` varchar(255) DEFAULT NULL,
  `langid` varchar(255) DEFAULT NULL,
  `custompicpos` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "movie"
#

/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'中国合伙人2','6.5','互联网创业启示录','《中国合伙人2》是刘亚当执导的剧情片，赵立新、凌潇肃、王嘉、董琦等主演，于2018年12月18日在中国内地上映。影片以徐顺之的视角展现了一个程序员向创业者的蜕变，讲述了一代互联网人追逐梦想的传奇创业故事。2018年11月29日，由国家电影局指导，中影股份、华夏电影公司等单位联合推介，作为庆祝改革开放40周年9部重点国产影片之一。',NULL,'2018','122','','',NULL),(2,'一出好戏','7.6','黄渤艺兴荒岛求生',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'一句顶一万句','7.0','范伟刘蓓中年结婚',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'华丽上班族','6.8','全明星阵容歌舞片',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'巴黎假期','6.0','古仔采洁巴黎相爱',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'测试标题','测试分数','测试子标题','测试介绍','测试类别','2020-01-01','0','0','0',NULL),(15,'测试标题','测试分数','测试子标题','测试介绍','测试类别','2020-01-01','0','0','0',NULL),(16,'测试标题','测试分数','测试子标题','测试介绍','测试类别','2020-01-01','0','0','0',NULL),(17,'测试标题','测试分数','测试子标题','测试介绍','测试类别','2020-01-01','0','0','0',NULL),(18,'测试标题','测试分数','测试子标题','测试介绍','测试类别','2020-01-01','0','0','0',NULL),(21,'测试标题','测试分数','测试子标题','测试介绍','测试类别','2020-01-01','0','0','0',NULL),(22,'测试标题','测试分数','测试子标题','测试介绍','测试类别','2020-01-01','0','0','0',NULL),(23,'测试标题','测试分数','测试子标题','测试介绍','测试类别','2020-01-01','0','0','0',NULL),(24,'测试标题','测试分数','测试子标题','测试介绍','测试类别','2020-01-01','0','0','0',NULL),(25,'测试标题','测试分数','测试子标题','测试介绍','测试类别','2020-01-01','0','0','0',NULL);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;

#
# Structure for table "showbanner"
#

DROP TABLE IF EXISTS `showbanner`;
CREATE TABLE `showbanner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "showbanner"
#

/*!40000 ALTER TABLE `showbanner` DISABLE KEYS */;
INSERT INTO `showbanner` VALUES (1),(2),(3);
/*!40000 ALTER TABLE `showbanner` ENABLE KEYS */;

#
# Structure for table "users"
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "users"
#

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,'1','2'),(13,'1','2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
