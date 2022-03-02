/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : group

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 25/02/2022 14:45:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coms_admin
-- ----------------------------
DROP TABLE IF EXISTS `coms_admin`;
CREATE TABLE `coms_admin`  (
  `id` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_admin
-- ----------------------------
INSERT INTO `coms_admin` VALUES (1001, 'admin123', '$2a$10$FXF.j.RL2Q.6n1NO.QCx6Ojc.Lpu0XzHBgEKzKYX5GK5tfxeEzXoy', '管理员一', 0);
INSERT INTO `coms_admin` VALUES (1002, 'admin', '$2a$10$EfWmKYkJ4KuouqV9rI.3eubMWM4kqvo5T9q7Q2rjYJSEuRnhFTaAC', '超级管理员', 1);

-- ----------------------------
-- Table structure for coms_category
-- ----------------------------
DROP TABLE IF EXISTS `coms_category`;
CREATE TABLE `coms_category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `icon` varchar(515) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_category
-- ----------------------------
INSERT INTO `coms_category` VALUES (4, 0, '主食', 'rice.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (5, 0, '食用油', 'test1.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (6, 0, '调料', 'seasoning.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (7, 0, '蔬菜', 'vegetables.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (8, 0, '肉/蛋', 'meat.png', '2021-07-08 10:00:24', '2021-08-14 16:38:43');
INSERT INTO `coms_category` VALUES (9, 0, '其他', 'test1.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (15, 6, '白糖', 'f6a88388-6844-41c2-8c28-a27117636bce.png', '2021-07-08 10:00:24', '2021-08-30 19:36:49');
INSERT INTO `coms_category` VALUES (16, 6, '白醋', 'efb8bbc8-3f61-47ca-80cb-3d1ad83700d5.png', '2021-07-08 10:00:24', '2021-08-30 19:37:18');
INSERT INTO `coms_category` VALUES (17, 6, '味精', '1b5b8f90-feb3-4b3e-9e8e-e101870e6787.png', '2021-07-08 10:00:24', '2021-08-30 19:38:11');
INSERT INTO `coms_category` VALUES (18, 6, '沙司', 'rice.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (19, 6, '面酱', '86c03aac-17cf-497c-8ec2-6a9c5ca427ad.png', '2021-07-08 10:00:24', '2021-08-30 19:40:11');
INSERT INTO `coms_category` VALUES (20, 6, '番茄酱', 'b807351a-aca4-41b2-a9b7-91969fe0ea89.png', '2021-08-13 23:27:11', '2021-08-30 19:39:19');
INSERT INTO `coms_category` VALUES (21, 6, '鸡精', '2e29647f-de7b-48ec-b42c-e699a2fe0e38.png', '2021-07-08 10:00:24', '2021-08-30 19:35:09');
INSERT INTO `coms_category` VALUES (22, 6, '大料', 'ab16e09f-f1c5-421f-b397-2786484e530a.png', '2021-07-08 10:00:24', '2021-08-30 19:34:33');
INSERT INTO `coms_category` VALUES (23, 4, '大米', 'c4e9b177-94da-4d36-aad6-a0121c4af779.png', '2021-07-08 10:00:24', '2021-08-30 19:34:01');
INSERT INTO `coms_category` VALUES (24, 4, '面', 'ae728890-8076-488e-8aef-b8f97ca47613.png', '2021-07-08 10:00:24', '2021-08-30 19:33:37');
INSERT INTO `coms_category` VALUES (25, 4, '杂粮', '56defe0a-32b1-42aa-afde-98fe8617e52f.png', '2021-07-08 10:00:24', '2021-08-30 19:32:49');
INSERT INTO `coms_category` VALUES (26, 4, '豆类', '6a757bd9-0e50-4fdd-8f01-86c6c04f1377.png', '2021-07-08 10:00:24', '2021-08-30 19:32:18');
INSERT INTO `coms_category` VALUES (27, 4, '薯类', '8cb4b985-aa6c-46fe-866f-aef79954de2d.png', '2021-07-08 10:00:24', '2021-08-30 19:30:43');
INSERT INTO `coms_category` VALUES (28, 8, '牛肉', '23d0e887-a28a-4d5b-afb8-3a5acc61d408.png', '2021-07-12 10:20:57', '2021-08-30 19:31:21');
INSERT INTO `coms_category` VALUES (29, 8, '猪肉', '18e4d7e2-6334-4309-bda8-a793a8afd96b.png', '2021-07-12 10:25:55', '2021-08-30 19:27:00');
INSERT INTO `coms_category` VALUES (30, 8, '鸡肉', '9dbe2e44-25b6-4df6-98eb-9650fbb6cb37.png', '2021-07-12 10:26:34', '2021-08-30 19:27:40');
INSERT INTO `coms_category` VALUES (31, 8, '鸭肉', '13658e27-d60b-470f-8952-45b9891dd9be.png', '2021-07-12 10:27:16', '2021-08-30 19:28:50');
INSERT INTO `coms_category` VALUES (32, 8, '鱼肉', 'aec69e84-b156-4005-876c-42f5f82b17f4.png', '2021-07-12 10:28:50', '2021-08-30 19:29:42');
INSERT INTO `coms_category` VALUES (33, 8, '羊肉', '66d1b796-31fe-4c1d-8c0d-41e6c43e1e79.png', '2021-07-12 10:52:49', '2021-08-30 19:19:27');
INSERT INTO `coms_category` VALUES (34, 8, '蛋', '48f1571c-da91-4ed4-b9bb-7cbc3fe6cefb.png', '2021-07-12 10:53:58', '2021-08-30 19:20:15');
INSERT INTO `coms_category` VALUES (35, 8, '冷冻食品', 'a988da9e-8b5a-4f56-a453-b16c543ba3e5.png', '2021-07-12 10:57:29', '2021-08-30 19:20:43');
INSERT INTO `coms_category` VALUES (36, 8, '熟食', '12ac2b29-7681-4051-ac5f-6c25685ad293.png', '2021-07-12 11:04:35', '2021-08-30 19:21:22');
INSERT INTO `coms_category` VALUES (37, 7, '叶菜', 'e07ccadd-7c63-41df-bf7c-89f900b18bd1.png', '2021-07-12 11:11:58', '2021-08-30 19:22:07');
INSERT INTO `coms_category` VALUES (38, 7, '豆芽', 'ad1aa112-9ca8-4109-8c0d-8bd6f71df2fb.png', '2021-07-12 11:12:32', '2021-08-30 19:22:42');
INSERT INTO `coms_category` VALUES (39, 7, '葱蒜', '11bb629b-b319-4579-a0d1-59af0c753e58.png', '2021-07-12 11:13:29', '2021-08-30 19:23:30');
INSERT INTO `coms_category` VALUES (40, 7, '瓜果', 'cdac7fc1-24e3-4f49-8604-22e00bc702b1.png', '2021-07-12 11:15:03', '2021-08-30 19:24:58');
INSERT INTO `coms_category` VALUES (41, 0, 'abc', 'test1.png', '2021-08-14 16:45:52', '2021-08-14 16:45:52');
INSERT INTO `coms_category` VALUES (42, 41, 'AAA', '83ff9761-32c6-4fd3-b029-5b6b335dfae5.jpg', '2021-08-14 16:46:13', '2021-08-14 16:46:13');
INSERT INTO `coms_category` VALUES (43, 41, 'BBB', '71014e77-e92a-419f-ace1-7d62b10935f1.jpg', '2021-08-14 16:46:32', '2021-08-14 16:46:32');
INSERT INTO `coms_category` VALUES (44, 41, 'CCC', '2b2d1bda-1685-45be-8fb2-2c040e462822.jpg', '2021-08-14 16:46:47', '2021-08-14 16:46:47');
INSERT INTO `coms_category` VALUES (49, 41, '三年级数学', 'be8ea668-4d10-43ba-8df0-f6edd15717f4.jpg', '2021-08-15 16:36:30', '2021-08-15 16:36:30');

-- ----------------------------
-- Table structure for coms_err_order
-- ----------------------------
DROP TABLE IF EXISTS `coms_err_order`;
CREATE TABLE `coms_err_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `err_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `err_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_id` int NOT NULL,
  `order_item_id` int NULL DEFAULT NULL,
  `stall_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_err_order
-- ----------------------------
INSERT INTO `coms_err_order` VALUES (1, 'wewew', 'w', 1, 0, NULL);
INSERT INTO `coms_err_order` VALUES (2, 'asdfasd', 'null', 234, 0, NULL);
INSERT INTO `coms_err_order` VALUES (3, '有毒', 'null', 232, 0, NULL);
INSERT INTO `coms_err_order` VALUES (4, '有毒', 'null', 229, 0, NULL);
INSERT INTO `coms_err_order` VALUES (5, '不阿红', 'null', 228, 0, NULL);
INSERT INTO `coms_err_order` VALUES (6, '暗室逢灯第三', 'null', 356, NULL, NULL);
INSERT INTO `coms_err_order` VALUES (7, '敖德萨发送到', 'null', 355, NULL, NULL);
INSERT INTO `coms_err_order` VALUES (8, '阿斯蒂芬', 'null', 354, NULL, NULL);
INSERT INTO `coms_err_order` VALUES (9, '沙发上的', 'null', 353, NULL, NULL);
INSERT INTO `coms_err_order` VALUES (10, 'fhgh', 'null', 364, NULL, NULL);
INSERT INTO `coms_err_order` VALUES (11, 'safd', '', 367, NULL, 3);
INSERT INTO `coms_err_order` VALUES (12, 'safd', '', 367, NULL, 3);
INSERT INTO `coms_err_order` VALUES (13, 'as', '', 367, NULL, 3);
INSERT INTO `coms_err_order` VALUES (14, 'asd', '', 367, NULL, 3);
INSERT INTO `coms_err_order` VALUES (15, 'asfsdafsdafsd', '202194174518_eax6v08iv.png ', 366, NULL, 3);
INSERT INTO `coms_err_order` VALUES (16, 'assfsfasfa', '202195165040_33sbw62eo.png ', 369, NULL, 3);
INSERT INTO `coms_err_order` VALUES (17, '沙发斯蒂芬十大', '20219683455_vnqc8ikt1.png ', 371, NULL, 3);
INSERT INTO `coms_err_order` VALUES (18, '我是你', '', 375, NULL, 3);
INSERT INTO `coms_err_order` VALUES (19, '你明明', '202196192726_y28uzu7wc.jpg ', 374, NULL, 3);
INSERT INTO `coms_err_order` VALUES (20, 'JS您PVPXP静默www五', '20219883932_yh1rb6ot1.jpg ', 435, NULL, 3);
INSERT INTO `coms_err_order` VALUES (21, '我想问一下', '2021998475_chomj48fu.jpg ', 442, NULL, 4);
INSERT INTO `coms_err_order` VALUES (22, '购买遇到问题:不太好', '202191094630_5zh8ya43d.jpg ', 446, NULL, 2);
INSERT INTO `coms_err_order` VALUES (23, '性能问题:', '', 449, NULL, 2);
INSERT INTO `coms_err_order` VALUES (24, '功能建议:撒地方送达', '', 463, NULL, 2);

-- ----------------------------
-- Table structure for coms_order
-- ----------------------------
DROP TABLE IF EXISTS `coms_order`;
CREATE TABLE `coms_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `stall_id` int NOT NULL,
  `amount_total` decimal(10, 2) NOT NULL,
  `status` int NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sup_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sup_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sup_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fid_order_stall`(`stall_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 464 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_order
-- ----------------------------
INSERT INTO `coms_order` VALUES (154, 3, 1110.00, 1, '2021-07-12 15:57:17', '2021-07-24 16:52:26', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (155, 3, 105.00, 1, '2021-07-12 16:03:18', '2021-07-24 16:52:28', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (226, 3, 9.50, 1, '2021-07-13 14:00:44', '2021-07-24 16:52:29', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (227, 3, 237.00, 1, '2021-07-13 14:03:46', '2021-07-24 16:52:29', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (228, 3, 237.00, -1, '2021-07-13 14:03:46', '2021-07-24 16:52:30', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (229, 3, 357.00, -1, '2021-07-13 14:04:39', '2021-07-24 16:52:31', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (231, 3, 6438.00, 1, '2021-07-13 14:05:37', '2021-07-24 16:52:31', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (232, 3, 48.50, -1, '2021-07-13 15:57:48', '2021-07-24 16:52:33', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (233, 3, 48.50, 1, '2021-07-13 15:57:48', '2021-07-24 16:52:35', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (234, 3, 9.50, -1, '2021-07-13 16:14:52', '2021-07-24 16:52:35', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (235, 3, 7.50, 1, '2021-07-14 08:29:57', '2021-07-24 16:52:36', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (236, 3, 15.00, 1, '2021-07-14 08:30:46', '2021-07-24 16:52:37', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (237, 3, 24.50, 1, '2021-07-14 08:33:23', '2021-07-24 16:52:38', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (238, 3, 24.50, 1, '2021-07-14 08:33:23', '2021-07-24 16:52:38', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (243, 3, 175.00, 1, '2021-07-14 09:58:03', '2021-07-24 16:52:40', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (244, 3, 330.00, 1, '2021-07-14 09:59:15', '2021-07-24 16:52:41', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (256, 3, 30.00, 1, '2021-07-14 10:52:42', '2021-07-24 16:52:42', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (257, 3, 30.00, 1, '2021-07-14 10:52:42', '2021-07-24 16:52:43', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (258, 3, 15.00, 1, '2021-07-14 16:08:37', '2021-07-24 16:52:44', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (259, 3, 30.00, 1, '2021-07-20 15:30:08', '2021-07-24 16:52:47', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (260, 3, 30.00, 1, '2021-07-20 15:30:08', '2021-07-24 16:52:50', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (268, 3, 30.00, 1, '2021-08-06 14:47:39', '2021-08-06 14:47:39', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (269, 3, 30.00, 1, '2021-08-06 14:51:21', '2021-08-06 14:51:21', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (270, 3, 7.50, 1, '2021-08-06 14:55:22', '2021-08-06 14:55:22', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (271, 3, 17.00, 1, '2021-08-07 20:53:22', '2021-08-07 20:53:22', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (272, 3, 28.50, 1, '2021-08-07 20:53:40', '2021-08-07 20:53:40', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (273, 3, 292.50, 1, '2021-08-07 20:54:02', '2021-08-07 20:54:02', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (274, 3, 75.00, 1, '2021-08-08 22:49:03', '2021-08-08 22:49:03', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (275, 3, 15.00, 1, '2021-08-08 22:49:03', '2021-08-08 22:49:03', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (276, 3, 15.00, 1, '2021-08-08 22:49:03', '2021-08-08 22:49:03', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (277, 3, 15.00, 1, '2021-08-09 00:01:13', '2021-08-09 00:01:13', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (278, 3, 45.00, 1, '2021-08-09 00:01:13', '2021-08-09 00:01:13', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (279, 3, 444.00, 1, '2021-08-09 00:01:13', '2021-08-09 00:01:13', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (283, 3, 10.00, 1, '2021-08-10 00:35:11', '2021-08-10 00:35:11', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (284, 3, 45.00, 1, '2021-08-10 00:35:11', '2021-08-10 00:35:11', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (285, 3, 129.00, 1, '2021-08-10 00:35:59', '2021-08-10 00:35:59', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (286, 3, 100.00, 1, '2021-08-10 00:44:13', '2021-08-10 00:44:13', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (287, 3, 60.00, 1, '2021-08-11 01:32:03', '2021-08-11 01:32:03', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (288, 3, 30.00, 1, '2021-08-11 01:32:03', '2021-08-11 01:32:03', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (289, 3, 22.00, 1, '2021-08-11 01:32:03', '2021-08-11 01:32:03', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (290, 3, 15.00, 1, '2021-08-11 01:33:23', '2021-08-11 01:33:23', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (291, 3, 129.00, 1, '2021-08-11 01:35:10', '2021-08-11 01:35:10', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (292, 3, 289.00, 1, '2021-08-12 00:04:58', '2021-08-12 00:04:58', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (293, 3, 60.00, 1, '2021-08-12 00:04:58', '2021-08-12 00:04:58', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (294, 3, 88.00, 1, '2021-08-13 01:09:03', '2021-08-13 01:09:03', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (295, 3, 258.00, 1, '2021-08-13 01:09:03', '2021-08-13 01:09:03', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (296, 3, 75.00, 1, '2021-08-13 01:09:03', '2021-08-13 01:09:03', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (297, 3, 159.42, 1, '2021-08-14 20:04:47', '2021-08-14 20:04:47', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (298, 3, 99.00, 1, '2021-08-14 20:04:48', '2021-08-14 20:04:48', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (299, 3, 110.00, 1, '2021-08-15 16:24:11', '2021-08-15 16:24:11', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (300, 3, 196.92, 1, '2021-08-15 16:24:11', '2021-08-15 16:24:11', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (301, 3, 19.00, 1, '2021-08-27 11:29:07', '2021-08-27 11:29:07', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (302, 3, 9.50, 1, '2021-08-27 11:30:08', '2021-08-27 11:30:08', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (303, 3, 15.00, 1, '2021-08-30 15:40:06', '2021-08-30 15:40:06', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (304, 3, 19.00, 1, '2021-08-30 17:17:35', '2021-08-30 17:17:35', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (305, 3, 7.50, 1, '2021-08-30 17:24:02', '2021-08-30 17:24:02', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (306, 3, 79.71, 1, '2021-08-30 17:27:49', '2021-08-30 17:27:49', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (307, 3, 129.00, 1, '2021-08-30 17:34:20', '2021-08-30 17:34:20', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (308, 3, 5.50, 1, '2021-08-30 17:39:37', '2021-08-30 17:39:37', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (309, 3, 19.80, 1, '2021-08-30 17:39:37', '2021-08-30 17:39:37', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (310, 3, 15.00, 1, '2021-08-30 20:07:22', '2021-08-30 20:07:22', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (311, 3, 19.80, 1, '2021-08-30 20:22:35', '2021-08-30 20:22:35', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (312, 3, 6.80, 1, '2021-08-30 21:04:52', '2021-08-30 21:04:52', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (313, 3, 7.50, 1, '2021-08-30 21:04:52', '2021-08-30 21:04:52', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (314, 3, 17.00, 1, '2021-08-30 21:10:08', '2021-08-30 21:10:08', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (315, 3, 17.00, 1, '2021-08-30 21:10:45', '2021-08-30 21:10:45', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (316, 3, 17.00, 1, '2021-08-31 09:13:23', '2021-08-31 09:13:23', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (317, 3, 9.50, 1, '2021-08-31 09:16:30', '2021-08-31 09:16:30', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (318, 3, 9.50, 1, '2021-08-31 09:27:05', '2021-08-31 09:27:05', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (319, 3, 17.00, 1, '2021-08-31 09:29:39', '2021-08-31 09:29:39', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (320, 3, 19.00, 1, '2021-08-31 09:35:49', '2021-08-31 09:35:49', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (321, 3, 17.00, 1, '2021-08-31 09:38:34', '2021-08-31 09:38:34', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (322, 3, 9.50, 1, '2021-08-31 09:42:31', '2021-08-31 09:42:31', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (323, 3, 7.50, 1, '2021-08-31 09:46:45', '2021-08-31 09:46:45', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (324, 3, 17.00, 1, '2021-08-31 10:15:26', '2021-08-31 10:15:26', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (325, 3, 17.00, 1, '2021-08-31 10:20:07', '2021-08-31 10:20:07', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (326, 3, 17.00, 1, '2021-08-31 10:20:54', '2021-08-31 10:20:54', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (327, 3, 17.00, 1, '2021-08-31 10:34:54', '2021-08-31 10:34:54', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (328, 3, 17.00, 1, '2021-08-31 10:49:29', '2021-08-31 10:49:29', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (329, 3, 6.80, 1, '2021-08-31 10:49:29', '2021-08-31 10:49:29', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (330, 3, 6.80, 1, '2021-08-31 11:18:46', '2021-08-31 11:18:46', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (331, 3, 87.21, 1, '2021-08-31 11:18:46', '2021-08-31 11:18:46', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (332, 3, 26.50, 1, '2021-08-31 11:20:31', '2021-08-31 11:20:31', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (333, 3, 6.80, 1, '2021-08-31 11:20:31', '2021-08-31 11:20:31', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (334, 3, 6.80, 1, '2021-08-31 11:22:33', '2021-08-31 11:22:33', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (335, 3, 17.00, 1, '2021-08-31 11:22:33', '2021-08-31 11:22:33', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (336, 3, 17.00, 1, '2021-08-31 11:23:10', '2021-08-31 11:23:10', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (337, 3, 6.80, 1, '2021-08-31 11:23:10', '2021-08-31 11:23:10', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (338, 3, 7.50, 1, '2021-08-31 11:26:00', '2021-08-31 11:26:00', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (339, 3, 6.80, 1, '2021-08-31 11:26:00', '2021-08-31 11:26:00', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (340, 3, 56.50, 1, '2021-08-31 11:28:35', '2021-08-31 11:28:35', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (341, 3, 11.00, 1, '2021-08-31 11:28:35', '2021-08-31 11:28:35', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (342, 3, 17.00, 1, '2021-08-31 15:11:17', '2021-08-31 15:11:17', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (343, 3, 6.80, 1, '2021-08-31 15:11:17', '2021-08-31 15:11:17', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (344, 3, 59.50, 1, '2021-08-31 15:17:03', '2021-08-31 15:17:03', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (345, 3, 6.60, 1, '2021-08-31 16:23:06', '2021-08-31 16:23:06', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (346, 3, 25.50, 1, '2021-08-31 16:25:20', '2021-08-31 16:25:20', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (347, 3, 8.90, 1, '2021-08-31 17:07:16', '2021-08-31 17:07:16', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (348, 3, 10.50, 1, '2021-08-31 17:07:53', '2021-08-31 17:07:53', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (349, 3, 20.50, 1, '2021-08-31 17:07:53', '2021-08-31 17:07:53', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (350, 3, 21.00, 1, '2021-09-01 14:22:08', '2021-09-01 14:22:08', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (351, 3, 24.90, 1, '2021-09-01 14:25:08', '2021-09-01 14:25:08', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (352, 3, 19.80, 1, '2021-09-01 14:25:42', '2021-09-01 14:25:42', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (353, 3, 10.50, -1, '2021-09-01 14:25:42', '2021-09-01 14:44:16', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (354, 3, 5.50, -1, '2021-09-01 14:25:42', '2021-09-01 14:43:47', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (355, 3, 48.70, -1, '2021-09-01 14:26:35', '2021-09-01 14:29:43', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (356, 3, 31.50, -1, '2021-09-01 14:26:35', '2021-09-01 14:29:17', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (358, 3, 222.00, 1, '2021-09-01 15:54:52', '2021-09-01 15:54:52', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (359, 3, 10.50, 1, '2021-09-01 21:03:45', '2021-09-01 21:03:45', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (360, 3, 26.00, 1, '2021-09-02 11:51:22', '2021-09-02 11:51:22', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (361, 3, 10.50, 1, '2021-09-02 11:51:22', '2021-09-02 11:51:22', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (362, 3, 10.50, 1, '2021-09-03 17:43:39', '2021-09-03 17:43:39', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (363, 3, 12.00, 1, '2021-09-03 17:43:39', '2021-09-03 17:43:39', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (364, 3, 12.10, -1, '2021-09-03 17:44:42', '2021-09-03 17:45:10', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (365, 3, 15.00, 1, '2021-09-03 17:44:42', '2021-09-03 17:44:42', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (366, 3, 203.90, -1, '2021-09-04 10:37:49', '2021-09-04 17:45:19', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (367, 3, 8.90, -1, '2021-09-04 10:38:18', '2021-09-04 17:18:03', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (368, 3, 15.00, 1, '2021-09-05 16:49:53', '2021-09-05 16:49:53', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (369, 3, 12.00, -1, '2021-09-05 16:49:53', '2021-09-05 16:50:41', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (370, 3, 60.00, 1, '2021-09-05 17:29:46', '2021-09-05 17:29:46', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (371, 3, 30.00, -1, '2021-09-06 08:34:18', '2021-09-06 08:34:56', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (372, 3, 6.60, 1, '2021-09-06 09:39:28', '2021-09-06 09:39:28', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (373, 3, 30.00, 1, '2021-09-06 09:39:28', '2021-09-06 09:39:28', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (374, 3, 10.50, -1, '2021-09-06 16:06:57', '2021-09-06 19:27:27', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (375, 3, 16.50, -1, '2021-09-06 18:05:29', '2021-09-06 19:27:04', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (376, 3, 16.50, 1, '2021-09-06 19:50:35', '2021-09-06 19:50:35', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (404, 3, 130.00, 1, '2021-09-07 14:52:02', '2021-09-07 14:52:02', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (415, 3, 2.40, 1, '2021-09-07 15:51:33', '2021-09-07 15:51:33', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (416, 3, 37.80, 1, '2021-09-07 15:51:33', '2021-09-07 15:51:33', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (417, 3, 2.40, 1, '2021-09-07 15:57:32', '2021-09-07 15:57:32', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (419, 3, 1634.00, 1, '2021-09-07 15:59:57', '2021-09-07 15:59:57', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (433, 3, 208.71, 1, '2021-09-07 16:24:03', '2021-09-07 16:24:03', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (434, 3, 22.00, 1, '2021-09-08 08:35:15', '2021-09-08 08:35:15', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (435, 3, 26.00, -1, '2021-09-08 08:35:15', '2021-09-08 08:39:33', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (436, 3, 158.40, 1, '2021-09-08 15:07:12', '2021-09-08 15:07:12', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (437, 3, 12.00, 1, '2021-09-08 15:07:12', '2021-09-08 15:07:12', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (438, 3, 1043.00, 1, '2021-09-08 15:51:37', '2021-09-08 15:51:37', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (439, 3, 18.90, 1, '2021-09-08 19:35:04', '2021-09-08 19:35:04', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (440, 4, 234.00, 1, '2021-09-08 21:31:48', '2021-09-08 21:31:48', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (441, 4, 22.00, 1, '2021-09-09 08:35:12', '2021-09-09 08:35:12', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (442, 4, 26.00, -1, '2021-09-09 08:38:34', '2021-09-09 08:47:07', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (443, 4, 6.60, 1, '2021-09-09 08:53:15', '2021-09-09 08:53:15', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (444, 2, 26.00, 1, '2021-09-10 09:25:27', '2021-09-10 09:25:27', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (445, 2, 79.71, 1, '2021-09-10 09:45:40', '2021-09-10 09:45:40', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (446, 2, 22.00, -1, '2021-09-10 09:45:40', '2021-09-10 09:46:31', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (447, 2, 208.71, 1, '2021-09-10 09:52:13', '2021-09-10 09:52:13', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (448, 2, 32.60, 1, '2021-09-10 09:52:13', '2021-09-10 09:52:13', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (449, 2, 22.00, -1, '2021-09-10 09:52:13', '2021-09-10 09:52:25', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (450, 2, 22.00, 1, '2021-09-10 09:52:43', '2021-09-10 09:52:43', '供应商5', '18635247852', 5);
INSERT INTO `coms_order` VALUES (458, 2, 58.60, 1, '2021-09-10 10:23:45', '2021-09-10 10:23:45', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (459, 2, 50.00, 1, '2021-09-10 10:23:45', '2021-09-10 10:23:45', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (460, 2, 12.00, 1, '2021-09-10 10:23:45', '2021-09-10 10:23:45', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (461, 2, 29.70, 1, '2021-09-10 11:16:04', '2021-09-10 11:16:04', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (462, 2, 18.90, 1, '2021-09-10 11:16:04', '2021-09-10 11:16:04', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (463, 2, 6.60, -1, '2021-09-10 11:16:04', '2021-09-10 11:16:29', '供应商2', '15623548524', 2);
INSERT INTO `coms_order` VALUES (464, 12, 9.90, 1, '2022-02-24 16:08:06', '2022-02-24 16:08:06', '供应商1', '18472634753', 1);
INSERT INTO `coms_order` VALUES (465, 12, 50.00, 1, '2022-02-24 16:08:06', '2022-02-24 16:08:06', '供应商3', '13562458524', 3);

-- ----------------------------
-- Table structure for coms_order_item
-- ----------------------------
DROP TABLE IF EXISTS `coms_order_item`;
CREATE TABLE `coms_order_item`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `prod_id` int NULL DEFAULT NULL,
  `order_id` int NULL DEFAULT NULL,
  `current_money` decimal(10, 2) NOT NULL,
  `num` int NOT NULL,
  `prod_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `prod_pic_url` varchar(515) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fid_orderitem_prod`(`prod_id`) USING BTREE,
  INDEX `fid_orderitem_order`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 388 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_order_item
-- ----------------------------
INSERT INTO `coms_order_item` VALUES (107, 15, 154, 222.00, 5, '油麦菜', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.yzcdn.cn%2Fupload_files%2F2018%2F03%2F21%2FFuxCCcDErMkUv5KNv-gZfV2LGPaY.jpg%21730x0.jpg&refer=http%3A%2F%2Fimg.yzcdn.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652938&t=baaa08d7e2f91af49c441c5e7879e88d', '2021-07-12 15:57:17', '2021-07-12 15:57:17');
INSERT INTO `coms_order_item` VALUES (108, 13, 155, 15.00, 3, '红烧牛肉方便面', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fi2%2F749584932%2FO1CN012lGspN1mItytsH0iM_%21%21749584932.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652898&t=cf1177d894aa214d7478ed88d91823f1', '2021-07-12 16:03:18', '2021-07-12 16:03:18');
INSERT INTO `coms_order_item` VALUES (185, 3, 226, 9.50, 1, '安井火锅油条', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fpc_best%2F1809%2F17%2Fc57%2F109939120_1537176947197.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652549&t=d9edcbc38a792ad147896059126b4e90', '2021-07-13 14:00:44', '2021-07-13 14:00:44');
INSERT INTO `coms_order_item` VALUES (186, 1, 227, 222.00, 1, '合兆耗油肉片', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcbu01.alicdn.com%2Fimg%2Fibank%2F2018%2F894%2F582%2F9361285498_877391837.jpg&refer=http%3A%2F%2Fcbu01.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628648175&t=022b2716fd593c4253330837c6667c66', '2021-07-13 14:03:46', '2021-07-13 14:03:46');
INSERT INTO `coms_order_item` VALUES (187, 8, 228, 15.00, 1, '大米', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01c66658f58df8a8012049ef1c6948.jpg%40900w_1l_2o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652852&t=e895c6c10fd408c2f396e74122ef14ca', '2021-07-13 14:03:46', '2021-07-13 14:03:46');
INSERT INTO `coms_order_item` VALUES (188, 1, 229, 222.00, 1, '合兆耗油肉片', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcbu01.alicdn.com%2Fimg%2Fibank%2F2018%2F894%2F582%2F9361285498_877391837.jpg&refer=http%3A%2F%2Fcbu01.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628648175&t=022b2716fd593c4253330837c6667c66', '2021-07-13 14:04:39', '2021-07-13 14:04:39');
INSERT INTO `coms_order_item` VALUES (189, 2, 229, 135.00, 1, '新东大锅包肉', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.yzcdn.cn%2Fupload_files%2F2018%2F04%2F21%2FFh-7kPUdJyrXtClEhy6RKVr-yEkw.jpg%3FimageView2%2F2%2Fw%2F580%2Fh%2F580%2Fq%2F75%2Fformat%2Fjpg&refer=http%3A%2F%2Fimg.yzcdn.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628648282&t=fcc0f014d82056cefb79cc49b572c66e', '2021-07-13 14:04:39', '2021-07-13 14:04:39');
INSERT INTO `coms_order_item` VALUES (190, 1, 231, 222.00, 29, '合兆耗油肉片', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcbu01.alicdn.com%2Fimg%2Fibank%2F2018%2F894%2F582%2F9361285498_877391837.jpg&refer=http%3A%2F%2Fcbu01.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628648175&t=022b2716fd593c4253330837c6667c66', '2021-07-13 14:05:37', '2021-07-13 14:05:37');
INSERT INTO `coms_order_item` VALUES (191, 5, 232, 5.00, 4, '好自然大白菜', 'https://img2.baidu.com/it/u=3052855542,1154292779&fm=26&fmt=auto&gp=0.jpg', '2021-07-13 15:57:48', '2021-07-13 15:57:48');
INSERT INTO `coms_order_item` VALUES (192, 3, 233, 9.50, 3, '安井火锅油条', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fpc_best%2F1809%2F17%2Fc57%2F109939120_1537176947197.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652549&t=d9edcbc38a792ad147896059126b4e90', '2021-07-13 15:57:48', '2021-07-13 15:57:48');
INSERT INTO `coms_order_item` VALUES (193, 3, 234, 9.50, 1, '安井火锅油条', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fpc_best%2F1809%2F17%2Fc57%2F109939120_1537176947197.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652549&t=d9edcbc38a792ad147896059126b4e90', '2021-07-13 16:14:52', '2021-07-13 16:14:52');
INSERT INTO `coms_order_item` VALUES (194, 4, 235, 7.50, 1, '景泰隆津味', 'http://ssnew.cn/RQvf5', '2021-07-14 08:29:57', '2021-07-14 08:29:57');
INSERT INTO `coms_order_item` VALUES (195, 13, 236, 15.00, 1, '红烧牛肉方便面', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fi2%2F749584932%2FO1CN012lGspN1mItytsH0iM_%21%21749584932.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652898&t=cf1177d894aa214d7478ed88d91823f1', '2021-07-14 08:30:46', '2021-07-14 08:30:46');
INSERT INTO `coms_order_item` VALUES (196, 3, 237, 9.50, 1, '安井火锅油条', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fpc_best%2F1809%2F17%2Fc57%2F109939120_1537176947197.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652549&t=d9edcbc38a792ad147896059126b4e90', '2021-07-14 08:33:23', '2021-07-14 08:33:23');
INSERT INTO `coms_order_item` VALUES (197, 16, 238, 15.00, 1, '大米AA', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01532e58bfb859a801219c77674a26.jpg%403000w_1l_0o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652974&t=d8e0b6a0c2c1d456979f113980ec53e8', '2021-07-14 08:33:23', '2021-07-14 08:33:23');
INSERT INTO `coms_order_item` VALUES (198, 5, 243, 5.00, 35, '好自然大白菜', 'https://img2.baidu.com/it/u=3052855542,1154292779&fm=26&fmt=auto&gp=0.jpg', '2021-07-14 09:58:03', '2021-07-14 09:58:03');
INSERT INTO `coms_order_item` VALUES (199, 8, 244, 15.00, 22, '大米', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01c66658f58df8a8012049ef1c6948.jpg%40900w_1l_2o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652852&t=e895c6c10fd408c2f396e74122ef14ca', '2021-07-14 09:59:15', '2021-07-14 09:59:15');
INSERT INTO `coms_order_item` VALUES (200, 13, 256, 15.00, 1, '红烧牛肉方便面', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fi2%2F749584932%2FO1CN012lGspN1mItytsH0iM_%21%21749584932.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652898&t=cf1177d894aa214d7478ed88d91823f1', '2021-07-14 10:52:42', '2021-07-14 10:52:42');
INSERT INTO `coms_order_item` VALUES (201, 8, 256, 15.00, 1, '大米', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01c66658f58df8a8012049ef1c6948.jpg%40900w_1l_2o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652852&t=e895c6c10fd408c2f396e74122ef14ca', '2021-07-14 10:52:42', '2021-07-14 10:52:42');
INSERT INTO `coms_order_item` VALUES (202, 22, 257, 15.00, 0, '伊品味精', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.yzcdn.cn%2Fupload_files%2F2016%2F11%2F01%2FFtDkLKYPtLruhrPkFRgLToMYg70G.jpg%3FimageView2%2F2%2Fw%2F580%2Fh%2F580%2Fq%2F75%2Fformat%2Fjpg&refer=http%3A%2F%2Fimg.yzcdn.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628653040&t=695346bb8de8f5a0bed30e834c86e587', '2021-07-14 10:52:42', '2021-07-14 10:52:42');
INSERT INTO `coms_order_item` VALUES (203, 22, 258, 15.00, 1, '伊品味精', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.yzcdn.cn%2Fupload_files%2F2016%2F11%2F01%2FFtDkLKYPtLruhrPkFRgLToMYg70G.jpg%3FimageView2%2F2%2Fw%2F580%2Fh%2F580%2Fq%2F75%2Fformat%2Fjpg&refer=http%3A%2F%2Fimg.yzcdn.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628653040&t=695346bb8de8f5a0bed30e834c86e587', '2021-07-14 16:08:37', '2021-07-14 16:08:37');
INSERT INTO `coms_order_item` VALUES (204, 4, 259, 7.50, 2, '景泰隆津味', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fseopic.699pic.com%2Fphoto%2F50050%2F7111.jpg_wh1200.jpg&refer=http%3A%2F%2Fseopic.699pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628769774&t=1d57ce3c013adf907e1b76e47b7f987e', '2021-07-20 15:30:08', '2021-07-20 15:30:08');
INSERT INTO `coms_order_item` VALUES (205, 8, 260, 15.00, 1, '大米', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01c66658f58df8a8012049ef1c6948.jpg%40900w_1l_2o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652852&t=e895c6c10fd408c2f396e74122ef14ca', '2021-07-20 15:30:08', '2021-07-20 15:30:08');
INSERT INTO `coms_order_item` VALUES (214, 8, 268, 15.00, 1, '大米', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01c66658f58df8a8012049ef1c6948.jpg%40900w_1l_2o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652852&t=e895c6c10fd408c2f396e74122ef14ca', '2021-08-06 14:47:39', '2021-08-06 14:47:39');
INSERT INTO `coms_order_item` VALUES (215, 13, 268, 15.00, 1, '红烧牛肉方便面', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fi2%2F749584932%2FO1CN012lGspN1mItytsH0iM_%21%21749584932.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652898&t=cf1177d894aa214d7478ed88d91823f1', '2021-08-06 14:47:39', '2021-08-06 14:47:39');
INSERT INTO `coms_order_item` VALUES (216, 8, 269, 15.00, 1, '大米', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01c66658f58df8a8012049ef1c6948.jpg%40900w_1l_2o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652852&t=e895c6c10fd408c2f396e74122ef14ca', '2021-08-06 14:51:21', '2021-08-06 14:51:21');
INSERT INTO `coms_order_item` VALUES (217, 13, 269, 15.00, 1, '红烧牛肉方便面', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fi2%2F749584932%2FO1CN012lGspN1mItytsH0iM_%21%21749584932.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652898&t=cf1177d894aa214d7478ed88d91823f1', '2021-08-06 14:51:21', '2021-08-06 14:51:21');
INSERT INTO `coms_order_item` VALUES (218, 4, 270, 7.50, 1, '景泰隆津味', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fseopic.699pic.com%2Fphoto%2F50050%2F7111.jpg_wh1200.jpg&refer=http%3A%2F%2Fseopic.699pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628769774&t=1d57ce3c013adf907e1b76e47b7f987e', '2021-08-06 14:55:22', '2021-08-06 14:55:22');
INSERT INTO `coms_order_item` VALUES (219, 3, 271, 9.50, 1, '安井火锅油条', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fpc_best%2F1809%2F17%2Fc57%2F109939120_1537176947197.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652549&t=d9edcbc38a792ad147896059126b4e90', '2021-08-07 20:53:22', '2021-08-07 20:53:22');
INSERT INTO `coms_order_item` VALUES (220, 4, 271, 7.50, 1, '景泰隆津味', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fseopic.699pic.com%2Fphoto%2F50050%2F7111.jpg_wh1200.jpg&refer=http%3A%2F%2Fseopic.699pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628769774&t=1d57ce3c013adf907e1b76e47b7f987e', '2021-08-07 20:53:22', '2021-08-07 20:53:22');
INSERT INTO `coms_order_item` VALUES (221, 3, 272, 9.50, 3, '安井火锅油条', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fpc_best%2F1809%2F17%2Fc57%2F109939120_1537176947197.jpg&refer=http%3A%2F%2Fimg.pconline.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652549&t=d9edcbc38a792ad147896059126b4e90', '2021-08-07 20:53:40', '2021-08-07 20:53:40');
INSERT INTO `coms_order_item` VALUES (222, 2, 273, 135.00, 2, '新东大锅包肉', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.yzcdn.cn%2Fupload_files%2F2018%2F04%2F21%2FFh-7kPUdJyrXtClEhy6RKVr-yEkw.jpg%3FimageView2%2F2%2Fw%2F580%2Fh%2F580%2Fq%2F75%2Fformat%2Fjpg&refer=http%3A%2F%2Fimg.yzcdn.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628648282&t=fcc0f014d82056cefb79cc49b572c66e', '2021-08-07 20:54:02', '2021-08-07 20:54:02');
INSERT INTO `coms_order_item` VALUES (223, 4, 273, 7.50, 3, '景泰隆津味', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fseopic.699pic.com%2Fphoto%2F50050%2F7111.jpg_wh1200.jpg&refer=http%3A%2F%2Fseopic.699pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628769774&t=1d57ce3c013adf907e1b76e47b7f987e', '2021-08-07 20:54:02', '2021-08-07 20:54:02');
INSERT INTO `coms_order_item` VALUES (224, 13, 274, 15.00, 4, '红烧牛肉方便面', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fi2%2F749584932%2FO1CN012lGspN1mItytsH0iM_%21%21749584932.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652898&t=cf1177d894aa214d7478ed88d91823f1', '2021-08-08 22:49:03', '2021-08-08 22:49:03');
INSERT INTO `coms_order_item` VALUES (225, 21, 275, 15.00, 1, '双汇鸡精', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.mrshuhua.net%2FtushuJDEwLmFsaWNkbi5jb20vaTQvODA5MDY1ODIyL1RCMnZ2OGJmOUJqcHVGankxWGRYWGFvb1ZYYV8hITgwOTA2NTgyMiQ5.jpg&refer=http%3A%2F%2Fwww.mrshuhua.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628653015&t=e5ce4c890a50094652607a4f0bc57b08', '2021-08-08 22:49:03', '2021-08-08 22:49:03');
INSERT INTO `coms_order_item` VALUES (226, 5, 276, 5.00, 3, '好自然大白菜', 'https://img2.baidu.com/it/u=3052855542,1154292779&fm=26&fmt=auto&gp=0.jpg', '2021-08-08 22:49:03', '2021-08-08 22:49:03');
INSERT INTO `coms_order_item` VALUES (227, 16, 274, 15.00, 1, '大米AA', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01532e58bfb859a801219c77674a26.jpg%403000w_1l_0o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652974&t=d8e0b6a0c2c1d456979f113980ec53e8', '2021-08-08 22:49:03', '2021-08-08 22:49:03');
INSERT INTO `coms_order_item` VALUES (228, 4, 277, 7.50, 2, '景泰隆津味', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fseopic.699pic.com%2Fphoto%2F50050%2F7111.jpg_wh1200.jpg&refer=http%3A%2F%2Fseopic.699pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628769774&t=1d57ce3c013adf907e1b76e47b7f987e', '2021-08-09 00:01:13', '2021-08-09 00:01:13');
INSERT INTO `coms_order_item` VALUES (229, 13, 278, 15.00, 3, '红烧牛肉方便面', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fi2%2F749584932%2FO1CN012lGspN1mItytsH0iM_%21%21749584932.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652898&t=cf1177d894aa214d7478ed88d91823f1', '2021-08-09 00:01:13', '2021-08-09 00:01:13');
INSERT INTO `coms_order_item` VALUES (230, 15, 279, 222.00, 2, '油麦菜', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.yzcdn.cn%2Fupload_files%2F2018%2F03%2F21%2FFuxCCcDErMkUv5KNv-gZfV2LGPaY.jpg%21730x0.jpg&refer=http%3A%2F%2Fimg.yzcdn.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652938&t=baaa08d7e2f91af49c441c5e7879e88d', '2021-08-09 00:01:13', '2021-08-09 00:01:13');
INSERT INTO `coms_order_item` VALUES (231, 5, 283, 5.00, 2, '好自然大白菜', 'bbaa09d7-0aa2-4944-8e94-ed02afba0394R-C (1).jfif', '2021-08-10 00:35:11', '2021-08-10 00:35:11');
INSERT INTO `coms_order_item` VALUES (232, 13, 284, 15.00, 3, '红烧牛肉方便面', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fi2%2F749584932%2FO1CN012lGspN1mItytsH0iM_%21%21749584932.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1628652898&t=cf1177d894aa214d7478ed88d91823f1', '2021-08-10 00:35:11', '2021-08-10 00:35:11');
INSERT INTO `coms_order_item` VALUES (233, 26, 285, 129.00, 1, '大希地 精品盒装国产整切西冷牛排套餐含酱包共750g（130g*5片+酱包20g*5包） 牛扒 优质牧场牛肉生鲜', 'e032716f-587e-43b4-b212-5dae271c5d7160564fb8af5f81c8.jpg', '2021-08-10 00:35:59', '2021-08-10 00:35:59');
INSERT INTO `coms_order_item` VALUES (234, 6, 286, 50.00, 2, '王中王火腿肠', '6349ec79-ed9f-47ae-b34f-cf23cf7468a6OIP-C.jfif', '2021-08-10 00:44:13', '2021-08-10 00:44:13');
INSERT INTO `coms_order_item` VALUES (235, 14, 287, 15.00, 2, '吃你家大米', '0ca8c1a3-2fa3-4cf0-a091-4a698a190223.png', '2021-08-11 01:32:03', '2021-08-11 01:32:03');
INSERT INTO `coms_order_item` VALUES (236, 21, 288, 15.00, 2, '双汇鸡精', '604e250d-f300-4200-8c1a-da903309027a.jfif', '2021-08-11 01:32:03', '2021-08-11 01:32:03');
INSERT INTO `coms_order_item` VALUES (237, 13, 287, 15.00, 2, '红烧牛肉方便面', 'b0e7df03-8909-4a86-8897-69ca76f374a2.jpg', '2021-08-11 01:32:03', '2021-08-11 01:32:03');
INSERT INTO `coms_order_item` VALUES (238, 27, 289, 22.00, 1, '大棚油麦菜', '4c5a233a-4e99-46d0-a1c5-b81fcd0b7339.jfif', '2021-08-11 01:32:03', '2021-08-11 01:32:03');
INSERT INTO `coms_order_item` VALUES (239, 4, 290, 7.50, 2, '景泰隆津味', '92361e2d-3103-409c-9067-7caa4df4ad24.jpg', '2021-08-11 01:33:23', '2021-08-11 01:33:23');
INSERT INTO `coms_order_item` VALUES (240, 26, 291, 129.00, 1, '大希地 精品盒装国产整切西冷牛排套餐含酱包共750g（130g*5片+酱包20g*5包） 牛扒 优质牧场牛肉生鲜', '2c15f8c1-6d8d-4c04-99ac-6fee3ad9327c.jpg', '2021-08-11 01:35:10', '2021-08-11 01:35:10');
INSERT INTO `coms_order_item` VALUES (241, 2, 292, 135.00, 2, '新东大锅包肉', '893ebb12-fe28-419a-8d4b-a396070b73ee.jpg', '2021-08-12 00:04:58', '2021-08-12 00:04:58');
INSERT INTO `coms_order_item` VALUES (242, 3, 292, 9.50, 2, '安井火锅油条', '878703f0-d444-4cfd-9595-17afcc3d066b.jfif', '2021-08-12 00:04:58', '2021-08-12 00:04:58');
INSERT INTO `coms_order_item` VALUES (243, 13, 293, 15.00, 4, '红烧牛肉方便面', '64480b61-2bc3-4580-859c-53520c851430.jpg', '2021-08-12 00:04:58', '2021-08-12 00:04:58');
INSERT INTO `coms_order_item` VALUES (244, 27, 294, 22.00, 4, '大棚油麦菜', '4c5a233a-4e99-46d0-a1c5-b81fcd0b7339.jfif', '2021-08-13 01:09:03', '2021-08-13 01:09:03');
INSERT INTO `coms_order_item` VALUES (245, 26, 295, 129.00, 2, '大希地 精品盒装国产整切西冷牛排套餐含酱包共750g（130g*5片+酱包20g*5包） 牛扒 优质牧场牛肉生鲜', '2c15f8c1-6d8d-4c04-99ac-6fee3ad9327c.jpg', '2021-08-13 01:09:03', '2021-08-13 01:09:03');
INSERT INTO `coms_order_item` VALUES (246, 22, 296, 15.00, 5, '伊品味精', '7f76d5d8-bf97-4cee-92a4-a73af2534543.jpg', '2021-08-13 01:09:03', '2021-08-13 01:09:03');
INSERT INTO `coms_order_item` VALUES (247, 7, 297, 79.71, 2, ' 福春园锅包肉', '64c3dcf5-7038-43e6-a825-a71031c5e687.jpg', '2021-08-14 20:04:48', '2021-08-14 20:04:48');
INSERT INTO `coms_order_item` VALUES (248, 22, 298, 19.80, 5, '太太乐味精', '59fc9886-ddaf-4f43-99c8-bf18dfb579d6.jpg', '2021-08-14 20:04:48', '2021-08-14 20:04:48');
INSERT INTO `coms_order_item` VALUES (249, 27, 299, 22.00, 5, '大棚油麦菜', 'f51a9829-650a-4288-8f33-c7b392779a07.jfif', '2021-08-15 16:24:11', '2021-08-15 16:24:11');
INSERT INTO `coms_order_item` VALUES (250, 7, 300, 79.71, 2, ' 福春园锅包肉', '64c3dcf5-7038-43e6-a825-a71031c5e687.jpg', '2021-08-15 16:24:11', '2021-08-15 16:24:11');
INSERT INTO `coms_order_item` VALUES (251, 4, 300, 7.50, 5, '好记 木桶酿造津味有机老酱油', '28d9a0d5-ea00-433d-8813-ce3316f4d7eb.jfif', '2021-08-15 16:24:11', '2021-08-15 16:24:11');
INSERT INTO `coms_order_item` VALUES (252, 3, 301, 9.50, 2, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-27 11:29:07', '2021-08-27 11:29:07');
INSERT INTO `coms_order_item` VALUES (253, 3, 302, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-27 11:30:08', '2021-08-27 11:30:08');
INSERT INTO `coms_order_item` VALUES (254, 4, 303, 7.50, 2, '好记 木桶酿造津味有机老酱油', '3bc149fe-cdc4-4697-9930-4863e68e14e2.jpg', '2021-08-30 15:40:06', '2021-08-30 15:40:06');
INSERT INTO `coms_order_item` VALUES (255, 3, 304, 9.50, 2, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-30 17:17:35', '2021-08-30 17:17:35');
INSERT INTO `coms_order_item` VALUES (256, 4, 305, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-30 17:24:02', '2021-08-30 17:24:02');
INSERT INTO `coms_order_item` VALUES (257, 7, 306, 79.71, 1, ' 福春园锅包肉', '5cc5194a-59a9-42f4-a2e1-75df095e5d90.jpg', '2021-08-30 17:27:49', '2021-08-30 17:27:49');
INSERT INTO `coms_order_item` VALUES (258, 26, 307, 129.00, 1, '大希地 精品盒装国产整切西冷牛排套餐含酱包共750g（130g*5片+酱包20g*5包） 牛扒 优质牧场牛肉生鲜', '7c152a45-79f0-4e13-85d9-5c2dde016f97.jpg', '2021-08-30 17:34:20', '2021-08-30 17:34:20');
INSERT INTO `coms_order_item` VALUES (259, 14, 308, 5.50, 1, '柴火大院 长粒香 东北大米', '2373e76d-48ad-4f7d-8040-88b0ff5c3890.jpg', '2021-08-30 17:39:37', '2021-08-30 17:39:37');
INSERT INTO `coms_order_item` VALUES (260, 22, 309, 19.80, 1, '太太乐味精', 'fe003c97-262e-4aea-9ae6-10ca3af9c38e.jpg', '2021-08-30 17:39:37', '2021-08-30 17:39:37');
INSERT INTO `coms_order_item` VALUES (261, 4, 310, 7.50, 2, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-30 20:07:22', '2021-08-30 20:07:22');
INSERT INTO `coms_order_item` VALUES (262, 22, 311, 19.80, 1, '太太乐味精', 'fe003c97-262e-4aea-9ae6-10ca3af9c38e.jpg', '2021-08-30 20:22:35', '2021-08-30 20:22:35');
INSERT INTO `coms_order_item` VALUES (263, 8, 312, 6.80, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-08-30 21:04:52', '2021-08-30 21:04:52');
INSERT INTO `coms_order_item` VALUES (264, 4, 313, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-30 21:04:52', '2021-08-30 21:04:52');
INSERT INTO `coms_order_item` VALUES (265, 3, 314, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-30 21:10:08', '2021-08-30 21:10:08');
INSERT INTO `coms_order_item` VALUES (266, 4, 314, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-30 21:10:08', '2021-08-30 21:10:08');
INSERT INTO `coms_order_item` VALUES (267, 3, 315, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-30 21:10:45', '2021-08-30 21:10:45');
INSERT INTO `coms_order_item` VALUES (268, 4, 315, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-30 21:10:45', '2021-08-30 21:10:45');
INSERT INTO `coms_order_item` VALUES (269, 3, 316, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 09:13:23', '2021-08-31 09:13:23');
INSERT INTO `coms_order_item` VALUES (270, 4, 316, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 09:13:23', '2021-08-31 09:13:23');
INSERT INTO `coms_order_item` VALUES (271, 3, 317, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 09:16:30', '2021-08-31 09:16:30');
INSERT INTO `coms_order_item` VALUES (272, 3, 318, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 09:27:05', '2021-08-31 09:27:05');
INSERT INTO `coms_order_item` VALUES (273, 4, 319, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 09:29:39', '2021-08-31 09:29:39');
INSERT INTO `coms_order_item` VALUES (274, 3, 319, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 09:29:39', '2021-08-31 09:29:39');
INSERT INTO `coms_order_item` VALUES (275, 3, 320, 9.50, 2, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 09:35:49', '2021-08-31 09:35:49');
INSERT INTO `coms_order_item` VALUES (276, 4, 321, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 09:38:34', '2021-08-31 09:38:34');
INSERT INTO `coms_order_item` VALUES (277, 3, 321, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 09:38:34', '2021-08-31 09:38:34');
INSERT INTO `coms_order_item` VALUES (278, 3, 322, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 09:42:31', '2021-08-31 09:42:31');
INSERT INTO `coms_order_item` VALUES (279, 4, 323, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 09:46:45', '2021-08-31 09:46:45');
INSERT INTO `coms_order_item` VALUES (280, 3, 324, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 10:15:26', '2021-08-31 10:15:26');
INSERT INTO `coms_order_item` VALUES (281, 4, 324, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 10:15:26', '2021-08-31 10:15:26');
INSERT INTO `coms_order_item` VALUES (282, 3, 325, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 10:20:07', '2021-08-31 10:20:07');
INSERT INTO `coms_order_item` VALUES (283, 4, 325, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 10:20:07', '2021-08-31 10:20:07');
INSERT INTO `coms_order_item` VALUES (284, 4, 326, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 10:20:54', '2021-08-31 10:20:54');
INSERT INTO `coms_order_item` VALUES (285, 3, 326, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 10:20:54', '2021-08-31 10:20:54');
INSERT INTO `coms_order_item` VALUES (286, 4, 327, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 10:34:54', '2021-08-31 10:34:54');
INSERT INTO `coms_order_item` VALUES (287, 3, 327, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 10:34:54', '2021-08-31 10:34:54');
INSERT INTO `coms_order_item` VALUES (288, 3, 328, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 10:49:29', '2021-08-31 10:49:29');
INSERT INTO `coms_order_item` VALUES (289, 4, 328, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 10:49:29', '2021-08-31 10:49:29');
INSERT INTO `coms_order_item` VALUES (290, 8, 329, 6.80, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-08-31 10:49:29', '2021-08-31 10:49:29');
INSERT INTO `coms_order_item` VALUES (291, 8, 330, 6.80, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-08-31 11:18:46', '2021-08-31 11:18:46');
INSERT INTO `coms_order_item` VALUES (292, 4, 331, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 11:18:46', '2021-08-31 11:18:46');
INSERT INTO `coms_order_item` VALUES (293, 7, 331, 79.71, 1, ' 福春园锅包肉', '5cc5194a-59a9-42f4-a2e1-75df095e5d90.jpg', '2021-08-31 11:18:46', '2021-08-31 11:18:46');
INSERT INTO `coms_order_item` VALUES (294, 4, 332, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 11:20:31', '2021-08-31 11:20:31');
INSERT INTO `coms_order_item` VALUES (295, 8, 333, 6.80, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-08-31 11:20:31', '2021-08-31 11:20:31');
INSERT INTO `coms_order_item` VALUES (296, 3, 332, 9.50, 2, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 11:20:31', '2021-08-31 11:20:31');
INSERT INTO `coms_order_item` VALUES (297, 8, 334, 6.80, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-08-31 11:22:33', '2021-08-31 11:22:33');
INSERT INTO `coms_order_item` VALUES (298, 4, 335, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 11:22:33', '2021-08-31 11:22:33');
INSERT INTO `coms_order_item` VALUES (299, 3, 335, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 11:22:33', '2021-08-31 11:22:33');
INSERT INTO `coms_order_item` VALUES (300, 4, 336, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 11:23:10', '2021-08-31 11:23:10');
INSERT INTO `coms_order_item` VALUES (301, 8, 337, 6.80, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-08-31 11:23:10', '2021-08-31 11:23:10');
INSERT INTO `coms_order_item` VALUES (302, 3, 336, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 11:23:10', '2021-08-31 11:23:10');
INSERT INTO `coms_order_item` VALUES (303, 4, 338, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 11:26:00', '2021-08-31 11:26:00');
INSERT INTO `coms_order_item` VALUES (304, 8, 339, 6.80, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-08-31 11:26:00', '2021-08-31 11:26:00');
INSERT INTO `coms_order_item` VALUES (305, 3, 340, 9.50, 2, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 11:28:35', '2021-08-31 11:28:35');
INSERT INTO `coms_order_item` VALUES (306, 14, 341, 5.50, 2, '柴火大院 长粒香 东北大米', '2373e76d-48ad-4f7d-8040-88b0ff5c3890.jpg', '2021-08-31 11:28:35', '2021-08-31 11:28:35');
INSERT INTO `coms_order_item` VALUES (307, 4, 340, 7.50, 5, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 11:28:35', '2021-08-31 11:28:35');
INSERT INTO `coms_order_item` VALUES (308, 4, 342, 7.50, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 15:11:17', '2021-08-31 15:11:17');
INSERT INTO `coms_order_item` VALUES (309, 3, 342, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 15:11:17', '2021-08-31 15:11:17');
INSERT INTO `coms_order_item` VALUES (310, 8, 343, 6.80, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-08-31 15:11:17', '2021-08-31 15:11:17');
INSERT INTO `coms_order_item` VALUES (311, 3, 344, 9.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 15:17:03', '2021-08-31 15:17:03');
INSERT INTO `coms_order_item` VALUES (312, 6, 344, 50.00, 1, '王中王火腿肠', '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', '2021-08-31 15:17:03', '2021-08-31 15:17:03');
INSERT INTO `coms_order_item` VALUES (313, 8, 345, 6.60, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-08-31 16:23:06', '2021-08-31 16:23:06');
INSERT INTO `coms_order_item` VALUES (314, 4, 346, 7.50, 2, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 16:25:20', '2021-08-31 16:25:20');
INSERT INTO `coms_order_item` VALUES (315, 3, 346, 10.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 16:25:20', '2021-08-31 16:25:20');
INSERT INTO `coms_order_item` VALUES (316, 4, 347, 8.90, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-08-31 17:07:16', '2021-08-31 17:07:16');
INSERT INTO `coms_order_item` VALUES (317, 3, 348, 10.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-08-31 17:07:53', '2021-08-31 17:07:53');
INSERT INTO `coms_order_item` VALUES (318, 13, 349, 15.00, 1, '统一 红烧牛肉面', 'feaf95fb-9816-4a92-8a9e-98906b4bf98a.jpg', '2021-08-31 17:07:53', '2021-08-31 17:07:53');
INSERT INTO `coms_order_item` VALUES (319, 14, 349, 5.50, 1, '柴火大院 长粒香 东北大米', '2373e76d-48ad-4f7d-8040-88b0ff5c3890.jpg', '2021-08-31 17:07:54', '2021-08-31 17:07:54');
INSERT INTO `coms_order_item` VALUES (320, 3, 350, 10.50, 2, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-09-01 14:22:08', '2021-09-01 14:22:08');
INSERT INTO `coms_order_item` VALUES (321, 2, 351, 24.90, 1, '新东大盐酥鸡米花鸡块', '75820170-6381-4ff6-80f3-7270ac4564f7.jpg', '2021-09-01 14:25:08', '2021-09-01 14:25:08');
INSERT INTO `coms_order_item` VALUES (322, 22, 352, 19.80, 1, '太太乐味精', 'fe003c97-262e-4aea-9ae6-10ca3af9c38e.jpg', '2021-09-01 14:25:42', '2021-09-01 14:25:42');
INSERT INTO `coms_order_item` VALUES (323, 3, 353, 10.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-09-01 14:25:42', '2021-09-01 14:25:42');
INSERT INTO `coms_order_item` VALUES (324, 14, 354, 5.50, 1, '柴火大院 长粒香 东北大米', '2373e76d-48ad-4f7d-8040-88b0ff5c3890.jpg', '2021-09-01 14:25:42', '2021-09-01 14:25:42');
INSERT INTO `coms_order_item` VALUES (325, 16, 355, 15.00, 2, '三人行 金奖长粒香东北大米', '2aac87bf-495b-4ba7-85b8-232ba6ea9420.jpg', '2021-09-01 14:26:35', '2021-09-01 14:26:35');
INSERT INTO `coms_order_item` VALUES (326, 3, 356, 10.50, 3, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-09-01 14:26:35', '2021-09-01 14:26:35');
INSERT INTO `coms_order_item` VALUES (327, 14, 355, 5.50, 1, '柴火大院 长粒香 东北大米', '2373e76d-48ad-4f7d-8040-88b0ff5c3890.jpg', '2021-09-01 14:26:35', '2021-09-01 14:26:35');
INSERT INTO `coms_order_item` VALUES (328, 8, 355, 6.60, 2, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-09-01 14:26:35', '2021-09-01 14:26:35');
INSERT INTO `coms_order_item` VALUES (329, 1, 358, 222.00, 1, '恒都 澳洲 厚切 肥牛肉片', '4a1ba5d8-ffc6-4afb-8576-50f7bdce8428.jpg', '2021-09-01 15:54:53', '2021-09-01 15:54:53');
INSERT INTO `coms_order_item` VALUES (330, 3, 359, 10.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-09-01 21:03:45', '2021-09-01 21:03:45');
INSERT INTO `coms_order_item` VALUES (331, 14, 360, 5.50, 2, '柴火大院 长粒香 东北大米', '2373e76d-48ad-4f7d-8040-88b0ff5c3890.jpg', '2021-09-02 11:51:22', '2021-09-02 11:51:22');
INSERT INTO `coms_order_item` VALUES (332, 3, 361, 10.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-09-02 11:51:22', '2021-09-02 11:51:22');
INSERT INTO `coms_order_item` VALUES (333, 16, 360, 15.00, 1, '三人行 金奖长粒香东北大米', '2aac87bf-495b-4ba7-85b8-232ba6ea9420.jpg', '2021-09-02 11:51:22', '2021-09-02 11:51:22');
INSERT INTO `coms_order_item` VALUES (334, 3, 362, 10.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-09-03 17:43:39', '2021-09-03 17:43:39');
INSERT INTO `coms_order_item` VALUES (335, 28, 363, 12.00, 1, '味精111', 'b3a26c71-4a28-474e-94a3-43638bf62723.jpg', '2021-09-03 17:43:39', '2021-09-03 17:43:39');
INSERT INTO `coms_order_item` VALUES (336, 14, 364, 5.50, 1, '柴火大院 长粒香 东北大米', '2373e76d-48ad-4f7d-8040-88b0ff5c3890.jpg', '2021-09-03 17:44:42', '2021-09-03 17:44:42');
INSERT INTO `coms_order_item` VALUES (337, 21, 365, 15.00, 1, '太太乐鸡精', 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', '2021-09-03 17:44:42', '2021-09-03 17:44:42');
INSERT INTO `coms_order_item` VALUES (338, 8, 364, 6.60, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-09-03 17:44:42', '2021-09-03 17:44:42');
INSERT INTO `coms_order_item` VALUES (339, 2, 366, 24.90, 1, '新东大盐酥鸡米花鸡块', '75820170-6381-4ff6-80f3-7270ac4564f7.jpg', '2021-09-04 10:37:49', '2021-09-04 10:37:49');
INSERT INTO `coms_order_item` VALUES (340, 6, 366, 50.00, 1, '王中王火腿肠', '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', '2021-09-04 10:37:49', '2021-09-04 10:37:49');
INSERT INTO `coms_order_item` VALUES (341, 26, 366, 129.00, 1, '大希地 精品盒装国产整切西冷牛排套餐含酱包共750g（130g*5片+酱包20g*5包） 牛扒 优质牧场牛肉生鲜', 'ce773cf7-c9fc-4750-b3c5-115a5d311296.png', '2021-09-04 10:37:49', '2021-09-04 10:37:49');
INSERT INTO `coms_order_item` VALUES (342, 4, 367, 8.90, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-09-04 10:38:18', '2021-09-04 10:38:18');
INSERT INTO `coms_order_item` VALUES (343, 13, 368, 15.00, 1, '统一 红烧牛肉面', 'feaf95fb-9816-4a92-8a9e-98906b4bf98a.jpg', '2021-09-05 16:49:53', '2021-09-05 16:49:53');
INSERT INTO `coms_order_item` VALUES (344, 28, 369, 12.00, 1, '味精111', 'b3a26c71-4a28-474e-94a3-43638bf62723.jpg', '2021-09-05 16:49:53', '2021-09-05 16:49:53');
INSERT INTO `coms_order_item` VALUES (345, 21, 370, 15.00, 4, '太太乐鸡精', 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', '2021-09-05 17:29:46', '2021-09-05 17:29:46');
INSERT INTO `coms_order_item` VALUES (346, 13, 371, 15.00, 2, '统一 红烧牛肉面', 'feaf95fb-9816-4a92-8a9e-98906b4bf98a.jpg', '2021-09-06 08:34:18', '2021-09-06 08:34:18');
INSERT INTO `coms_order_item` VALUES (347, 8, 372, 6.60, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-09-06 09:39:28', '2021-09-06 09:39:28');
INSERT INTO `coms_order_item` VALUES (348, 21, 373, 15.00, 2, '太太乐鸡精', 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', '2021-09-06 09:39:28', '2021-09-06 09:39:28');
INSERT INTO `coms_order_item` VALUES (349, 3, 374, 10.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-09-06 16:06:57', '2021-09-06 16:06:57');
INSERT INTO `coms_order_item` VALUES (350, 14, 375, 5.50, 3, '柴火大院 长粒香 东北大米', '2373e76d-48ad-4f7d-8040-88b0ff5c3890.jpg', '2021-09-06 18:05:29', '2021-09-06 18:05:29');
INSERT INTO `coms_order_item` VALUES (351, 14, 376, 5.50, 3, '柴火大院 长粒香 东北大米', '2373e76d-48ad-4f7d-8040-88b0ff5c3890.jpg', '2021-09-06 19:50:35', '2021-09-06 19:50:35');
INSERT INTO `coms_order_item` VALUES (352, 13, 404, 26.00, 5, '统一 红烧牛肉面', 'feaf95fb-9816-4a92-8a9e-98906b4bf98a.jpg', '2021-09-07 14:52:02', '2021-09-07 14:52:02');
INSERT INTO `coms_order_item` VALUES (353, 22, 415, 0.80, 3, '太太乐味精', 'fe003c97-262e-4aea-9ae6-10ca3af9c38e.jpg', '2021-09-07 15:51:33', '2021-09-07 15:51:33');
INSERT INTO `coms_order_item` VALUES (354, 4, 416, 18.90, 2, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-09-07 15:51:33', '2021-09-07 15:51:33');
INSERT INTO `coms_order_item` VALUES (355, 22, 417, 0.80, 3, '太太乐味精', 'fe003c97-262e-4aea-9ae6-10ca3af9c38e.jpg', '2021-09-07 15:57:32', '2021-09-07 15:57:32');
INSERT INTO `coms_order_item` VALUES (357, 26, 433, 129.00, 1, '大希地 精品盒装国产整切西冷牛排套餐含酱包共750g（130g*5片+酱包20g*5包） 牛扒 优质牧场牛肉生鲜', 'ce773cf7-c9fc-4750-b3c5-115a5d311296.png', '2021-09-07 16:24:03', '2021-09-07 16:24:03');
INSERT INTO `coms_order_item` VALUES (358, 7, 433, 79.71, 1, ' 福春园锅包肉', '5cc5194a-59a9-42f4-a2e1-75df095e5d90.jpg', '2021-09-07 16:24:03', '2021-09-07 16:24:03');
INSERT INTO `coms_order_item` VALUES (359, 27, 434, 22.00, 1, '大棚油麦菜', 'b3e14f4b-7893-4873-87c1-e851b5099669.jfif', '2021-09-08 08:35:15', '2021-09-08 08:35:15');
INSERT INTO `coms_order_item` VALUES (360, 13, 435, 26.00, 1, '统一 红烧牛肉面', 'feaf95fb-9816-4a92-8a9e-98906b4bf98a.jpg', '2021-09-08 08:35:15', '2021-09-08 08:35:15');
INSERT INTO `coms_order_item` VALUES (361, 4, 436, 18.90, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-09-08 15:07:12', '2021-09-08 15:07:12');
INSERT INTO `coms_order_item` VALUES (362, 3, 436, 10.50, 1, '安井火锅油条', '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-09-08 15:07:12', '2021-09-08 15:07:12');
INSERT INTO `coms_order_item` VALUES (363, 28, 437, 12.00, 1, '味精111', 'b3a26c71-4a28-474e-94a3-43638bf62723.jpg', '2021-09-08 15:07:12', '2021-09-08 15:07:12');
INSERT INTO `coms_order_item` VALUES (364, 26, 436, 129.00, 1, '大希地 精品盒装国产整切西冷牛排套餐含酱包共750g（130g*5片+酱包20g*5包） 牛扒 优质牧场牛肉生鲜', 'ce773cf7-c9fc-4750-b3c5-115a5d311296.png', '2021-09-08 15:07:12', '2021-09-08 15:07:12');
INSERT INTO `coms_order_item` VALUES (366, 4, 439, 18.90, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-09-08 19:35:04', '2021-09-08 19:35:04');
INSERT INTO `coms_order_item` VALUES (367, 13, 440, 26.00, 9, '统一 红烧牛肉面', 'feaf95fb-9816-4a92-8a9e-98906b4bf98a.jpg', '2021-09-08 21:31:48', '2021-09-08 21:31:48');
INSERT INTO `coms_order_item` VALUES (368, 27, 441, 22.00, 1, '大棚油麦菜', 'b3e14f4b-7893-4873-87c1-e851b5099669.jfif', '2021-09-09 08:35:12', '2021-09-09 08:35:12');
INSERT INTO `coms_order_item` VALUES (369, 14, 442, 5.50, 2, '柴火大院 长粒香 东北大米', '2373e76d-48ad-4f7d-8040-88b0ff5c3890.jpg', '2021-09-09 08:38:34', '2021-09-09 08:38:34');
INSERT INTO `coms_order_item` VALUES (370, 16, 442, 15.00, 1, '三人行 金奖长粒香东北大米', '2aac87bf-495b-4ba7-85b8-232ba6ea9420.jpg', '2021-09-09 08:38:34', '2021-09-09 08:38:34');
INSERT INTO `coms_order_item` VALUES (371, 8, 443, 6.60, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-09-09 08:53:15', '2021-09-09 08:53:15');
INSERT INTO `coms_order_item` VALUES (372, 13, 444, 26.00, 1, '统一 红烧牛肉面', 'feaf95fb-9816-4a92-8a9e-98906b4bf98a.jpg', '2021-09-10 09:25:27', '2021-09-10 09:25:27');
INSERT INTO `coms_order_item` VALUES (373, 7, 445, 79.71, 1, ' 福春园锅包肉', '5cc5194a-59a9-42f4-a2e1-75df095e5d90.jpg', '2021-09-10 09:45:40', '2021-09-10 09:45:40');
INSERT INTO `coms_order_item` VALUES (374, 27, 446, 22.00, 1, '大棚油麦菜', 'b3e14f4b-7893-4873-87c1-e851b5099669.jfif', '2021-09-10 09:45:40', '2021-09-10 09:45:40');
INSERT INTO `coms_order_item` VALUES (375, 26, 447, 129.00, 1, '大希地 精品盒装国产整切西冷牛排套餐含酱包共750g（130g*5片+酱包20g*5包） 牛扒 优质牧场牛肉生鲜', 'ce773cf7-c9fc-4750-b3c5-115a5d311296.png', '2021-09-10 09:52:13', '2021-09-10 09:52:13');
INSERT INTO `coms_order_item` VALUES (376, 8, 448, 6.60, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-09-10 09:52:13', '2021-09-10 09:52:13');
INSERT INTO `coms_order_item` VALUES (377, 7, 447, 79.71, 1, ' 福春园锅包肉', '5cc5194a-59a9-42f4-a2e1-75df095e5d90.jpg', '2021-09-10 09:52:13', '2021-09-10 09:52:13');
INSERT INTO `coms_order_item` VALUES (378, 13, 448, 26.00, 1, '统一 红烧牛肉面', 'feaf95fb-9816-4a92-8a9e-98906b4bf98a.jpg', '2021-09-10 09:52:13', '2021-09-10 09:52:13');
INSERT INTO `coms_order_item` VALUES (379, 27, 449, 22.00, 1, '大棚油麦菜', 'b3e14f4b-7893-4873-87c1-e851b5099669.jfif', '2021-09-10 09:52:13', '2021-09-10 09:52:13');
INSERT INTO `coms_order_item` VALUES (380, 27, 450, 22.00, 1, '大棚油麦菜', 'b3e14f4b-7893-4873-87c1-e851b5099669.jfif', '2021-09-10 09:52:43', '2021-09-10 09:52:43');
INSERT INTO `coms_order_item` VALUES (381, 8, 458, 6.60, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-09-10 10:23:45', '2021-09-10 10:23:45');
INSERT INTO `coms_order_item` VALUES (382, 6, 459, 50.00, 1, '王中王火腿肠', '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', '2021-09-10 10:23:45', '2021-09-10 10:23:45');
INSERT INTO `coms_order_item` VALUES (383, 13, 458, 26.00, 2, '统一 红烧牛肉面', 'feaf95fb-9816-4a92-8a9e-98906b4bf98a.jpg', '2021-09-10 10:23:45', '2021-09-10 10:23:45');
INSERT INTO `coms_order_item` VALUES (384, 28, 460, 12.00, 1, '味精111', 'b3a26c71-4a28-474e-94a3-43638bf62723.jpg', '2021-09-10 10:23:45', '2021-09-10 10:23:45');
INSERT INTO `coms_order_item` VALUES (385, 24, 461, 9.90, 3, '梅花味精', '81f830fd-3846-4cd8-a08f-ea82058d559e.jpg', '2021-09-10 11:16:04', '2021-09-10 11:16:04');
INSERT INTO `coms_order_item` VALUES (386, 4, 462, 18.90, 1, '好记 木桶酿造津味有机老酱油', 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-09-10 11:16:04', '2021-09-10 11:16:04');
INSERT INTO `coms_order_item` VALUES (387, 8, 463, 6.60, 1, '十月稻田 长粒香 东北大米', '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-09-10 11:16:04', '2021-09-10 11:16:04');
INSERT INTO `coms_order_item` VALUES (388, 24, 464, 9.90, 1, '梅花味精', '81f830fd-3846-4cd8-a08f-ea82058d559e.jpg', '2022-02-24 16:08:06', '2022-02-24 16:08:06');
INSERT INTO `coms_order_item` VALUES (389, 6, 465, 50.00, 1, '王中王火腿肠', '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', '2022-02-24 16:08:06', '2022-02-24 16:08:06');

-- ----------------------------
-- Table structure for coms_price_list
-- ----------------------------
DROP TABLE IF EXISTS `coms_price_list`;
CREATE TABLE `coms_price_list`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `prod_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `prod_id` int NOT NULL,
  `sup_id` int NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_price_list
-- ----------------------------
INSERT INTO `coms_price_list` VALUES (1, 'aa', 12.00, 2, 2, '2021-07-03 21:56:09');
INSERT INTO `coms_price_list` VALUES (2, 'aa', 12.00, 2, 2, '2021-07-03 21:56:29');
INSERT INTO `coms_price_list` VALUES (3, 'bb', 111.00, 2, 3, '2021-07-03 21:56:50');
INSERT INTO `coms_price_list` VALUES (4, '合兆耗油肉片', 222.00, 1, 1, '2021-07-07 09:02:04');
INSERT INTO `coms_price_list` VALUES (5, '合兆耗油肉片啊啊', 222.00, 1, 1, '2021-07-07 09:02:41');
INSERT INTO `coms_price_list` VALUES (6, '合兆耗油肉片啊啊aaaa', 222.00, 1, 1, '2021-07-07 09:59:22');
INSERT INTO `coms_price_list` VALUES (7, '啊啊啊啊111', 15.00, 11, 1, '2021-07-07 10:07:52');
INSERT INTO `coms_price_list` VALUES (8, 'a', 15.00, 12, 1, '2021-07-07 18:43:40');
INSERT INTO `coms_price_list` VALUES (9, '红烧牛肉方便面', 15.00, 13, 1, '2021-07-07 18:47:36');
INSERT INTO `coms_price_list` VALUES (10, '吃你家大米', 15.00, 14, 1, '2021-07-08 08:32:25');
INSERT INTO `coms_price_list` VALUES (11, '油麦菜', 15.00, 15, 1, '2021-07-08 10:46:03');
INSERT INTO `coms_price_list` VALUES (12, '油麦菜', 222.00, 15, 1, '2021-07-08 10:46:59');
INSERT INTO `coms_price_list` VALUES (13, '油麦菜', 222.00, 15, 1, '2021-07-08 10:47:04');
INSERT INTO `coms_price_list` VALUES (14, '大米AA', 15.00, 16, 1, '2021-07-09 09:00:19');
INSERT INTO `coms_price_list` VALUES (15, 'dami', 15.00, 17, 1, '2021-07-09 09:21:06');
INSERT INTO `coms_price_list` VALUES (16, '11', 15.00, 18, 1, '2021-07-09 10:24:12');
INSERT INTO `coms_price_list` VALUES (17, 'asd', 15.00, 19, 1, '2021-07-09 10:46:35');
INSERT INTO `coms_price_list` VALUES (18, 'asd', 15.00, 20, 1, '2021-07-09 10:51:21');
INSERT INTO `coms_price_list` VALUES (19, '双汇鸡精', 15.00, 21, 25, '2021-07-11 20:16:48');
INSERT INTO `coms_price_list` VALUES (20, '伊品味精', 15.00, 22, 21, '2021-07-11 20:24:39');
INSERT INTO `coms_price_list` VALUES (21, 'abc', 15.00, 23, 26, '2021-07-12 08:32:02');
INSERT INTO `coms_price_list` VALUES (22, '好好好味精', 15.00, 24, 21, '2021-07-12 08:41:28');
INSERT INTO `coms_price_list` VALUES (23, '123', 15.00, 29, 26, '2021-09-04 08:33:07');

-- ----------------------------
-- Table structure for coms_product
-- ----------------------------
DROP TABLE IF EXISTS `coms_product`;
CREATE TABLE `coms_product`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `measure` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计数单位',
  `pd` date NOT NULL,
  `fd` int NOT NULL,
  `stock` int NOT NULL,
  `status` int NOT NULL COMMENT '商品状态,1-在售,2-下架,3删除',
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sup_cate_id` int NULL DEFAULT NULL COMMENT '分类id,对应coms_category表的主键',
  `pic_url` varchar(515) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fid_prod_cate`(`sup_cate_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10130 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_product
-- ----------------------------
INSERT INTO `coms_product` VALUES (1, '恒都 澳洲 厚切 肥牛肉片', 222.00, '/件', '2021-07-07', 365, 0, 1, '恒都', 22, '4a1ba5d8-ffc6-4afb-8576-50f7bdce8428.jpg', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (2, '新东大盐酥鸡米花鸡块', 24.90, '/件', '2021-06-07', 365, 149, 1, '新东大', 39, '75820170-6381-4ff6-80f3-7270ac4564f7.jpg', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (3, '安井火锅油条', 10.50, '/件', '2021-05-31', 365, 106, 1, '安井', 39, '993f5331-f07a-405d-af7d-7a1abd34583e.jfif', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (4, '好记 木桶酿造津味有机老酱油', 18.90, '/件', '2021-06-02', 20, 130, 1, '好记', 39, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (5, '好自然大白菜', 5.00, '/件', '2021-07-02', 3, 0, 1, '好自然', 41, 'e6c4c1bc-15b3-4250-a588-da6320e0a5c2.jfif', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (6, '王中王火腿肠', 50.00, '/件', '2021-07-02', 20, 35, 1, '双汇', 40, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (7, ' 福春园锅包肉', 79.71, '/件', '2021-07-07', 10, 90, 1, ' 福春园', 39, '5cc5194a-59a9-42f4-a2e1-75df095e5d90.jpg', '2021-07-07 09:21:56', '2021-07-12 11:38:20');
INSERT INTO `coms_product` VALUES (8, '十月稻田 长粒香 东北大米', 6.60, '/件', '2021-07-07', 180, 44, 1, '十月稻田', 27, '34edc0d1-2fa7-4025-89e7-71d88ae1c4f0.jpg', '2021-07-07 10:00:06', '2021-07-12 11:38:23');
INSERT INTO `coms_product` VALUES (13, '统一 红烧牛肉面', 26.00, '/件', '2021-07-07', 180, 38, 1, '统一', 28, 'feaf95fb-9816-4a92-8a9e-98906b4bf98a.jpg', '2021-07-07 18:47:36', '2021-07-12 11:38:25');
INSERT INTO `coms_product` VALUES (14, '柴火大院 长粒香 东北大米', 5.50, '/件', '2021-07-08', 365, 81, 0, '柴火大院', 27, '2373e76d-48ad-4f7d-8040-88b0ff5c3890.jpg', '2021-07-08 08:32:25', '2021-07-12 11:38:27');
INSERT INTO `coms_product` VALUES (16, '三人行 金奖长粒香东北大米', 15.00, '/件', '2021-07-09', 180, 88, 0, '三人行', 27, '2aac87bf-495b-4ba7-85b8-232ba6ea9420.jpg', '2021-07-09 09:00:19', '2021-07-12 11:38:34');
INSERT INTO `coms_product` VALUES (21, '太太乐鸡精', 15.00, '/件', '2021-07-11', 120, 0, 1, '太太乐', 25, 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', '2021-07-11 20:16:48', '2021-07-12 11:38:38');
INSERT INTO `coms_product` VALUES (22, '太太乐味精', 0.80, '/件', '2021-07-11', 180, 72, 1, '太太乐', 21, 'fe003c97-262e-4aea-9ae6-10ca3af9c38e.jpg', '2021-07-11 20:24:39', '2021-07-12 11:38:40');
INSERT INTO `coms_product` VALUES (24, '梅花味精', 9.90, '/件', '2021-07-12', 120, 94, 1, '梅花', 21, '81f830fd-3846-4cd8-a08f-ea82058d559e.jpg', '2021-07-12 08:41:28', '2021-07-12 11:38:42');
INSERT INTO `coms_product` VALUES (26, '大希地 精品盒装国产整切西冷牛排套餐含酱包共750g（130g*5片+酱包20g*5包） 牛扒 优质牧场牛肉生鲜', 129.00, '/件', '2021-08-13', 21, 11, 1, '第三方', 32, 'ce773cf7-c9fc-4750-b3c5-115a5d311296.png', '2021-08-09 01:54:43', '2021-08-09 01:54:43');
INSERT INTO `coms_product` VALUES (27, '大棚油麦菜', 22.00, '/件', '2021-07-07', 3, 71, 1, '大棚集团', 41, 'b3e14f4b-7893-4873-87c1-e851b5099669.jfif', '2021-08-10 00:23:02', '2021-08-10 00:23:02');
INSERT INTO `coms_product` VALUES (28, '味精111', 12.00, '/件', '2021-08-21', 21, 196, 1, '味精111', 21, 'b3a26c71-4a28-474e-94a3-43638bf62723.jpg', '2021-08-30 09:46:49', '2021-08-30 09:46:49');
INSERT INTO `coms_product` VALUES (29, '123', 15.00, '/件', '2021-09-04', 123, 100, 0, '123', 26, '2fa86a81-20c6-437b-b2eb-bd728be598be.png', '2021-09-04 08:33:07', '2021-09-04 08:33:07');
INSERT INTO `coms_product` VALUES (30, 'asd', 1.00, '/件', '2021-09-05', 1, 100, 1, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (31, 'asd', 2.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (32, 'asd', 3.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (33, 'asd', 4.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (34, 'asd', 5.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (35, 'asd', 6.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '4a1ba5d8-ffc6-4afb-8576-50f7bdce8428.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (36, 'asd', 7.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (37, 'asd', 8.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (38, 'asd', 9.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (39, 'asd', 10.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (40, 'asd', 11.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (41, 'asd', 12.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (42, 'asd', 13.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (43, 'asd', 14.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '4a1ba5d8-ffc6-4afb-8576-50f7bdce8428.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (44, 'asd', 15.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (45, 'asd', 16.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (46, 'asd', 17.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (47, 'asd', 18.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (48, 'asd', 19.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (49, 'asd', 20.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (50, 'asd', 21.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (51, 'asd', 22.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '4a1ba5d8-ffc6-4afb-8576-50f7bdce8428.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (52, 'asd', 23.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (53, 'asd', 24.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (54, 'asd', 25.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (55, 'asd', 26.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (56, 'asd', 27.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (57, 'asd', 28.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (58, 'asd', 29.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (59, 'asd', 30.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '4a1ba5d8-ffc6-4afb-8576-50f7bdce8428.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (60, 'asd', 31.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (61, 'asd', 32.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (62, 'asd', 33.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (63, 'asd', 34.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (64, 'asd', 35.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (65, 'asd', 36.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (66, 'asd', 37.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (67, 'asd', 38.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '4a1ba5d8-ffc6-4afb-8576-50f7bdce8428.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (68, 'asd', 39.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (69, 'asd', 40.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (70, 'asd', 41.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (71, 'asd', 42.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (72, 'asd', 43.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (73, 'asd', 44.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (74, 'asd', 45.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (75, 'asd', 46.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '4a1ba5d8-ffc6-4afb-8576-50f7bdce8428.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (76, 'asd', 47.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (77, 'asd', 48.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (78, 'asd', 49.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (79, 'asd', 50.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (80, 'asd', 51.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (81, 'asd', 52.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (82, 'asd', 53.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (83, 'asd', 54.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '4a1ba5d8-ffc6-4afb-8576-50f7bdce8428.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (84, 'asd', 55.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (85, 'asd', 56.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (86, 'asd', 57.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (87, 'asd', 58.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (88, 'asd', 59.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (89, 'asd', 60.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (90, 'asd', 61.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (91, 'asd', 62.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '4a1ba5d8-ffc6-4afb-8576-50f7bdce8428.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (92, 'asd', 63.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (93, 'asd', 64.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (94, 'asd', 65.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (95, 'asd', 66.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'e4957851-0b1d-4a88-b33e-087e02d8cf22.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (96, 'asd', 67.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (97, 'asd', 68.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '32f2c955-ccf7-4285-9c88-2418cc60112c.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (98, 'asd', 69.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (99, 'asd', 70.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, '4a1ba5d8-ffc6-4afb-8576-50f7bdce8428.jpg', NULL, NULL);
INSERT INTO `coms_product` VALUES (100, 'asd', 71.00, NULL, '2021-09-05', 1, 100, 0, 'dd', NULL, 'a4ecd0f4-a47f-4fc7-af31-030998308c54.jpg', NULL, NULL);

-- ----------------------------
-- Table structure for coms_stall
-- ----------------------------
DROP TABLE IF EXISTS `coms_stall`;
CREATE TABLE `coms_stall`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_stall
-- ----------------------------
INSERT INTO `coms_stall` VALUES (1, '档口一', '12345678', '13520151984', 0, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_stall` VALUES (2, '档口二', '12345678', '13643629458', 1, '2021-07-08 10:00:24', '2021-08-30 11:10:32');
INSERT INTO `coms_stall` VALUES (3, '档口三', '12345678', '13104383881', 1, '2021-07-08 10:00:24', '2021-08-31 16:05:13');
INSERT INTO `coms_stall` VALUES (4, '档口四', '12345678', '13382020927', 1, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_stall` VALUES (7, '测试档口1', '12345678', '13793623937', 1, '2021-08-15 23:02:25', '2021-08-15 23:02:25');
INSERT INTO `coms_stall` VALUES (8, '测试档口2', '12345678', '13132009072', 1, '2021-08-15 23:06:59', '2021-08-15 23:06:59');
INSERT INTO `coms_stall` VALUES (9, '测试档口3', '12345678', '13917624263', 0, '2021-08-15 23:07:20', '2021-08-15 23:07:20');
INSERT INTO `coms_stall` VALUES (10, '测试档口4', '12345678', '13241566526', 1, '2021-08-15 23:07:34', '2021-08-15 23:07:34');
INSERT INTO `coms_stall` VALUES (11, '测试档口5', '12345678', '13627359637', 1, '2021-08-15 23:07:48', '2021-08-15 23:07:48');
INSERT INTO `coms_stall` VALUES (12, '测试档口6', '12345678', '13564973063', 1, '2021-09-10 11:26:01', '2021-09-10 11:26:12');

-- ----------------------------
-- Table structure for coms_sup_category
-- ----------------------------
DROP TABLE IF EXISTS `coms_sup_category`;
CREATE TABLE `coms_sup_category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `cate_id` int NOT NULL,
  `sup_id` int NOT NULL,
  `cate_l1_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_sup_category
-- ----------------------------
INSERT INTO `coms_sup_category` VALUES (16, 23, 4, 4);
INSERT INTO `coms_sup_category` VALUES (17, 24, 4, 4);
INSERT INTO `coms_sup_category` VALUES (18, 25, 4, 4);
INSERT INTO `coms_sup_category` VALUES (19, 26, 4, 4);
INSERT INTO `coms_sup_category` VALUES (20, 27, 4, 4);
INSERT INTO `coms_sup_category` VALUES (21, 17, 1, 6);
INSERT INTO `coms_sup_category` VALUES (22, 18, 1, 6);
INSERT INTO `coms_sup_category` VALUES (23, 19, 1, 6);
INSERT INTO `coms_sup_category` VALUES (24, 20, 1, 6);
INSERT INTO `coms_sup_category` VALUES (25, 21, 1, 6);
INSERT INTO `coms_sup_category` VALUES (26, 22, 1, 6);
INSERT INTO `coms_sup_category` VALUES (27, 23, 2, 4);
INSERT INTO `coms_sup_category` VALUES (28, 24, 2, 4);
INSERT INTO `coms_sup_category` VALUES (29, 25, 2, 4);
INSERT INTO `coms_sup_category` VALUES (30, 26, 2, 4);
INSERT INTO `coms_sup_category` VALUES (31, 27, 2, 4);
INSERT INTO `coms_sup_category` VALUES (32, 28, 3, 8);
INSERT INTO `coms_sup_category` VALUES (33, 29, 3, 8);
INSERT INTO `coms_sup_category` VALUES (34, 30, 3, 8);
INSERT INTO `coms_sup_category` VALUES (35, 31, 3, 8);
INSERT INTO `coms_sup_category` VALUES (36, 32, 3, 8);
INSERT INTO `coms_sup_category` VALUES (37, 33, 3, 8);
INSERT INTO `coms_sup_category` VALUES (38, 34, 3, 8);
INSERT INTO `coms_sup_category` VALUES (39, 35, 3, 8);
INSERT INTO `coms_sup_category` VALUES (40, 36, 3, 8);
INSERT INTO `coms_sup_category` VALUES (41, 37, 5, 7);
INSERT INTO `coms_sup_category` VALUES (42, 38, 5, 7);
INSERT INTO `coms_sup_category` VALUES (43, 39, 5, 7);
INSERT INTO `coms_sup_category` VALUES (44, 40, 5, 7);
INSERT INTO `coms_sup_category` VALUES (45, 37, 4, 7);
INSERT INTO `coms_sup_category` VALUES (46, 38, 4, 7);
INSERT INTO `coms_sup_category` VALUES (47, 39, 4, 7);
INSERT INTO `coms_sup_category` VALUES (48, 40, 4, 7);
INSERT INTO `coms_sup_category` VALUES (49, 15, 15, 6);
INSERT INTO `coms_sup_category` VALUES (50, 16, 15, 6);
INSERT INTO `coms_sup_category` VALUES (51, 17, 15, 6);
INSERT INTO `coms_sup_category` VALUES (52, 18, 15, 6);
INSERT INTO `coms_sup_category` VALUES (53, 19, 15, 6);
INSERT INTO `coms_sup_category` VALUES (54, 20, 15, 6);
INSERT INTO `coms_sup_category` VALUES (55, 21, 15, 6);
INSERT INTO `coms_sup_category` VALUES (56, 22, 15, 6);
INSERT INTO `coms_sup_category` VALUES (57, 42, 15, 41);
INSERT INTO `coms_sup_category` VALUES (58, 43, 15, 41);
INSERT INTO `coms_sup_category` VALUES (59, 44, 15, 41);
INSERT INTO `coms_sup_category` VALUES (60, 49, 15, 41);
INSERT INTO `coms_sup_category` VALUES (74, 28, 7, 8);
INSERT INTO `coms_sup_category` VALUES (75, 29, 7, 8);
INSERT INTO `coms_sup_category` VALUES (76, 30, 7, 8);
INSERT INTO `coms_sup_category` VALUES (77, 31, 7, 8);
INSERT INTO `coms_sup_category` VALUES (78, 32, 7, 8);
INSERT INTO `coms_sup_category` VALUES (79, 33, 7, 8);
INSERT INTO `coms_sup_category` VALUES (80, 34, 7, 8);
INSERT INTO `coms_sup_category` VALUES (81, 35, 7, 8);
INSERT INTO `coms_sup_category` VALUES (82, 36, 7, 8);
INSERT INTO `coms_sup_category` VALUES (83, 42, 7, 41);
INSERT INTO `coms_sup_category` VALUES (84, 43, 7, 41);
INSERT INTO `coms_sup_category` VALUES (85, 44, 7, 41);
INSERT INTO `coms_sup_category` VALUES (86, 49, 7, 41);
INSERT INTO `coms_sup_category` VALUES (87, 28, 19, 8);
INSERT INTO `coms_sup_category` VALUES (88, 29, 19, 8);
INSERT INTO `coms_sup_category` VALUES (89, 30, 19, 8);
INSERT INTO `coms_sup_category` VALUES (90, 31, 19, 8);
INSERT INTO `coms_sup_category` VALUES (91, 32, 19, 8);
INSERT INTO `coms_sup_category` VALUES (92, 33, 19, 8);
INSERT INTO `coms_sup_category` VALUES (93, 34, 19, 8);
INSERT INTO `coms_sup_category` VALUES (94, 35, 19, 8);
INSERT INTO `coms_sup_category` VALUES (95, 36, 19, 8);
INSERT INTO `coms_sup_category` VALUES (96, 23, 8, 4);
INSERT INTO `coms_sup_category` VALUES (97, 24, 8, 4);
INSERT INTO `coms_sup_category` VALUES (98, 25, 8, 4);
INSERT INTO `coms_sup_category` VALUES (99, 26, 8, 4);
INSERT INTO `coms_sup_category` VALUES (100, 27, 8, 4);
INSERT INTO `coms_sup_category` VALUES (101, 42, 8, 41);
INSERT INTO `coms_sup_category` VALUES (102, 43, 8, 41);
INSERT INTO `coms_sup_category` VALUES (103, 44, 8, 41);
INSERT INTO `coms_sup_category` VALUES (104, 49, 8, 41);

-- ----------------------------
-- Table structure for coms_supplier
-- ----------------------------
DROP TABLE IF EXISTS `coms_supplier`;
CREATE TABLE `coms_supplier`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_supplier
-- ----------------------------
INSERT INTO `coms_supplier` VALUES (1, '供应商1', '12345678', '1331130704', '天津市南开区', 1, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (2, '供应商2', '12345678', '13276346778', '天津市西青区', 1, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (3, '供应商3', '12345678', '1353393715', '天津市东丽区', 0, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (4, '供应商4', '12345678', '13597397825', '天津市北辰区', 1, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (5, '供应商5', '12345678', '13229367195', '天津市红桥区', 1, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (6, '供应商6', '12345678', '13708634724', '天津市河西区', 0, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (7, 'test', '12345678', '13294692430', '天津市XX区', 0, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (8, '测试供应商1', '12345678', '13942633379', '天津市1', 1, '2021-07-08 11:05:54', '2021-07-08 11:21:22');
INSERT INTO `coms_supplier` VALUES (9, '供应商7', '12345678', '13485406693', '天津是津南区', 1, '2021-07-08 16:47:21', '2021-07-08 16:47:25');
INSERT INTO `coms_supplier` VALUES (10, '供应商8', '12345678', '13196905323', '天津市北辰区', 1, '2021-07-08 16:48:38', '2021-07-08 16:48:53');
INSERT INTO `coms_supplier` VALUES (11, '供应商9', '12345678', '13194459972', '天津市红桥区', 1, '2021-07-08 16:49:48', '2021-07-08 16:49:52');
INSERT INTO `coms_supplier` VALUES (12, '供应商10', '12345678', '1396000497', '天津市东丽区', 1, '2021-07-08 16:50:29', '2021-07-08 16:50:32');
INSERT INTO `coms_supplier` VALUES (13, '测试供应商2', '12345678', '1395269457', '北京市', 1, '2021-08-20 14:54:38', '2021-08-20 14:54:38');
INSERT INTO `coms_supplier` VALUES (15, '测试供应商3', '12345678', '1362691656', '北京市', 0, '2021-08-20 15:08:13', '2021-08-20 15:08:13');
INSERT INTO `coms_supplier` VALUES (17, '测试供应商4', '12345678', '13282466626', '北京市', 1, '2021-08-21 22:54:22', '2021-08-21 22:54:22');
INSERT INTO `coms_supplier` VALUES (18, '测试供应商5', '12345678', '13503297689', '北京市', 1, '2021-08-21 22:54:31', '2021-08-21 22:54:31');
INSERT INTO `coms_supplier` VALUES (19, '测试供应商6', '12345678', '13370825333', '北京市', 1, '2021-08-21 22:55:06', '2021-08-21 22:55:06');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '朱元璋', '语文', '60');
INSERT INTO `user` VALUES (2, '朱元璋', '数学', '70');
INSERT INTO `user` VALUES (3, '朱元璋', '英语', '80');
INSERT INTO `user` VALUES (4, '秦始皇', '语文', '90');
INSERT INTO `user` VALUES (5, '秦始皇', '数学', '100');
INSERT INTO `user` VALUES (1, '朱元璋', '语文', '60');
INSERT INTO `user` VALUES (2, '朱元璋', '数学', '70');
INSERT INTO `user` VALUES (3, '朱元璋', '英语', '80');
INSERT INTO `user` VALUES (4, '秦始皇', '语文', '90');
INSERT INTO `user` VALUES (5, '秦始皇', '数学', '100');
INSERT INTO `user` VALUES (1, '朱元璋', '语文', '60');
INSERT INTO `user` VALUES (2, '朱元璋', '数学', '70');
INSERT INTO `user` VALUES (3, '朱元璋', '英语', '80');
INSERT INTO `user` VALUES (4, '秦始皇', '语文', '90');
INSERT INTO `user` VALUES (5, '秦始皇', '数学', '100');
INSERT INTO `user` VALUES (1, '朱元璋', '语文', '60');
INSERT INTO `user` VALUES (2, '朱元璋', '数学', '70');
INSERT INTO `user` VALUES (3, '朱元璋', '英语', '80');
INSERT INTO `user` VALUES (4, '秦始皇', '语文', '90');
INSERT INTO `user` VALUES (5, '秦始皇', '数学', '100');
INSERT INTO `user` VALUES (1, '朱元璋', '语文', '60');
INSERT INTO `user` VALUES (2, '朱元璋', '数学', '70');
INSERT INTO `user` VALUES (3, '朱元璋', '英语', '80');
INSERT INTO `user` VALUES (4, '秦始皇', '语文', '90');
INSERT INTO `user` VALUES (5, '秦始皇', '数学', '100');

-- ----------------------------
-- View structure for orderitemview
-- ----------------------------
DROP VIEW IF EXISTS `orderitemview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `orderitemview` AS select `ord`.`prod_name` AS `prod_name`,`ord`.`num` AS `num`,`prod`.`pd` AS `pd`,`prod`.`fd` AS `fd`,`prod`.`brand` AS `brand`,`orp`.`sup_name` AS `sup_name`,`orp`.`sup_id` AS `sup_id`,`ord`.`create_time` AS `create_time` from ((`coms_order_item` `ord` left join `coms_order` `orp` on((`ord`.`order_id` = `orp`.`id`))) left join `coms_product` `prod` on((`ord`.`prod_id` = `prod`.`id`)));

SET FOREIGN_KEY_CHECKS = 1;
