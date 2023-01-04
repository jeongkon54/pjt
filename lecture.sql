-- --------------------------------------------------------
-- 호스트:                          localhost
-- 서버 버전:                        10.10.2-MariaDB-1:10.10.2+maria~ubu2204 - mariadb.org binary distribution
-- 서버 OS:                        debian-linux-gnu
-- HeidiSQL 버전:                  12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- pjt 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `pjt` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci */;
USE `pjt`;

-- 테이블 pjt.hall 구조 내보내기
CREATE TABLE IF NOT EXISTS `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity_people` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `hall_name` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `lecture_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp706tqi2pn651xn4et3edyw7e` (`lecture_id`),
  CONSTRAINT `FKp706tqi2pn651xn4et3edyw7e` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 pjt.hall:~1 rows (대략적) 내보내기
INSERT INTO `hall` (`id`, `capacity_people`, `create_date`, `end_time`, `hall_name`, `start_time`, `update_date`, `lecture_id`) VALUES
	(1, 3, '2023-01-02 14:12:14', '2023-01-02 16:00:00', 'KBS홀', '2023-01-02 15:00:00', '2023-01-02 14:12:14', 1);

-- 테이블 pjt.lecture 구조 내보내기
CREATE TABLE IF NOT EXISTS `lecture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `lecture_content` text DEFAULT NULL,
  `lecture_hall` varchar(255) DEFAULT NULL,
  `lecturer` varchar(255) DEFAULT NULL,
  `sign_up_people` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 pjt.lecture:~2 rows (대략적) 내보내기
INSERT INTO `lecture` (`id`, `create_date`, `end_time`, `lecture_content`, `lecture_hall`, `lecturer`, `sign_up_people`, `start_time`, `update_date`) VALUES
	(1, '2023-01-02 14:18:36', '2023-01-02 00:00:00', '심리학 내용입니다.', 'KBS홀', '이박사', 2, '2023-01-02 00:00:00', '2023-01-03 14:35:15'),
	(2, '2023-01-03 12:51:02', '2023-01-06 00:00:00', '가정의학 내용입니다.', 'KBS홀', '이의사', 1, '2023-01-06 00:00:00', '2023-01-03 12:51:02');

-- 테이블 pjt.lecture_apply 구조 내보내기
CREATE TABLE IF NOT EXISTS `lecture_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `stats` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `lecture_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrp7q1jaosmflkritf37rw0wl9` (`lecture_id`),
  KEY `FK984p498rxo46vg5m0ohy0qb7k` (`user_id`),
  CONSTRAINT `FK984p498rxo46vg5m0ohy0qb7k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrp7q1jaosmflkritf37rw0wl9` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 pjt.lecture_apply:~4 rows (대략적) 내보내기
INSERT INTO `lecture_apply` (`id`, `create_date`, `stats`, `update_date`, `lecture_id`, `user_id`) VALUES
	(3, '2023-01-03 13:24:52', 'A', '2023-01-03 13:24:52', 1, 5),
	(5, '2023-01-03 13:29:44', 'C', '2023-01-03 14:30:34', 1, 4),
	(9, '2023-01-03 14:14:04', 'A', '2023-01-03 14:14:04', 1, 2),
	(10, '2023-01-03 14:34:48', 'C', '2023-01-03 14:35:15', 1, 7);

-- 테이블 pjt.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 테이블 데이터 pjt.user:~7 rows (대략적) 내보내기
INSERT INTO `user` (`id`, `create_date`, `gender`, `update_date`, `user_name`, `user_number`) VALUES
	(1, '2023-01-02 14:06:04', 'M', '2023-01-02 14:06:04', '이승기', '2301A'),
	(2, '2023-01-02 14:06:28', 'F', '2023-01-02 14:06:28', '김슬기', '2301B'),
	(3, '2023-01-02 14:06:31', 'M', '2023-01-02 14:06:31', '김날길', '2301C'),
	(4, '2023-01-02 14:06:37', 'F', '2023-01-02 14:06:37', '아이유', '2301D'),
	(5, '2023-01-02 14:06:43', 'M', '2023-01-02 14:06:43', '남궁민', '2301E'),
	(6, '2023-01-02 14:06:47', 'F', '2023-01-02 14:06:47', '이효리', '2301F'),
	(7, '2023-01-02 23:28:51', 'M', '2023-01-02 23:28:51', '이상순', '2301G');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
