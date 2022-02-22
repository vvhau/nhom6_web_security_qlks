CREATE DATABASE  IF NOT EXISTS `ltw_qlks_demo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ltw_qlks_demo`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: ltw_qlks_demo
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id_booking` int NOT NULL AUTO_INCREMENT,
  `so_nguoi` int DEFAULT NULL,
  `check_in` date NOT NULL,
  `check_out` date NOT NULL,
  `dat_online` tinyint(1) DEFAULT NULL,
  `id_nguoi_dat` int DEFAULT NULL,
  `id_HD` int DEFAULT NULL,
  `id_phong` int NOT NULL,
  PRIMARY KEY (`id_booking`),
  KEY `id_nguoi_dat` (`id_nguoi_dat`),
  KEY `id_HD` (`id_HD`),
  KEY `id_phong` (`id_phong`),
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`id_nguoi_dat`) REFERENCES `user` (`id`),
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`id_HD`) REFERENCES `hoa_don` (`id_HD`),
  CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`id_phong`) REFERENCES `phong` (`id_phong`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (14,1,'2021-12-19','2021-12-22',1,17,NULL,7);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoa_don`
--

DROP TABLE IF EXISTS `hoa_don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoa_don` (
  `id_HD` int NOT NULL AUTO_INCREMENT,
  `ngay_tao` datetime DEFAULT NULL,
  `id_user` int NOT NULL,
  PRIMARY KEY (`id_HD`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `hoa_don_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don`
--

LOCK TABLES `hoa_don` WRITE;
/*!40000 ALTER TABLE `hoa_don` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoa_don` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khach_hang`
--

DROP TABLE IF EXISTS `khach_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khach_hang` (
  `id_khach` int NOT NULL AUTO_INCREMENT,
  `ho_ten` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cmnd` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dia_chi` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `id_booking` int DEFAULT NULL,
  PRIMARY KEY (`id_khach`),
  UNIQUE KEY `cmnd` (`cmnd`),
  UNIQUE KEY `_uni_customer_in_booking` (`cmnd`,`id_booking`),
  KEY `id_booking` (`id_booking`),
  CONSTRAINT `khach_hang_ibfk_1` FOREIGN KEY (`id_booking`) REFERENCES `booking` (`id_booking`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khach_hang`
--

LOCK TABLES `khach_hang` WRITE;
/*!40000 ALTER TABLE `khach_hang` DISABLE KEYS */;
INSERT INTO `khach_hang` VALUES (9,'1','1','1',14);
/*!40000 ALTER TABLE `khach_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_phong`
--

DROP TABLE IF EXISTS `loai_phong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loai_phong` (
  `id_loai_phong` int NOT NULL AUTO_INCREMENT,
  `ten_loai_phong` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `hinh_anh` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `don_gia` float DEFAULT NULL,
  `so_nguoi` int DEFAULT NULL,
  `ghi_chu` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_loai_phong`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_phong`
--

LOCK TABLES `loai_phong` WRITE;
/*!40000 ALTER TABLE `loai_phong` DISABLE KEYS */;
INSERT INTO `loai_phong` VALUES (1,'Giường đơn','https://www.hotelgrandsaigon.com/wp-content/uploads/sites/227/2017/12/GRAND_DLXK_01.jpg',200000,2,'phòng rộng thoáng mát'),(2,'Giường đơn lớn','https://vinapad.com/wp-content/uploads/2019/01/Phong-ngu-khach-san-mini.jpg',250000,3,''),(3,'Giường đôi','https://ezcloud.vn/wp-content/uploads/2019/07/4649_abc-1.jpg',300000,4,''),(5,'Giường đôi lớn','https://www.vietnambooking.com/wp-content/uploads/2018/06/co-nhung-loai-phong-khach-san-nao-02-06-18-6.jpg',500000,5,'rong');
/*!40000 ALTER TABLE `loai_phong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong`
--

DROP TABLE IF EXISTS `phong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phong` (
  `id_phong` int NOT NULL AUTO_INCREMENT,
  `ten_phong` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `id_loai_phong` int NOT NULL,
  `id_trang_thai` int NOT NULL,
  PRIMARY KEY (`id_phong`),
  KEY `id_loai_phong` (`id_loai_phong`),
  KEY `id_trang_thai` (`id_trang_thai`),
  CONSTRAINT `phong_ibfk_1` FOREIGN KEY (`id_loai_phong`) REFERENCES `loai_phong` (`id_loai_phong`),
  CONSTRAINT `phong_ibfk_2` FOREIGN KEY (`id_trang_thai`) REFERENCES `trang_thai` (`id_trang_thai`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong`
--

LOCK TABLES `phong` WRITE;
/*!40000 ALTER TABLE `phong` DISABLE KEYS */;
INSERT INTO `phong` VALUES (1,'101',1,3),(2,'102',2,1),(3,'103',3,1),(4,'201',1,1),(5,'202',2,3),(6,'203',3,1),(7,'301',1,2),(8,'302',2,1),(9,'303',3,1);
/*!40000 ALTER TABLE `phong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen`
--

DROP TABLE IF EXISTS `quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quyen` (
  `id_quyen` int NOT NULL AUTO_INCREMENT,
  `ten_quyen` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_quyen`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen`
--

LOCK TABLES `quyen` WRITE;
/*!40000 ALTER TABLE `quyen` DISABLE KEYS */;
INSERT INTO `quyen` VALUES (1,'admin'),(2,'employee'),(3,'customer');
/*!40000 ALTER TABLE `quyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trang_thai`
--

DROP TABLE IF EXISTS `trang_thai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trang_thai` (
  `id_trang_thai` int NOT NULL AUTO_INCREMENT,
  `ten_trang_thai` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_trang_thai`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trang_thai`
--

LOCK TABLES `trang_thai` WRITE;
/*!40000 ALTER TABLE `trang_thai` DISABLE KEYS */;
INSERT INTO `trang_thai` VALUES (1,'Còn trống'),(2,'Đang ở'),(3,'Đang sửa');
/*!40000 ALTER TABLE `trang_thai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ho_ten` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `gioi_tinh` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cmnd` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sdt` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ten_dang_nhap` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `mat_khau` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `kich_hoat` tinyint(1) DEFAULT NULL,
  `id_quyen` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `sdt` (`sdt`),
  UNIQUE KEY `ten_dang_nhap` (`ten_dang_nhap`),
  UNIQUE KEY `cmnd_UNIQUE` (`cmnd`),
  KEY `id_quyen` (`id_quyen`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`id_quyen`) REFERENCES `quyen` (`id_quyen`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','2001-03-15','Nam','215000009','admin@gmail.com','0943234567','admin','21232f297a57a5a743894a0e4a801fc3',1,1),(16,'Khách Hàng 1','1998-06-18','Nam','212121212','kh1@gmail.com','0265144648','kh1','21fe13b92c973486e1024fad211785f',1,3),(17,'Google','2021-11-17','Nam','2121212122','kh2@gmail.com','','kh2','d41d8cd98f00b204e9800998ecf8427e',1,3),(31,'Nguyễn Văn An','2021-12-15','Nam','2121','kh3@gmail.com','0965937447','kh3','2ea1e9165f641ad86f64cceb65d1f51a',1,3),(33,'nv1','2021-12-14','Nữ','215504384','nv1@gmail.com','0936483772','nv1','b81fbabe373a8a0a80df5da5602e702e',1,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-19 20:05:36
