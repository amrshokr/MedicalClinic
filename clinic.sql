CREATE DATABASE  IF NOT EXISTS `clinic` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `clinic`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: clinic
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
-- Table structure for table `medical_appiontement`
--

DROP TABLE IF EXISTS `medical_appiontement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_appiontement` (
  `APPIONTEMENT_ID` int NOT NULL AUTO_INCREMENT,
  `PATIENT_ID` int DEFAULT NULL,
  `DOCTOR_ID` int DEFAULT NULL,
  `APPIONTEMENT_DATE` datetime DEFAULT NULL,
  `ISACTIVE` varchar(3) DEFAULT NULL,
  `REASON` varchar(200) DEFAULT NULL,
  `EMPLOYEE_ID` int DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` int DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`APPIONTEMENT_ID`),
  UNIQUE KEY `APPIONTEMENT_ID_UNIQUE` (`APPIONTEMENT_ID`),
  KEY `MEDICAL_APPIONTEMENT_FK1_idx` (`PATIENT_ID`),
  KEY `MEDICAL_APPIONTEMENT_FK2_idx` (`DOCTOR_ID`),
  KEY `MEDICAL_APPIONTEMENT_FK3_idx` (`EMPLOYEE_ID`),
  CONSTRAINT `MEDICAL_APPIONTEMENT_FK1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `medical_patient` (`PATIENT_ID`),
  CONSTRAINT `MEDICAL_APPIONTEMENT_FK2` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `medical_doctor` (`DOCTOR_ID`),
  CONSTRAINT `MEDICAL_APPIONTEMENT_FK3` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `medical_employee` (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medical_doctor`
--

DROP TABLE IF EXISTS `medical_doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_doctor` (
  `DOCTOR_ID` int NOT NULL AUTO_INCREMENT,
  `DOCTOR_NAME` varchar(50) DEFAULT NULL,
  `DOCTOR_PHONE` varchar(20) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`DOCTOR_ID`),
  UNIQUE KEY `DOCTOR_ID_UNIQUE` (`DOCTOR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medical_employee`
--

DROP TABLE IF EXISTS `medical_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_employee` (
  `EMPLOYEE_ID` int NOT NULL AUTO_INCREMENT,
  `EMPLOYEE_NAME` varchar(255) DEFAULT NULL,
  `EMPLOYEE_PHONE` varchar(255) DEFAULT NULL,
  `ROLE_ID` int DEFAULT NULL,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medical_patient`
--

DROP TABLE IF EXISTS `medical_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_patient` (
  `PATIENT_ID` int NOT NULL AUTO_INCREMENT,
  `PATIENT_NAME` varchar(100) DEFAULT NULL,
  `PATIENT_PHONE` varchar(45) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`PATIENT_ID`),
  UNIQUE KEY `PATIENT_ID_UNIQUE` (`PATIENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-20  5:52:52
