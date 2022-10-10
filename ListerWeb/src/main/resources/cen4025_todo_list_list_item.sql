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
-- Table structure for table `todo_list_list_item`
--

DROP TABLE IF EXISTS `todo_list_list_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `todo_list_list_item` (
  `ToDoList_todo_list_id` int NOT NULL,
  `list_item_id` int DEFAULT NULL,
  `listItems_list_item_id` int NOT NULL,
  PRIMARY KEY (`ToDoList_todo_list_id`,`listItems_list_item_id`),
  KEY `fk_list_item_idx` (`listItems_list_item_id`),
  CONSTRAINT `fk_list_item` FOREIGN KEY (`listItems_list_item_id`) REFERENCES `list_item` (`LIST_ITEM_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_todo_list_id` FOREIGN KEY (`ToDoList_todo_list_id`) REFERENCES `todo_list` (`TODO_LIST_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo_list_list_item`
--

LOCK TABLES `todo_list_list_item` WRITE;
/*!40000 ALTER TABLE `todo_list_list_item` DISABLE KEYS */;
INSERT INTO `todo_list_list_item` (`ToDoList_todo_list_id`, `list_item_id`, `listItems_list_item_id`) VALUES (1,0,3),(2,0,11),(6,0,4),(6,1,6),(6,2,8),(7,0,5),(10,0,13),(10,1,14);
/*!40000 ALTER TABLE `todo_list_list_item` ENABLE KEYS */;
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
