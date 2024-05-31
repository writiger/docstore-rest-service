/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : ds_comment

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 01/06/2024 06:46:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '0-未删除\r\n1-已删除',
  `doc_uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parent_id` int NULL DEFAULT NULL,
  `root_parent_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 'You must be the change you wish to see in the world.', 20, 'Cheng Zhennan', '2012-10-22 12:45:07', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 0, 0);
INSERT INTO `comment` VALUES (2, 'Such sessions are also susceptible to session hijacking, where a malicious user takes               ', 47, 'Marjorie Roberts', '2021-09-08 12:01:02', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 0, 0);
INSERT INTO `comment` VALUES (3, 'All journeys have secret destinations of which the traveler is unaware.', 64, 'Shao Anqi', '2018-03-08 15:43:07', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 0, 0);
INSERT INTO `comment` VALUES (4, 'A man is not old until regrets take the place of dreams. A man is not old until regrets             ', 104, 'Jesse Fisher', '2021-07-29 00:51:56', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 1, 1);
INSERT INTO `comment` VALUES (5, 'Champions keep playing until they get it right. The first step is as good as half over.', 107, 'lmy', '2021-10-26 08:44:35', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 1, 1);
INSERT INTO `comment` VALUES (6, 'Navicat Data Modeler enables you to build high-quality conceptual, logical and physical             ', 75, 'Roy Soto', '2021-09-13 11:14:30', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 4, 1);
INSERT INTO `comment` VALUES (7, 'It wasn’t raining when Noah built the ark. If the plan doesn’t work, change the                 ', 54, 'Stephen Turner', '2021-01-30 15:09:17', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 6, 1);
INSERT INTO `comment` VALUES (8, 'In other words, Navicat provides the ability for data in different databases and/or                 ', 43, 'Huang Zitao', '2016-10-15 08:31:49', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 2, 2);
INSERT INTO `comment` VALUES (9, 'It collects process metrics such as CPU load, RAM usage, and a variety of other resources           ', 105, 'Harry Cruz', '2003-11-07 12:11:42', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 2, 2);
INSERT INTO `comment` VALUES (10, 'You can select any connections, objects or projects, and then select the corresponding              ', 31, 'Edwin Rodriguez', '2001-01-27 23:58:39', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 9, 2);
INSERT INTO `comment` VALUES (12, '@Marjorie Roberts:test', 3, 'writiger', '2024-05-22 19:48:29', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 2, 1);
INSERT INTO `comment` VALUES (13, '@Cheng Zhennan:test', 4, 'user2', '2024-05-29 18:53:56', 0, 'uGPDNMGkTaqqh4Pyv2Gm1g', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
