-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.9-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema webbanhang
--

CREATE DATABASE IF NOT EXISTS webbanhang;
USE webbanhang;

--
-- Definition of table `bill`
--

DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `ID_Bill` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) unsigned NOT NULL,
  `Address` varchar(100) NOT NULL,
  `District` varchar(20) NOT NULL,
  `City` varchar(50) NOT NULL,
  `DateModified` datetime NOT NULL,
  `Status` varchar(30) NOT NULL DEFAULT 'active',
  `Total` decimal(20,0) DEFAULT NULL,
  PRIMARY KEY (`ID_Bill`),
  KEY `FK_bill_customer` (`customer_id`),
  CONSTRAINT `FK_bill_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bill`
--

/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` (`ID_Bill`,`customer_id`,`Address`,`District`,`City`,`DateModified`,`Status`,`Total`) VALUES 
 (6,12,'asdfsafafaf','sadfsa','agaasfsaf','2019-09-16 00:00:00','active','12000'),
 (7,12,'dia chi nha m','tan binh','hcm','2019-09-15 00:00:00','pending','1000000000');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;


--
-- Definition of table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  `category_type` int(10) unsigned DEFAULT '0',
  `category_status` varchar(45) NOT NULL DEFAULT 'active',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `unique_category` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`category_id`,`category_name`,`category_type`,`category_status`) VALUES 
 (8,'active',2,'remove'),
 (9,'adsfasasffd',3,'active');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


--
-- Definition of table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `ID_Comment` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_Product` bigint(20) unsigned NOT NULL DEFAULT '0',
  `Title` varchar(100) NOT NULL,
  `customer_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `Content` varchar(200) NOT NULL,
  `DateModified` datetime NOT NULL,
  PRIMARY KEY (`ID_Comment`),
  KEY `FK_comment_product` (`ID_Product`),
  KEY `FK_comment_customer` (`customer_id`),
  CONSTRAINT `FK_comment_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ID`),
  CONSTRAINT `FK_comment_product` FOREIGN KEY (`ID_Product`) REFERENCES `product` (`PRODUCT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment`
--

/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`ID_Comment`,`ID_Product`,`Title`,`customer_id`,`Content`,`DateModified`) VALUES 
 (1,4,'asdfasdfsakfjasfkasjfksaf',12,'asdfsafdasfsafsafd','2019-09-16 00:00:00');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;


--
-- Definition of table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `ID` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `STATUS` varchar(45) NOT NULL DEFAULT 'active',
  `AMT_BALANCE` bigint(20) unsigned NOT NULL DEFAULT '0',
  `PHONE` varchar(13) NOT NULL,
  `District` varchar(20) NOT NULL,
  `City` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `username_unique` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`ID`,`USERNAME`,`PASSWORD`,`ADDRESS`,`STATUS`,`AMT_BALANCE`,`PHONE`,`District`,`City`) VALUES 
 (00000000000000000012,'vuyeuai_007@yahoo.com','123456',NULL,'active',0,'','',''),
 (00000000000000000013,'daoanhvu19284673@gmail.com','vu',NULL,'',0,'','',''),
 (00000000000000000014,'vuyeuai_0asdf07@yahoo.com','123456',NULL,'pending',0,'','',''),
 (00000000000000000015,'vuyeuai_007@adsfasyahoo.com','123456',NULL,'pending',0,'','',''),
 (00000000000000000016,'vuyeuai_007@yasdfasahoo.com','123456',NULL,'pending',0,'','',''),
 (00000000000000000022,'daoanhvu1928467350@gmail.com','123456',NULL,'pending',1000,'','',''),
 (00000000000000000023,'vuyeuai_007@yasdfasdahoo.com','123456789',NULL,'pending',0,'','',''),
 (00000000000000000025,'vuyeuai_007@yaasdfsadhoo.com','123456',NULL,'pending',0,'','',''),
 (00000000000000000026,'vuyeuai_007@yahoo.comasdfas','123456',NULL,'pending',0,'','','');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Definition of table `detail_bill`
--

DROP TABLE IF EXISTS `detail_bill`;
CREATE TABLE `detail_bill` (
  `ID_Bill` bigint(20) unsigned NOT NULL DEFAULT '0',
  `ID_Product` bigint(20) unsigned NOT NULL DEFAULT '0',
  `Quatity` int(11) NOT NULL,
  PRIMARY KEY (`ID_Bill`,`ID_Product`),
  KEY `fk_detail_product` (`ID_Product`),
  CONSTRAINT `fk_detail_bill` FOREIGN KEY (`ID_Bill`) REFERENCES `bill` (`ID_Bill`),
  CONSTRAINT `fk_detail_product` FOREIGN KEY (`ID_Product`) REFERENCES `product` (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `detail_bill`
--

/*!40000 ALTER TABLE `detail_bill` DISABLE KEYS */;
INSERT INTO `detail_bill` (`ID_Bill`,`ID_Product`,`Quatity`) VALUES 
 (6,4,120),
 (7,4,100);
/*!40000 ALTER TABLE `detail_bill` ENABLE KEYS */;


--
-- Definition of table `detail_invoice`
--

DROP TABLE IF EXISTS `detail_invoice`;
CREATE TABLE `detail_invoice` (
  `ID_Invoice` bigint(20) NOT NULL DEFAULT '0',
  `ID_Product` bigint(20) unsigned NOT NULL DEFAULT '0',
  `Quatity` int(11) NOT NULL,
  `PriceBuy` decimal(20,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_Invoice`,`ID_Product`),
  KEY `fk_detail_invoice_product` (`ID_Product`),
  CONSTRAINT `detail_invoice_ibfk_1` FOREIGN KEY (`ID_Invoice`) REFERENCES `invoice` (`ID_Invoice`),
  CONSTRAINT `fk_detail_invoice` FOREIGN KEY (`ID_Invoice`) REFERENCES `invoice` (`ID_Invoice`),
  CONSTRAINT `fk_detail_invoice_product` FOREIGN KEY (`ID_Product`) REFERENCES `product` (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `detail_invoice`
--

/*!40000 ALTER TABLE `detail_invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `detail_invoice` ENABLE KEYS */;


--
-- Definition of table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `ID_Invoice` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `Companyid` bigint(20) NOT NULL DEFAULT '0',
  `DateModified` datetime NOT NULL,
  `Total` decimal(20,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_Invoice`),
  KEY `fk_invoice_customer` (`customer_id`),
  KEY `FK_invoice_supplier` (`Companyid`),
  CONSTRAINT `FK_invoice_supplier` FOREIGN KEY (`Companyid`) REFERENCES `supplier` (`CompanyId`),
  CONSTRAINT `fk_invoice_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `invoice`
--

/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` (`ID_Invoice`,`customer_id`,`Companyid`,`DateModified`,`Total`) VALUES 
 (2,12,2,'2019-09-16 00:00:00','12000000');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;


--
-- Definition of table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `PRODUCT_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `PRODUCT_NAME` longtext NOT NULL,
  `PRODUCT_QUANTITY` int(10) unsigned NOT NULL,
  `PRODUCT_PRICE` decimal(20,0) NOT NULL,
  `PRODUCT_MAINIMG` longtext,
  `PRODUCT_DESCRIPTION` longtext NOT NULL,
  `PRODUCT_CATEGORY_ID` int(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`PRODUCT_ID`),
  KEY `fk_product_category` (`PRODUCT_CATEGORY_ID`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`PRODUCT_CATEGORY_ID`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`PRODUCT_ID`,`PRODUCT_NAME`,`PRODUCT_QUANTITY`,`PRODUCT_PRICE`,`PRODUCT_MAINIMG`,`PRODUCT_DESCRIPTION`,`PRODUCT_CATEGORY_ID`) VALUES 
 (4,'',0,'0','img32.png','sadfsakfasjfkasjfaskfajsfk',8);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


--
-- Definition of table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
CREATE TABLE `ratings` (
  `id_product` bigint(20) unsigned NOT NULL DEFAULT '0',
  `Title` varchar(100) NOT NULL,
  `customer_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `Rating` int(11) NOT NULL,
  `Content` varchar(200) NOT NULL,
  PRIMARY KEY (`id_product`,`Title`,`customer_id`),
  KEY `fk_customer_rating` (`customer_id`),
  CONSTRAINT `FK_ratings_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`PRODUCT_ID`),
  CONSTRAINT `fk_customer_rating` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ratings`
--

/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;


--
-- Definition of table `reponse_comment`
--

DROP TABLE IF EXISTS `reponse_comment`;
CREATE TABLE `reponse_comment` (
  `ID_Comment` bigint(20) NOT NULL DEFAULT '0',
  `customer_id` bigint(20) unsigned zerofill NOT NULL DEFAULT '00000000000000000000',
  `Content` varchar(200) NOT NULL,
  `DateModified` datetime NOT NULL,
  PRIMARY KEY (`ID_Comment`,`customer_id`,`DateModified`),
  KEY `reponse_comment_ibfk_2` (`customer_id`),
  CONSTRAINT `fk_reponse_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ID`),
  CONSTRAINT `reponse_comment_ibfk_1` FOREIGN KEY (`ID_Comment`) REFERENCES `comment` (`ID_Comment`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reponse_comment_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reponse_comment`
--

/*!40000 ALTER TABLE `reponse_comment` DISABLE KEYS */;
INSERT INTO `reponse_comment` (`ID_Comment`,`customer_id`,`Content`,`DateModified`) VALUES 
 (1,00000000000000000012,'jmay muon diajfiadf','2019-09-16 00:00:00');
/*!40000 ALTER TABLE `reponse_comment` ENABLE KEYS */;


--
-- Definition of table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `CompanyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `CompanyName` varchar(100) NOT NULL,
  `Address` varchar(150) NOT NULL,
  `PhoneNumber` varchar(13) NOT NULL,
  `FaxNumber` varchar(13) NOT NULL,
  PRIMARY KEY (`CompanyId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `supplier`
--

/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` (`CompanyId`,`CompanyName`,`Address`,`PhoneNumber`,`FaxNumber`) VALUES 
 (2,'adfsafdsdfsafsafsa','sdfsafafsafafafs','0932688659','asdf');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
