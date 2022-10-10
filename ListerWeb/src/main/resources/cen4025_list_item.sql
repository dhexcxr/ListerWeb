-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cen4025
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `list_item`
--

DROP TABLE IF EXISTS `list_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `list_item` (
  `LIST_ITEM_ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `CREATED_DATE` timestamp(6) NOT NULL,
  `DONE` bit(1) DEFAULT NULL,
  `FINISHED_DATE` datetime DEFAULT NULL,
  `TODO_LIST_ITEM_NUM` int NOT NULL,
  PRIMARY KEY (`LIST_ITEM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_item`
--

LOCK TABLES `list_item` WRITE;
/*!40000 ALTER TABLE `list_item` DISABLE KEYS */;
INSERT INTO `list_item` (`LIST_ITEM_ID`, `NAME`, `CREATED_DATE`, `DONE`, `FINISHED_DATE`, `TODO_LIST_ITEM_NUM`) VALUES (3,'Dude23','2022-09-27 20:57:53.202000',_binary '','2022-10-08 00:16:23',1),(4,'What would you have me do?','2022-09-27 22:06:07.028000',_binary '','2022-09-27 18:35:05',1),(5,'The best list!','2022-09-27 22:08:26.877000',_binary '','2022-09-27 18:08:39',1),(6,'Become a fisher of men.','2022-10-07 20:31:46.717000',_binary '','2022-10-08 00:16:53',2),(8,'Do the thing.','2022-10-07 20:37:34.220000',_binary '','2022-10-08 09:16:49',4),(11,'DD fas!','2022-10-08 18:23:33.229000',_binary '','2022-10-08 14:23:42',1),(13,'Learn how to write functions','2022-10-10 02:17:11.038000',_binary '','2022-10-09 23:17:19',1),(14,'figure out how to call external JS','2022-10-10 02:30:15.244000',_binary '','2022-10-09 23:22:34',2);
/*!40000 ALTER TABLE `list_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-09 23:33:23
