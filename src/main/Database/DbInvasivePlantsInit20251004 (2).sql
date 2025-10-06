-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: invasiveplants
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `location_id` bigint NOT NULL AUTO_INCREMENT,
  `latitude` decimal(9,6) NOT NULL,
  `longitude` decimal(9,6) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  UNIQUE KEY `location_id_UNIQUE` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plants`
--

DROP TABLE IF EXISTS `plants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plants` (
  `plant_id` bigint NOT NULL AUTO_INCREMENT,
  `photo_before` varchar(512) DEFAULT NULL,
  `photo_after` varchar(512) DEFAULT NULL,
  `status` enum('REGISTERED','REMOVED','VERIFIED') NOT NULL DEFAULT 'REGISTERED',
  `species_id` bigint DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  `removed_by_user_id` bigint DEFAULT NULL,
  `reported_by_user_id` bigint DEFAULT NULL,
  `date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`plant_id`),
  UNIQUE KEY `plant-id_UNIQUE` (`plant_id`),
  KEY `fk_plants_location` (`location_id`),
  KEY `fk_plants_remover` (`removed_by_user_id`),
  KEY `fk_plants_reporter` (`reported_by_user_id`),
  KEY `fk_plants_species` (`species_id`),
  CONSTRAINT `fk_plants_location` FOREIGN KEY (`location_id`) REFERENCES `locations` (`location_id`),
  CONSTRAINT `fk_plants_remover` FOREIGN KEY (`removed_by_user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_plants_reporter` FOREIGN KEY (`reported_by_user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `fk_plants_species` FOREIGN KEY (`species_id`) REFERENCES `species` (`species_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plants`
--

LOCK TABLES `plants` WRITE;
/*!40000 ALTER TABLE `plants` DISABLE KEYS */;
INSERT INTO `plants` VALUES (1,'before1.jpg',NULL,'REGISTERED',1,1,NULL,1,'2025-10-01 10:00:00'),(2,'before2.jpg',NULL,'REGISTERED',2,2,NULL,2,'2025-10-02 11:30:00'),(3,'before3.jpg','after3.jpg','REMOVED',3,3,3,3,'2025-10-03 12:00:00'),(4,'before4.jpg',NULL,'REGISTERED',4,4,NULL,4,'2025-10-04 13:20:00'),(5,'before5.jpg','after5.jpg','VERIFIED',5,5,5,5,'2025-10-05 14:45:00');
/*!40000 ALTER TABLE `plants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rewards`
--

DROP TABLE IF EXISTS `rewards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rewards` (
  `reward_id` bigint NOT NULL AUTO_INCREMENT,
  `points` int NOT NULL,
  `reward-depending-on-points` int DEFAULT NULL,
  `description` mediumtext NOT NULL,
  PRIMARY KEY (`reward_id`),
  UNIQUE KEY `reward-id_UNIQUE` (`reward_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rewards`
--

LOCK TABLES `rewards` WRITE;
/*!40000 ALTER TABLE `rewards` DISABLE KEYS */;
INSERT INTO `rewards` VALUES (1,100,10,'Belöning för 10 poäng'),(2,200,20,'Belöning för 20 poäng');
/*!40000 ALTER TABLE `rewards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `admin` varchar(45) NOT NULL,
  `user` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role-id_UNIQUE` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `species`
--

DROP TABLE IF EXISTS `species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `species` (
  `species_id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` longtext NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `biological_charecteristics` longtext NOT NULL,
  `plant-handling` longtext NOT NULL,
  `photo` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`species_id`),
  UNIQUE KEY `species_id_UNIQUE` (`species_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `species`
--

LOCK TABLES `species` WRITE;
/*!40000 ALTER TABLE `species` DISABLE KEYS */;
INSERT INTO `species` VALUES (1,'Impatiens glandulifera','Jättebalsamin, mycket spridd i fuktiga områden','INVASIVE','Väldigt spridningsvillig, stor frömängd','Ryck upp innan fröbildning','url_to_photo1'),(2,'Heracleum mantegazzianum','Jätteloka kan orsaka brännskador på hud','INVASIVE','Stora blomställningar, saft farlig i solljus','Skyddsutrustning och professionell hantering','url_to_photo2'),(3,'Lysichiton americanus','Gul skunkkalla, växer i våtmarker','INVASIVE','Stor växt med ljusblommor','Gräv upp rotstockar','url_to_photo3'),(4,'Rosa rugosa','Vresros, sprider sig med rotskott','INVASIVE','Buske med taggar, tålig mot salt','Gräv upp rotskott, klipp tillbaka','url_to_photo4'),(5,'Solidago canadensis','Kanadensiskt gullris, konkurrerar ut inhemska arter','INVASIVE','Produktion av ämnen som hämmar andra växter','Mekanisk bekämpning, täckning','url_to_photo5');
/*!40000 ALTER TABLE `species` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_rewards`
--

DROP TABLE IF EXISTS `user_rewards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_rewards` (
  `user_id` bigint NOT NULL,
  `reward_id` bigint NOT NULL,
  PRIMARY KEY (`reward_id`,`user_id`),
  UNIQUE KEY `user-id_UNIQUE` (`user_id`),
  UNIQUE KEY `reward-id_UNIQUE` (`reward_id`),
  CONSTRAINT `fk_user_rewards_reward` FOREIGN KEY (`reward_id`) REFERENCES `rewards` (`reward_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_rewards_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_rewards`
--

LOCK TABLES `user_rewards` WRITE;
/*!40000 ALTER TABLE `user_rewards` DISABLE KEYS */;
INSERT INTO `user_rewards` VALUES (3,1),(5,2);
/*!40000 ALTER TABLE `user_rewards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `first-name` varchar(100) NOT NULL,
  `last-name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phonenumber` varchar(50) NOT NULL,
  `points` int DEFAULT '0',
  `role_id` bigint NOT NULL,
  `plant_id` bigint DEFAULT NULL,
  `rating` int DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phonenumber_UNIQUE` (`phonenumber`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `fk_users_role_id_idx` (`role_id`),
  KEY `fk_users_plant_id_idx` (`plant_id`),
  CONSTRAINT `fk_users_plant_id` FOREIGN KEY (`plant_id`) REFERENCES `plants` (`plant_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Alice','Andersson','alice@example.com','password1','0701112222',0,2,NULL,0),(2,'Bob','Bengtsson','bob@example.com','password2','0702223333',0,2,NULL,0),(3,'Carina','Carlsson','carina@example.com','password3','0703334444',0,2,NULL,0),(4,'David','Dahl','david@example.com','password4','0704445555',0,2,NULL,0),(5,'Eva','Ek','eva@example.com','password5','0705556666',0,2,NULL,0);
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

-- Dump completed on 2025-10-06 11:33:11
