/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : ds_belong

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 01/06/2024 06:45:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for belong
-- ----------------------------
DROP TABLE IF EXISTS `belong`;
CREATE TABLE `belong`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of belong
-- ----------------------------
INSERT INTO `belong` VALUES (1, 'DLNU', '大连民族大学');
INSERT INTO `belong` VALUES (2, 'Oxford', '潘嘉伦');
INSERT INTO `belong` VALUES (3, 'Akron', '钟云熙');
INSERT INTO `belong` VALUES (4, 'Oxford', '薛睿');
INSERT INTO `belong` VALUES (5, 'Akron', '秦岚');
INSERT INTO `belong` VALUES (6, 'Sapporo', '薛杰宏');
INSERT INTO `belong` VALUES (7, 'Shenzhen', '高杰宏');
INSERT INTO `belong` VALUES (8, 'Shanghai', '于杰宏');
INSERT INTO `belong` VALUES (9, 'Columbus', '宋云熙');
INSERT INTO `belong` VALUES (10, 'Chengdu', '周秀英');
INSERT INTO `belong` VALUES (11, 'Beijing', '孙詩涵');

SET FOREIGN_KEY_CHECKS = 1;
