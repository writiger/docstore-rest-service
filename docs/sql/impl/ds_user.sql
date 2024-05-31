/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : ds_user

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 01/06/2024 06:46:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `level` tinyint NOT NULL COMMENT '0-root;\r\n1-admin;\r\n2-normal;',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `belong` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` tinyint NOT NULL COMMENT '0-frozen;\r\n1-normal;',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`, `account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '0000000000000000000000', 'root', 'root@qq.com', 'root', 0, '$2a$12$cx.QxOj46NWF8rfrspJQ3.MGmLmBlNJQDeRlV4W52Y3.pRvjbanK2', 'dlnu', 1, 'url');
INSERT INTO `user` VALUES (3, 'K5ovlnj7TPmNGOnU3+92dQ', 'writiger', '731924080@qq.com', 'writiger', 1, '$2a$12$B6peTT4MFaA2whnCuMhdP.Uy.Ck.C18N6NUc/98nj.1HNHikF0QmO', 'dlnu', 1, 'url');
INSERT INTO `user` VALUES (4, 'OdJoU8WdRCSr3/231L1VLA', 'user', 'user2@qq.com', 'user2', 2, '$2a$10$KBfBndY67diXxZs8rJGldOMwgP256f6veZDl1wA4c8jy2uuUEkJwm', 'oxford', 1, 'url');
INSERT INTO `user` VALUES (5, 'GT7oaC35QYWd37KAHNwZuw', 'user3', 'user3@qq.com', 'user3', 2, '$2a$10$KBfBndY67diXxZs8rJGldOMwgP256f6veZDl1wA4c8jy2uuUEkJwm', 'dlnu', 1, 'url');
INSERT INTO `user` VALUES (6, 'KZFdDnSaTZy3A9mjZo+Y9Q', 'user1', 'user1@qq.com', 'root2', 2, '$2a$2a$12$uu3W71ulkrnhlc2KNTN6texLxgmuXOO9Db8AudlX46bKkYWJlnSU6', 'dlnu', 1, 'url');
INSERT INTO `user` VALUES (7, 'dbcb594b-1773-3d18-ad12-7766df763eb4', 'yuananqi', 'anqiy411@hotmail.com', 'Yuan Anqi', 0, '2a10yMlNkqH3kK2Txyh1RVNU1LPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 0, 'url');
INSERT INTO `user` VALUES (8, '5947c1a0-552d-71bf-15e5-25fe186c3f3c', 'earlwebb', 'webbearl6@gmail.com', 'Earl Webb', 2, '2a10yMlNkqH3kK2Txyh^RVNU10PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 1, 'url');
INSERT INTO `user` VALUES (9, '74eeaac7-8094-0080-1b97-4f3c4fadf8a9', 'lang', 'langu@gmail.com', 'Gu Lan', 1, '2a10yMlNkqH3kK2TxyhNRVNU1wPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 0, 'url');
INSERT INTO `user` VALUES (10, '9f4da37e-686b-caf5-aa00-ad2f8c8470b4', 'virginia2', 'virginiacru@mail.com', 'Virginia Cruz', 0, '2a10yMlNkqH3kK2Txyh9RVNU1VPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Shenzhen', 0, '');
INSERT INTO `user` VALUES (11, '0d5095e2-bc34-770e-b8d6-824c4a8c8ca4', 'jj1107', 'jjiang6@hotmail.com', 'Jiang Jialun', 0, '2a10yMlNkqH3kK2TxyhIRVNU1CPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Shenzhen', 1, '');
INSERT INTO `user` VALUES (12, 'aeeb120d-7dc8-897a-f337-7b0d997fdcae', 'lei19', 'leixiuying@gmail.com', 'Lei Xiuying', 2, '2a10yMlNkqH3kK2TxyhcRVNU1\\PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 0, '');
INSERT INTO `user` VALUES (13, '7351777c-b93b-8fe8-53b2-efacd8e9f1dd', 'smithjennifer57', 'jennifers1@mail.com', 'Jennifer Smith', 0, '2a10yMlNkqH3kK2TxyhSRVNU1OPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Shenzhen', 0, '');
INSERT INTO `user` VALUES (14, '93afacc9-2c42-e845-4a25-3bf3b57299dd', 'louigarcia1', 'louis1988@gmail.com', 'Louis Garcia', 1, '2a10yMlNkqH3kK2Txyh3RVNU1fPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'DLNU', 1, '');
INSERT INTO `user` VALUES (15, '06ed378a-2deb-d17d-0305-6d8ff5df0958', 'nnorman', 'neno87@outlook.com', 'Norman Nelson', 2, '2a10yMlNkqH3kK2TxyhrRVNU1gPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 1, '');
INSERT INTO `user` VALUES (16, '9af48050-8699-c8f9-099f-d13e93710ebd', 'ye3', 'zitaoye528@icloud.com', 'Ye Zitao', 2, '2a10yMlNkqH3kK2TxyhXRVNU1}PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 1, '');
INSERT INTO `user` VALUES (17, 'ede927e4-6409-b41b-8d94-1f90e4edb4f5', 'yunixion1206', 'xyuni@gmail.com', 'Xiong Yuning', 1, '2a10yMlNkqH3kK2TxyhkRVNU1MPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Columbus', 0, '');
INSERT INTO `user` VALUES (18, '76354840-b572-db8d-7b0e-1b0bf6ebe35c', 'wavince703', 'vincentwall425@gmail.com', 'Vincent Wallace', 2, '2a10yMlNkqH3kK2Txyh:RVNU1KPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 1, '');
INSERT INTO `user` VALUES (19, '3f0e742f-7df9-4d02-4704-4fcea33b26c0', 'rodgonzales', 'rodneygon@outlook.com', 'Rodney Gonzales', 0, '2a10yMlNkqH3kK2Txyh RVNU1YPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 0, '');
INSERT INTO `user` VALUES (20, 'd7718d11-e4c0-82ed-c29f-7ebcd1ac0faa', 'zhennancheng', 'zcheng@gmail.com', 'Cheng Zhennan', 0, '2a10yMlNkqH3kK2TxyhJRVNU1-PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Chengdu', 1, '');
INSERT INTO `user` VALUES (21, '83228a3e-9932-5a08-8e46-bc6dc32abbbb', 'xiuydu', 'duan1987@hotmail.com', 'Duan Xiuying', 0, '2a10yMlNkqH3kK2TxyhzRVNU1HPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Shanghai', 1, '');
INSERT INTO `user` VALUES (22, '58ef26af-4994-efc7-fef8-1ffc7bce8523', 'dunnm', 'dunnmar@outlook.com', 'Maria Dunn', 0, '2a10yMlNkqH3kK2TxyhvRVNU1NPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 0, '');
INSERT INTO `user` VALUES (23, '66f0b7ab-4b43-7d95-ba17-37fd7ba66ce9', 'roflo44', 'florose@gmail.com', 'Florence Rose', 2, '2a10yMlNkqH3kK2TxyhWRVNU1|PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 0, '');
INSERT INTO `user` VALUES (24, 'a539bd8c-038c-d159-dbab-f3d368fc0934', 'lei730', 'anqilei@gmail.com', 'Lei Anqi', 2, '2a10yMlNkqH3kK2Txyh9RVNU1GPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Beijing', 0, '');
INSERT INTO `user` VALUES (25, '52336d76-d1d4-6e06-50d2-fd0f15bc2a62', 'niwood', 'woodnich@outlook.com', 'Nicholas Wood', 2, '2a10yMlNkqH3kK2TxyhJRVNU1rPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 0, '');
INSERT INTO `user` VALUES (26, 'd0d82839-e208-2d7c-d55d-01cb0d3f2936', 'caiy703', 'cai2@gmail.com', 'Cai Yuning', 0, '2a10yMlNkqH3kK2TxyhcRVNU1pPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 1, '');
INSERT INTO `user` VALUES (27, '1430e994-3fb6-f54d-483b-52be5e9c3c7c', 'edwards10', 'shirley4@gmail.com', 'Shirley Edwards', 2, '2a10yMlNkqH3kK2TxyhERVNU1 PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Beijing', 1, '');
INSERT INTO `user` VALUES (28, '582da5e4-6407-e53c-fff6-41d83cf7b958', 'lewisjo615', 'joycele10@gmail.com', 'Joyce Lewis', 0, '2a10yMlNkqH3kK2Txyh7RVNU1MPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 1, '');
INSERT INTO `user` VALUES (29, '24bda98e-b8ba-7b6c-f656-72170d5fb4fd', 'zeng1964', 'yunxiz2@mail.com', 'Zeng Yunxi', 2, '2a10yMlNkqH3kK2Txyh=RVNU1~PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Beijing', 0, '');
INSERT INTO `user` VALUES (30, 'd3078960-7c55-cbe9-7c9e-698e29f2d6a2', 'tjanice', 'jturne2@icloud.com', 'Janice Turner', 0, '2a10yMlNkqH3kK2Txyh&RVNU1CPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Chengdu', 1, '');
INSERT INTO `user` VALUES (31, '1e18167e-a6a4-e843-fef4-2565bc3bce12', 'rodrigueze4', 'rodredwin1@yahoo.com', 'Edwin Rodriguez', 0, '2a10yMlNkqH3kK2TxyhgRVNU1^PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Columbus', 0, '');
INSERT INTO `user` VALUES (32, '3cde5b92-8aba-e267-3a46-8fb94265922c', 'joel14', 'joelmi@gmail.com', 'Joel Miller', 1, '2a10yMlNkqH3kK2Txyh2RVNU1mPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Columbus', 1, '');
INSERT INTO `user` VALUES (33, '55757884-46a6-c3ce-b094-a3bb535e3075', 'leethompson', 'thompsonl6@gmail.com', 'Lee Thompson', 1, '2a10yMlNkqH3kK2TxyhsRVNU1DPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 1, '');
INSERT INTO `user` VALUES (34, 'b9b54bd0-29a1-0ac2-979f-555bbe5c94a8', 'price1', 'pricedonna1209@icloud.com', 'Donna Price', 2, '2a10yMlNkqH3kK2TxyhmRVNU1_PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Beijing', 1, '');
INSERT INTO `user` VALUES (35, '72811f03-3a96-30de-bd31-4bc0a571ed1c', 'jialunyuan411', 'yuajia@hotmail.com', 'Yuan Jialun', 0, '2a10yMlNkqH3kK2Txyh&RVNU1]PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 1, '');
INSERT INTO `user` VALUES (36, 'a281e126-fc59-ec09-5ab4-28bf08e63afa', 'zr3', 'zhu816@icloud.com', 'Zhu Rui', 1, '2a10yMlNkqH3kK2Txyh9RVNU13PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Columbus', 1, '');
INSERT INTO `user` VALUES (37, '29091bf5-9fd3-17ca-8c66-efc3a04a1ec2', 'zengy', 'zeng1@gmail.com', 'Zeng Yuning', 2, '2a10yMlNkqH3kK2Txyh^RVNU1hPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 0, '');
INSERT INTO `user` VALUES (38, '95c7f0f3-6b76-0d20-7b30-3d0cacc07d98', 'tianxiaoming', 'xiaoming608@outlook.com', 'Tian Xiaoming', 2, '2a10yMlNkqH3kK2TxyhjRVNU1UPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Chengdu', 0, '');
INSERT INTO `user` VALUES (39, '0cf195e4-0393-1728-6681-77f455fc412b', 'hunter99', 'hunter75@outlook.com', 'Kenneth Hunter', 1, '2a10yMlNkqH3kK2TxyhsRVNU1+PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 1, '');
INSERT INTO `user` VALUES (40, 'e4a42651-90f1-084c-da00-5689b7d39a49', 'wanjialun415', 'wangji1997@gmail.com', 'Wang Jialun', 1, '2a10yMlNkqH3kK2Txyh1RVNU1YPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 0, '');
INSERT INTO `user` VALUES (41, 'ab28c49f-9a59-aec8-116b-15e22ede70e1', 'weavergregory', 'gregoryweaver@mail.com', 'Gregory Weaver', 2, '2a10yMlNkqH3kK2TxyhkRVNU1OPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 1, '');
INSERT INTO `user` VALUES (42, '71f5f790-3523-4dc5-03b9-b0cb9a7008a2', 'breng9', 'brendagarcia@gmail.com', 'Brenda Garcia', 2, '2a10yMlNkqH3kK2TxyhVRVNU1ZPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Chengdu', 0, '');
INSERT INTO `user` VALUES (43, '18e3877a-b787-eeb9-4f02-a3b22bf0242d', 'huangz5', 'huangzitao@outlook.com', 'Huang Zitao', 2, '2a10yMlNkqH3kK2Txyh*RVNU1jPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'DLNU', 1, '');
INSERT INTO `user` VALUES (44, '5d13e184-b41a-1ce3-9c1d-96b46b686d2e', 'martink', 'kevinmar@yahoo.com', 'Kevin Martin', 1, '2a10yMlNkqH3kK2Txyh\"RVNU1}PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 1, '');
INSERT INTO `user` VALUES (45, '40e9242c-2c9f-c673-9d2c-faefaef219df', 'romerp8', 'rpame@gmail.com', 'Pamela Romero', 0, '2a10yMlNkqH3kK2TxyheRVNU1sPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 0, '');
INSERT INTO `user` VALUES (46, 'd00b9844-1dfe-93f1-9980-c7857ead7996', 'meyereb', 'rebeccameyer68@icloud.com', 'Rebecca Meyer', 1, '2a10yMlNkqH3kK2Txyh1RVNU1fPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Chengdu', 0, '');
INSERT INTO `user` VALUES (47, 'b10b2775-1541-b071-f81b-735a8d52ebae', 'roberts10', 'mroberts@hotmail.com', 'Marjorie Roberts', 1, '2a10yMlNkqH3kK2Txyh\'RVNU1oPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Shanghai', 0, '');
INSERT INTO `user` VALUES (48, '752b53c4-b23b-fb72-4fb3-2432a2f77317', 'angelabryant', 'bange@mail.com', 'Angela Bryant', 1, '2a10yMlNkqH3kK2Txyh0RVNU1GPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 1, '');
INSERT INTO `user` VALUES (49, 'a7468791-0096-54cf-b196-ba78908cc9b4', 'josegar4', 'garzajose@outlook.com', 'Josephine Garza', 2, '2a10yMlNkqH3kK2TxyhQRVNU17PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 0, '');
INSERT INTO `user` VALUES (50, '718f2968-a479-e5ba-a92e-7db3847432ec', 'zxia', 'zhouxiaoming@outlook.com', 'Zhou Xiaoming', 2, '2a10yMlNkqH3kK2Txyh#RVNU1:PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 0, '');
INSERT INTO `user` VALUES (51, 'c6220215-28f8-af4d-eeea-83e6ad63bde4', 'konglan1990', 'lan1@yahoo.com', 'Kong Lan', 0, '2a10yMlNkqH3kK2TxyhwRVNU1KPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 1, '');
INSERT INTO `user` VALUES (52, 'a5105136-7bb9-f2f5-a42f-7118e8c173d8', 'josephinewoo', 'josephinewo7@icloud.com', 'Josephine Wood', 0, '2a10yMlNkqH3kK2TxyhCRVNU1PPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 0, '');
INSERT INTO `user` VALUES (53, '573a7512-ef96-c218-d7d2-6216cc10d6a5', 'zitaoshi', 'zitao1124@gmail.com', 'Shi Zitao', 1, '2a10yMlNkqH3kK2Txyh|RVNU1OPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Chengdu', 0, '');
INSERT INTO `user` VALUES (54, 'ab0aa686-8ae8-3fc4-29c2-d5119e3f0186', 'stephentu', 'tuste114@yahoo.com', 'Stephen Turner', 0, '2a10yMlNkqH3kK2TxyhRRVNU1WPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'DLNU', 1, '');
INSERT INTO `user` VALUES (55, 'e8822c59-e58c-d367-cd3a-1dcb6b295d39', 'mabu', 'butlematthew@gmail.com', 'Matthew Butler', 2, '2a10yMlNkqH3kK2Txyh{RVNU1QPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 1, '');
INSERT INTO `user` VALUES (56, '690723ad-9d92-8cae-26c9-0e6953aad13f', 'bellanita1210', 'bell19@outlook.com', 'Anita Bell', 2, '2a10yMlNkqH3kK2TxyhRRVNU1wPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 0, '');
INSERT INTO `user` VALUES (57, 'ee9ef89d-bd0c-3339-b137-3d8c76e3ed95', 'debbryan', 'debbryant@icloud.com', 'Debra Bryant', 1, '2a10yMlNkqH3kK2TxyhhRVNU1[PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Beijing', 1, '');
INSERT INTO `user` VALUES (58, '454324dd-b84d-2084-284b-1a370552e232', 'garywhite64', 'gary1@outlook.com', 'Gary White', 1, '2a10yMlNkqH3kK2TxyhsRVNU1(PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Beijing', 1, '');
INSERT INTO `user` VALUES (59, '31648f45-bacf-6c22-7eb7-f700a5a1d222', 'xiuyiz513', 'xiz@gmail.com', 'Zhao Xiuying', 0, '2a10yMlNkqH3kK2TxyhoRVNU1[PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 0, '');
INSERT INTO `user` VALUES (60, '7e3bc61f-9ecc-59ba-bc9c-01399e20fb7e', 'edhe', 'hened@yahoo.com', 'Edith Henry', 1, '2a10yMlNkqH3kK2Txyh*RVNU1=PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 1, '');
INSERT INTO `user` VALUES (61, '116374d6-aa88-7c59-f3c6-f7d4c4e41c1f', 'lu711', 'lujiehong502@outlook.com', 'Lu Jiehong', 0, '2a10yMlNkqH3kK2TxyhLRVNU1{PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'DLNU', 1, '');
INSERT INTO `user` VALUES (62, '8da16421-7429-0d70-881f-74c2b9449715', 'cindwells', 'ciwells9@outlook.com', 'Cindy Wells', 1, '2a10yMlNkqH3kK2TxyhZRVNU1{PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 0, '');
INSERT INTO `user` VALUES (63, 'ad3b723f-290a-1e22-c01f-5b7d03bcec78', 'ziyi1', 'ziyi4@icloud.com', 'Ding Ziyi', 1, '2a10yMlNkqH3kK2TxyhtRVNU1ZPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 0, '');
INSERT INTO `user` VALUES (64, 'c18592e5-857c-6b1b-b0ca-5bfc96d59d1d', 'sanqi', 'shaoanq3@gmail.com', 'Shao Anqi', 0, '2a10yMlNkqH3kK2TxyhURVNU1\"PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 1, '');
INSERT INTO `user` VALUES (65, '5ba8e405-0466-cc2c-e9b8-bb3276f5822d', 'jijia', 'jia1@gmail.com', 'Jia Jialun', 2, '2a10yMlNkqH3kK2Txyh\\RVNU18PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 1, '');
INSERT INTO `user` VALUES (66, '24f07a7d-77c5-15b5-b582-fafd3f6d1b55', 'brothelma10', 'brookst@gmail.com', 'Thelma Brooks', 2, '2a10yMlNkqH3kK2Txyh|RVNU1\\PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'DLNU', 1, '');
INSERT INTO `user` VALUES (67, '88ad5434-21ca-ab0a-1890-06d767098536', 'heather105', 'fox7@gmail.com', 'Heather Fox', 0, '2a10yMlNkqH3kK2TxyhuRVNU11PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Shenzhen', 1, '');
INSERT INTO `user` VALUES (68, 'ac41aca1-0377-f472-2e49-8e80c8b3b0c7', 'yanshihan', 'yan7@outlook.com', 'Yan Shihan', 1, '2a10yMlNkqH3kK2TxyhgRVNU1\\PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 0, '');
INSERT INTO `user` VALUES (69, '4fd50420-8352-470c-6633-81ba55797809', 'xiaomingshi4', 'xshi@gmail.com', 'Shi Xiaoming', 1, '2a10yMlNkqH3kK2TxyhlRVNU1MPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 1, '');
INSERT INTO `user` VALUES (70, '09469397-5d8e-1f09-ce07-52dc353ad3a7', 'anqlong1006', 'longanqi1960@gmail.com', 'Long Anqi', 0, '2a10yMlNkqH3kK2TxyhZRVNU14PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 1, '');
INSERT INTO `user` VALUES (71, 'e23b726a-f40b-ea1f-6ec4-a9cdad6f7415', 'jialun822', 'dingjialun216@hotmail.com', 'Ding Jialun', 2, '2a10yMlNkqH3kK2Txyh3RVNU1QPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Chengdu', 0, '');
INSERT INTO `user` VALUES (72, '44668c77-e3dc-c583-1de5-bf6139079500', 'curtiswebb', 'curtis59@gmail.com', 'Curtis Webb', 2, '2a10yMlNkqH3kK2Txyh$RVNU1cPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'DLNU', 0, '');
INSERT INTO `user` VALUES (73, '7ad066ef-f488-4ea0-c1aa-55049b2e8eb4', 'peterson1949', 'ronaldpeterson@yahoo.com', 'Ronald Peterson', 0, '2a10yMlNkqH3kK2Txyh~RVNU1pPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 1, '');
INSERT INTO `user` VALUES (74, '49d6eda8-6631-821c-0347-e8715f418e5b', 'molee', 'monical3@gmail.com', 'Monica Lee', 2, '2a10yMlNkqH3kK2Txyh2RVNU1NPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 0, '');
INSERT INTO `user` VALUES (75, 'a8c7407d-d7c5-16b2-c545-ed1a1f613ec0', 'roysoto', 'sotr@gmail.com', 'Roy Soto', 0, '2a10yMlNkqH3kK2TxyhsRVNU1}PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Columbus', 1, '');
INSERT INTO `user` VALUES (76, 'f418a419-f396-783f-be6e-4350860b4704', 'zhangz', 'zhangzhi@gmail.com', 'Zhang Zhiyuan', 0, '2a10yMlNkqH3kK2Txyh:RVNU19PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Shanghai', 0, '');
INSERT INTO `user` VALUES (77, '5bcd8c7c-c93c-0247-6b7c-1d794754a8a2', 'lujian5', 'lu56@hotmail.com', 'Jiang Lu', 0, '2a10yMlNkqH3kK2TxyhORVNU1EPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Beijing', 1, '');
INSERT INTO `user` VALUES (78, '53f7f7bd-95aa-36ff-eda4-e9f70c9e4f9a', 'zlu', 'luzhi@outlook.com', 'Lu Zhiyuan', 2, '2a10yMlNkqH3kK2TxyhFRVNU1/PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 1, '');
INSERT INTO `user` VALUES (79, 'fc116ce4-a446-4470-0527-7bd2ab5bdf6e', 'garykelley', 'kelg8@gmail.com', 'Gary Kelley', 1, '2a10yMlNkqH3kK2Txyh(RVNU14PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Chengdu', 1, '');
INSERT INTO `user` VALUES (80, 'cac44bf8-6526-3119-d92c-89d14fa99e4b', 'johnsonbobb', 'bobjoh1967@outlook.com', 'Bobby Johnson', 1, '2a10yMlNkqH3kK2Txyh*RVNU1&PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 0, '');
INSERT INTO `user` VALUES (81, 'e378b95e-fefa-8fde-a62e-c21fd9139ae1', 'rivera53', 'ethelrive@hotmail.com', 'Ethel Rivera', 0, '2a10yMlNkqH3kK2TxyhzRVNU1{PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 1, '');
INSERT INTO `user` VALUES (82, '99d141ca-1cf8-959c-e4b7-978a30e8115e', 'xiuyingyang', 'xiuying2@outlook.com', 'Yang Xiuying', 2, '2a10yMlNkqH3kK2TxyhwRVNU1LPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 1, '');
INSERT INTO `user` VALUES (83, 'cc00e741-7c87-dfbb-2163-cd582692bef4', 'nicowen2', 'nowens7@gmail.com', 'Nicole Owens', 2, '2a10yMlNkqH3kK2TxyhzRVNU1\"PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 0, '');
INSERT INTO `user` VALUES (84, '9fbf6d52-0032-220f-f65b-8f042cd7294e', 'tj1954', 'jiehongtan@icloud.com', 'Tan Jiehong', 1, '2a10yMlNkqH3kK2Txyh7RVNU1cPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 1, '');
INSERT INTO `user` VALUES (85, '36278b05-df7a-7474-9564-8c060e5d5db1', 'brucem', 'brucemorris4@mail.com', 'Bruce Morris', 1, '2a10yMlNkqH3kK2Txyh[RVNU12PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 1, '');
INSERT INTO `user` VALUES (86, '57716dbb-4b96-b2a4-1587-e99ceb37884e', 'yyunxi06', 'yeyunxi@icloud.com', 'Ye Yunxi', 2, '2a10yMlNkqH3kK2TxyhTRVNU1\"PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Columbus', 0, '');
INSERT INTO `user` VALUES (87, '0f6735be-b5ab-0c05-7021-7df0b8520e6c', 'yarui59', 'rui9@icloud.com', 'Yan Rui', 1, '2a10yMlNkqH3kK2Txyh0RVNU10PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 1, '');
INSERT INTO `user` VALUES (88, '4a60f6d3-8ca0-d8a7-f2e4-c1a0727ca302', 'marthelma8', 'thelmamarshall1007@hotmail.com', 'Thelma Marshall', 0, '2a10yMlNkqH3kK2TxyhaRVNU1dPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 1, '');
INSERT INTO `user` VALUES (89, '10a62a83-e27e-9c9f-5ec2-d240fd3c3d78', 'jgao6', 'jialungao02@gmail.com', 'Gao Jialun', 1, '2a10yMlNkqH3kK2TxyhvRVNU1vPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Shenzhen', 1, '');
INSERT INTO `user` VALUES (90, 'e452ac33-e71e-f958-b58b-30152bcb1ac2', 'xiaoming6', 'dingxia@mail.com', 'Ding Xiaoming', 0, '2a10yMlNkqH3kK2TxyhHRVNU1?PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Beijing', 1, '');
INSERT INTO `user` VALUES (91, '61480763-afec-df56-9c8b-dd8bb4ad6b61', 'xiong9', 'yunxix3@mail.com', 'Xiong Yunxi', 0, '2a10yMlNkqH3kK2Txyh[RVNU1#PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Shanghai', 1, '');
INSERT INTO `user` VALUES (92, '17523c84-5c86-3575-79a8-efba4521511a', 'qiuxiuying415', 'xiuyingqiu@gmail.com', 'Qiu Xiuying', 1, '2a10yMlNkqH3kK2Txyh>RVNU14PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 0, '');
INSERT INTO `user` VALUES (93, 'cce4b60b-7197-5cf9-592b-6249fa511bc3', 'meyercarolyn', 'carom@gmail.com', 'Carolyn Meyer', 1, '2a10yMlNkqH3kK2TxyhNRVNU1YPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 0, '');
INSERT INTO `user` VALUES (94, 'c13247fb-e695-885c-66f1-e017b9058f83', 'jrami4', 'ramirez68@outlook.com', 'Janice Ramirez', 1, '2a10yMlNkqH3kK2Txyh/RVNU1]PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'DLNU', 0, '');
INSERT INTO `user` VALUES (95, '005ef5d9-ce6a-697b-1788-f0b625cee8f4', 'jieholu7', 'jiehongl323@outlook.com', 'Lu Jiehong', 2, '2a10yMlNkqH3kK2TxyhsRVNU1?PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Sapporo', 0, '');
INSERT INTO `user` VALUES (96, 'ce41c039-5cf5-680d-47cf-ca54b9489a4c', 'mitchellpa', 'pmitchell@gmail.com', 'Paula Mitchell', 2, '2a10yMlNkqH3kK2TxyhuRVNU1cPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 0, '');
INSERT INTO `user` VALUES (97, '4ee0e39e-cea1-2d83-aae9-85bc1b71d509', 'anqi94', 'luanq@gmail.com', 'Lu Anqi', 0, '2a10yMlNkqH3kK2TxyhZRVNU13PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Oxford', 0, '');
INSERT INTO `user` VALUES (98, 'b4295d49-1aaa-4433-d41c-4a331197b197', 'anqisong1', 'songanqi@icloud.com', 'Song Anqi', 1, '2a10yMlNkqH3kK2Txyh1RVNU1ePEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Chengdu', 1, '');
INSERT INTO `user` VALUES (99, 'b2226cd9-7565-8f0e-cfb0-6144b8449aa5', 'reed74', 'reed4@gmail.com', 'Charlotte Reed', 0, '2a10yMlNkqH3kK2TxyhbRVNU1DPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Shenzhen', 1, '');
INSERT INTO `user` VALUES (100, 'a95dceda-cc78-d0d0-125a-bb5ed3b13ab9', 'yaanqi', 'anqiy@icloud.com', 'Yan Anqi', 2, '2a10yMlNkqH3kK2TxyhZRVNU1!PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Beijing', 1, '');
INSERT INTO `user` VALUES (101, '56370fc5-ef89-b2be-39de-9347a6d10d1f', 'zitao86', 'qiu9@icloud.com', 'Qiu Zitao', 1, '2a10yMlNkqH3kK2TxyhQRVNU1KPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Columbus', 1, '');
INSERT INTO `user` VALUES (102, 'c6cd84f3-54a5-ad3b-05f4-4d1607a06cdf', 'zhiyuang', 'gonzhi@gmail.com', 'Gong Zhiyuan', 0, '2a10yMlNkqH3kK2Txyh0RVNU1]PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Shanghai', 1, '');
INSERT INTO `user` VALUES (103, 'e94bf7c3-1332-8433-a16a-911a281acc94', 'lanqiu64', 'lan70@icloud.com', 'Qiu Lan', 0, '2a10yMlNkqH3kK2Txyh^RVNU1CPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'DLNU', 0, '');
INSERT INTO `user` VALUES (104, 'f2dc2298-2798-2f83-3be6-ef27a27030e4', 'fishejess', 'fishejesse409@mail.com', 'Jesse Fisher', 2, '2a10yMlNkqH3kK2TxyhLRVNU1EPEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'DLNU', 0, '');
INSERT INTO `user` VALUES (105, '55e0119d-0cb6-9fc0-c746-5e4207b13d8a', 'harrcruz', 'crharry@gmail.com', 'Harry Cruz', 2, '2a10yMlNkqH3kK2TxyhcRVNU1 PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'Akron', 0, '');
INSERT INTO `user` VALUES (106, '3dbd7e3f-55d9-96ad-a55c-9231f3ac4cc0', 'dalclark', 'clark1971@outlook.com', 'Dale Clark', 2, '2a10yMlNkqH3kK2Txyh$RVNU1[PEYe0MjEF485oTrMyGSvBuEV51rWgAW', 'DLNU', 1, '');
INSERT INTO `user` VALUES (107, 'NAiuPW5/TTmaE6l6L2NPRQ', 'lmy', '2081282864@qq.com', 'lmy', 2, '$2a$12$Fy.hOPb3l35qZJ6irweom..amtc7YdRHu5w21W2RxmlpqZHRyg/9m', 'DLNU', 1, '');

SET FOREIGN_KEY_CHECKS = 1;
