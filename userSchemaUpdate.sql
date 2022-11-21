-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: myhouse
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `SEQ` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `PROFILE_IMAGE_URL` varchar(500) DEFAULT '/no_img.png',
  `REG_DT` datetime DEFAULT NULL,
  `code` char(3) NOT NULL DEFAULT '002',
  `TOKEN` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`SEQ`),
  UNIQUE KEY `USER_EMAIL_UNIQUE` (`EMAIL`),
  KEY `FK_CODE_USERS_idx` (`code`),
  CONSTRAINT `FK_CODE_USERS` FOREIGN KEY (`code`) REFERENCES `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'test','1234','test','no_profileImg.png','2022-11-17 00:00:00','001','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjY4OTM1Mjg5MjkwLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzAxNDQ4ODksInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyRW1haWwiOiJ0ZXN0In0.sXAhHJ-8zqHHleoICBh9GPHQbOaB9cP-RsuOZH699qM'),(2,'test','1234','test2','/no_img.png','2022-11-17 00:00:00','001',NULL),(5,'test','1234','test3','/no_img.png','2022-11-17 00:00:00','001',NULL),(6,'안자몽','','test@ssafy.com','/no_img.png','2022-11-18 00:00:00','002',NULL),(7,'안자몽이','q1w2e3r4@','test@test.com','/no_img.png','2022-11-18 00:00:00','001',NULL),(8,'안자몽이3','q1w2e3r4@','q@qqq.com','/no_img.png','2022-11-18 00:00:00','001',NULL),(9,'안자몽이','q1w2e3r4@','zamong@ssafy.com','/no_img.png','2022-11-18 00:00:00','001',NULL),(10,'안자몽이','q1w2e3r4@','test2@ssafy.com','/no_img.png','2022-11-18 00:00:00','001',NULL),(13,'안자몽쿤','q1w2e3r4@','qqqq@qqqq.com','/no_img.png','2022-11-18 00:00:00','001',NULL),(14,'유저몽','q1w2e3r4@','ssafy@ssafy.com','/no_img.png','2022-11-18 00:00:00','002',NULL),(15,'아기자몽','q1w2e3r4@','baby@zamong.com','/no_img.png','2022-11-18 00:00:00','002',NULL),(16,'테스트','qwer1234@','test@my.com','/no_img.png','2022-11-22 00:27:19','002',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-22  1:53:26
