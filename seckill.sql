
/*
mysql> select version();
+------------+
| version()  |
+------------+
| 5.7.17-log |
+------------+
1 row in set (0.00 sec)
*/

drop table if exists goods;

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) DEFAULT NULL COMMENT '商品的图片',
  `goods_detail` longtext COMMENT '商品的详情介绍',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `goods_stock` int(11) DEFAULT '0' COMMENT '商品库存，-1表示没有限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;


LOCK TABLES `goods` WRITE;

INSERT INTO `goods` VALUES
(1,'iphoneX','Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机','/img/iphonex.png','Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机',8765.00,10000),
(2,'华为Meta9','华为 Mate 9 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待','/img/meta10.png','华为 Mate 9 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待',3212.00,-1),
(3,'iphone8','Apple iPhone 8 (A1865) 64GB 银色 移动联通电信4G手机','/img/iphone8.png','Apple iPhone 8 (A1865) 64GB 银色 移动联通电信4G手机',5589.00,10000),
(4,'小米6','小米6 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待','/img/mi6.png','小米6 4GB+32GB版 月光银 移动联通电信4G手机 双卡双待',3212.00,10000);

UNLOCK TABLES;


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '用户ID，手机号码',
  `nickname` varchar(255) NOT NULL,
  `password` varchar(32) DEFAULT NULL COMMENT 'MD5(MD5(pass明文+固定salt) + salt)',
  `salt` varchar(10) DEFAULT NULL,
  `head` varchar(128) DEFAULT NULL COMMENT '头像，云存储的ID',
  `register_date` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '上蔟登录时间',
  `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (13000000000,'user0','b7797cce01b4b131b433b6acf4add449','1a2b3c',NULL,'2017-11-30 09:01:59',NULL,1);
UNLOCK TABLES;

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `delivery_addr_id` bigint(20) DEFAULT NULL COMMENT '收获地址ID',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '冗余过来的商品名称',
  `goods_count` int(11) DEFAULT '0' COMMENT '商品数量',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `order_channel` tinyint(4) DEFAULT '0' COMMENT '1pc，2android，3ios',
  `status` tinyint(4) DEFAULT '0' COMMENT '订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
  `create_date` datetime DEFAULT NULL COMMENT '订单的创建时间',
  `pay_date` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `orders` WRITE;

INSERT INTO `orders` VALUES
(1,18912341234,1,NULL,'iphoneX',1,0.01,1,0,'2017-12-14 22:49:10',NULL),
(2,18912341234,2,NULL,'华为Meta9',1,0.01,1,0,'2017-12-14 22:55:42',NULL),
(3,18912341234,4,NULL,'小米6',1,0.01,1,0,'2017-12-16 16:19:23',NULL),
(4,18912341234,3,NULL,'iphone8',1,0.01,1,0,'2017-12-16 16:35:20',NULL);

UNLOCK TABLES;

DROP TABLE IF EXISTS `miaosha_goods`;

CREATE TABLE seckill_goods (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀的商品表',
                               `goods_id` bigint(20) DEFAULT NULL COMMENT '商品Id',
                               `seckill_price` decimal(10,2) DEFAULT '0.00' COMMENT '秒杀价',
                               `stock_count` int(11) DEFAULT NULL COMMENT '库存数量',
                               `start_date` datetime DEFAULT NULL COMMENT '秒杀开始时间',
                               `end_date` datetime DEFAULT NULL COMMENT '秒杀结束时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;



LOCK TABLES `seckill_goods` WRITE;
INSERT INTO `seckill_goods` VALUES (1,1,0.01,9,'2017-12-04 21:51:23','2017-12-31 21:51:27'),(2,2,0.01,9,'2017-12-04 21:40:14','2017-12-31 14:00:24'),(3,3,0.01,9,'2017-12-04 21:40:14','2017-12-31 14:00:24'),(4,4,0.01,9,'2017-12-04 21:40:14','2017-12-31 14:00:24');
UNLOCK TABLES;


DROP TABLE IF EXISTS `seckill_order`;

CREATE TABLE `seckill_order` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
                                 `order_id` bigint(20) DEFAULT NULL COMMENT '订单ID',
                                 `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `u_uid_gid` (`user_id`,`goods_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1551 DEFAULT CHARSET=utf8mb4;


LOCK TABLES `seckill_order` WRITE;
INSERT INTO `seckill_order` VALUES (1547,18912341234,1561,1),(1548,18912341234,1562,2),(1549,18912341234,1563,4),(1550,18912341234,1564,3);
UNLOCK TABLES;

