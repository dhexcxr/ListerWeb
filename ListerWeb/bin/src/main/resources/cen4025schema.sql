CREATE DATABASE  IF NOT EXISTS `cen4025` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cen4025`;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `todo_list`
--

DROP TABLE IF EXISTS `todo_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `todo_list` (
  `TODO_LIST_ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `CREATED_DATE` timestamp(6) NOT NULL,
  PRIMARY KEY (`TODO_LIST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-27 17:21:56
