/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : ds_doc

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 01/06/2024 06:46:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for doc
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `theme` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `digest` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `keyword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `suffix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `belong` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doc
-- ----------------------------
INSERT INTO `doc` VALUES (1, '123', '123', '作者1;作者2;作者3', '123', '123', '123', 'dlnu');
INSERT INTO `doc` VALUES (2, 'wB1okvPnSNueEp3PYz5ZZA', '1', '作者1;作者2;作者3', '3', '4', '.jpg', 'dlnu');
INSERT INTO `doc` VALUES (3, 'uGPDNMGkTaqqh4Pyv2Gm1g', 'test', '作者1;作者2;作者33', 'test', 'test', '.jpg', 'dlnu');
INSERT INTO `doc` VALUES (4, '1x8hulAZRlmKpE+eoPaAwA', 'test', '作者1;作者2;作者3', 'test', 'test', '.jpg', 'oxford');
INSERT INTO `doc` VALUES (5, 'rs++VPxsR+u7hsWeQB3yrg', 'oxford', 'oxford', 'oxford', 'oxford', '.jpg', 'oxford');

SET FOREIGN_KEY_CHECKS = 1;
