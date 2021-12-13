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

 Date: 08/12/2021 14:36:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coms_admin
-- ----------------------------
DROP TABLE IF EXISTS `coms_admin`;
CREATE TABLE `coms_admin`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1009 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `coms_category` VALUES (4, 0, '主食', '78f962f6-9427-483f-b7aa-67933ea54701.png', '2021-07-08 10:00:24', '2021-12-08 09:34:14');
INSERT INTO `coms_category` VALUES (5, 0, '食用油', '320b1dd9-779c-4adc-8581-9aee9c536df7.png', '2021-07-08 10:00:24', '2021-12-08 09:35:24');
INSERT INTO `coms_category` VALUES (6, 0, '调料', '7dfd968c-9fe1-4014-b856-056d22b45590.png', '2021-07-08 10:00:24', '2021-12-08 09:35:32');
INSERT INTO `coms_category` VALUES (7, 0, '蔬菜', '294b6dc6-3fe3-443a-9b1c-3984174fd8b2.png', '2021-07-08 10:00:24', '2021-12-08 09:35:47');
INSERT INTO `coms_category` VALUES (8, 0, '肉/蛋', '0172a3e8-992e-4fc1-af60-c5a22ed2d7c1.png', '2021-07-08 10:00:24', '2021-12-08 09:36:07');
INSERT INTO `coms_category` VALUES (9, 0, '其他', 'e740e395-73e4-4d02-a3f8-f5b64aebdc62.png', '2021-07-08 10:00:24', '2021-12-08 09:36:16');
INSERT INTO `coms_category` VALUES (15, 6, '白糖', 'rice.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (16, 6, '白醋', 'rice.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (17, 6, '味精', 'rice.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (18, 6, '沙司', 'rice.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (19, 6, '面酱', 'rice.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (20, 6, '番茄酱', 'rice.png', '2021-08-13 23:27:11', '2021-08-13 23:27:14');
INSERT INTO `coms_category` VALUES (21, 6, '鸡精', 'rice.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (22, 6, '大料', 'rice.png', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_category` VALUES (23, 4, '大米', '3e621378-6327-49cf-9a8f-5dd750324793.png', '2021-07-08 10:00:24', '2021-12-08 09:39:54');
INSERT INTO `coms_category` VALUES (24, 4, '面', '3d3a90b4-529b-4a44-b772-41e9b9c46f1a.png', '2021-07-08 10:00:24', '2021-12-08 09:37:55');
INSERT INTO `coms_category` VALUES (25, 4, '杂粮', '2947c3f0-5a12-4984-a956-91103c297218.png', '2021-07-08 10:00:24', '2021-12-08 09:39:08');
INSERT INTO `coms_category` VALUES (26, 4, '豆类', 'fd5f837d-612e-4650-9e09-f580a679ef7f.png', '2021-07-08 10:00:24', '2021-12-08 09:38:20');
INSERT INTO `coms_category` VALUES (27, 4, '薯类', 'a0896b69-1a4a-4b87-afd2-b9fa33b50fe3.png', '2021-07-08 10:00:24', '2021-12-08 09:38:31');
INSERT INTO `coms_category` VALUES (28, 8, '牛肉', 'rice.png', '2021-07-12 10:20:57', '2021-07-12 10:21:01');
INSERT INTO `coms_category` VALUES (29, 8, '猪肉', '17d6e526-21c8-4445-89f1-49678de5c801.png', '2021-07-12 10:25:55', '2021-12-08 09:36:39');
INSERT INTO `coms_category` VALUES (30, 8, '鸡肉', '252df795-1b4d-41ba-a6ae-14ba5f8ed0a1.png', '2021-07-12 10:26:34', '2021-12-08 09:36:47');
INSERT INTO `coms_category` VALUES (31, 8, '鸭肉', 'rice.png', '2021-07-12 10:27:16', '2021-07-12 10:27:18');
INSERT INTO `coms_category` VALUES (32, 8, '鱼肉', 'rice.png', '2021-07-12 10:28:50', '2021-07-12 10:28:50');
INSERT INTO `coms_category` VALUES (33, 8, '羊肉', 'rice.png', '2021-07-12 10:52:49', '2021-07-12 10:52:52');
INSERT INTO `coms_category` VALUES (34, 8, '蛋', 'rice.png', '2021-07-12 10:53:58', '2021-07-12 10:54:00');
INSERT INTO `coms_category` VALUES (35, 8, '冷冻食品', 'rice.png', '2021-07-12 10:57:29', '2021-07-12 10:57:31');
INSERT INTO `coms_category` VALUES (36, 8, '熟食', 'rice.png', '2021-07-12 11:04:35', '2021-07-12 11:04:35');
INSERT INTO `coms_category` VALUES (37, 7, '叶菜', 'rice.png', '2021-07-12 11:11:58', '2021-07-12 11:11:58');
INSERT INTO `coms_category` VALUES (38, 7, '豆芽', 'rice.png', '2021-07-12 11:12:32', '2021-07-12 11:12:32');
INSERT INTO `coms_category` VALUES (39, 7, '葱蒜', 'rice.png', '2021-07-12 11:13:29', '2021-07-12 11:13:29');
INSERT INTO `coms_category` VALUES (40, 7, '瓜果', 'rice.png', '2021-07-12 11:15:03', '2021-07-12 11:15:09');
INSERT INTO `coms_category` VALUES (41, 0, '鸡肉', 'a2e2736c-a1f2-4a96-bd11-0a73fcbc403f.png', '2021-08-14 16:45:52', '2021-12-08 09:40:17');
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
  `ordeitem_id` int NOT NULL,
  `stall_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_err_order
-- ----------------------------
INSERT INTO `coms_err_order` VALUES (1, 'wewew', 'w', 1, 0, NULL);
INSERT INTO `coms_err_order` VALUES (2, 'asdfasd', 'null', 234, 0, NULL);
INSERT INTO `coms_err_order` VALUES (3, '有毒', 'null', 232, 0, NULL);
INSERT INTO `coms_err_order` VALUES (4, '有毒', 'null', 229, 0, NULL);
INSERT INTO `coms_err_order` VALUES (5, '不阿红', 'null', 228, 0, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 304 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_order
-- ----------------------------
INSERT INTO `coms_order` VALUES (302, 11, 50.00, 1, '2021-12-08 09:47:22', '2021-12-08 09:47:22', '供应商3', '13562458524', 3);
INSERT INTO `coms_order` VALUES (303, 2, 59.50, 1, '2021-12-08 11:24:54', '2021-12-08 11:24:54', '供应商3', '13562458524', 3);

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
) ENGINE = InnoDB AUTO_INCREMENT = 256 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_order_item
-- ----------------------------
INSERT INTO `coms_order_item` VALUES (253, 6, 302, 50.00, 1, '王中王火腿肠', 'b45ec057-ccc1-4ced-80bf-c5af2b6b207a.jpg', '2021-12-08 09:47:22', '2021-12-08 09:47:22');
INSERT INTO `coms_order_item` VALUES (254, 6, 303, 50.00, 1, '王中王火腿肠', 'b45ec057-ccc1-4ced-80bf-c5af2b6b207a.jpg', '2021-12-08 11:24:54', '2021-12-08 11:24:54');
INSERT INTO `coms_order_item` VALUES (255, 3, 303, 9.50, 1, '安井火锅油条', '492cebef-303f-4367-8384-981dd966de0d.jfif', '2021-12-08 11:24:54', '2021-12-08 11:24:54');

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
INSERT INTO `coms_price_list` VALUES (23, '面粉', 50.00, 31, 13, '2021-12-07 13:16:17');

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
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_product
-- ----------------------------
INSERT INTO `coms_product` VALUES (1, '恒都 澳洲 厚切 肥牛肉片', 222.00, 'test', '2021-07-07', 365, 0, 1, '恒都', 22, '1995f479-42ae-4d27-9991-ae3c693543fc.jpg', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (2, '新东大盐酥鸡米花鸡块', 24.90, 'test', '2021-06-07', 365, 151, 1, '新东大', 39, '75820170-6381-4ff6-80f3-7270ac4564f7.jpg', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (3, '安井火锅油条', 9.50, 'test', '2021-05-31', 365, 0, 1, '安井', 39, '492cebef-303f-4367-8384-981dd966de0d.jfif', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (4, '好记 木桶酿造津味有机老酱油', 7.50, 'test', '2021-06-02', 20, 0, 1, '好记', 39, '8ccd5da9-6262-4862-a5e9-ac606adbfb90.jpg', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (5, '好自然大白菜', 5.00, 'test', '2021-07-02', 3, 0, 1, '好自然', 41, 'cd75a2cc-5116-4cac-ac0b-342489a3fcc2.jfif', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (6, '王中王火腿肠', 50.00, 'test', '2021-07-02', 20, 37, 1, '双汇', 40, 'b45ec057-ccc1-4ced-80bf-c5af2b6b207a.jpg', '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_product` VALUES (7, ' 福春园锅包肉', 79.71, 'test', '2021-07-07', 10, 95, 1, ' 福春园', 39, 'a4603234-238a-4e2e-9b62-fa5757855f57.jpg', '2021-07-07 09:21:56', '2021-07-12 11:38:20');
INSERT INTO `coms_product` VALUES (8, '十月稻田 长粒香 东北大米', 6.80, 'test', '2021-07-07', 180, 61, 0, '十月稻田', 27, 'f0db2433-bf27-4455-95a2-4890dd824ea9.jpg', '2021-07-07 10:00:06', '2021-07-12 11:38:23');
INSERT INTO `coms_product` VALUES (13, '统一 红烧牛肉面', 15.00, 'test', '2021-07-07', 180, 61, 0, '统一', 28, 'be5f1316-07d1-4696-ac27-fcf728b5680b.jpg', '2021-07-07 18:47:36', '2021-07-12 11:38:25');
INSERT INTO `coms_product` VALUES (14, '柴火大院 长粒香 东北大米', 5.50, 'test', '2021-07-08', 365, 96, 1, '柴火大院', 27, 'bfbbb50f-e988-4234-9473-30f44a986621.jpg', '2021-07-08 08:32:25', '2021-07-12 11:38:27');
INSERT INTO `coms_product` VALUES (15, '大棚油麦菜', 15.00, 'test', '2021-09-08', 5, 30, 0, '统一', 41, 'c7b392779a07.jfif', '2021-09-08 13:53:54', '2021-09-08 13:53:57');
INSERT INTO `coms_product` VALUES (16, '三人行 金奖长粒香东北大米', 15.00, 'test1', '2021-07-09', 180, 92, 0, '三人行', 27, '5f5e7d39-11d9-4cdb-84fc-2e598e61e9ff.jpg', '2021-07-09 09:00:19', '2021-07-12 11:38:34');
INSERT INTO `coms_product` VALUES (21, '双汇鸡精', 15.00, NULL, '2021-07-11', 120, 95, 0, '太太乐', 25, 'bae5fb6b-df61-4a71-91e4-3d731b8157ad.jpg', '2021-07-11 20:16:48', '2021-07-12 11:38:38');
INSERT INTO `coms_product` VALUES (22, '太太乐味精', 19.80, NULL, '2021-07-11', 180, 81, 0, '太太乐', 21, '59fc9886-ddaf-4f43-99c8-bf18dfb579d6.jpg', '2021-07-11 20:24:39', '2021-07-12 11:38:40');
INSERT INTO `coms_product` VALUES (24, '梅花味精', 9.90, '袋', '2021-12-07', 120, 98, 1, '梅花', 107, 'dc66820d-510c-4acf-a7ed-e32b210c430e.jpg', '2021-07-12 08:41:28', '2021-07-12 11:38:42');
INSERT INTO `coms_product` VALUES (26, '大希地 精品盒装国产整切西冷牛排套餐含酱包共750g（130g*5片+酱包20g*5包） 牛扒 优质牧场牛肉生鲜', 129.00, NULL, '2021-08-13', 21, 16, 0, '第三方', 32, '8c349515-108c-465a-a470-57f31ea59588.jpg', '2021-08-09 01:54:43', '2021-08-09 01:54:43');
INSERT INTO `coms_product` VALUES (27, '大棚油麦菜', 22.00, NULL, '2021-07-07', 3, 76, 0, '大棚集团', 41, 'f51a9829-650a-4288-8f33-c7b392779a07.jfif', '2021-08-10 00:23:02', '2021-08-10 00:23:02');
INSERT INTO `coms_product` VALUES (29, '123', 123.00, NULL, '2021-12-23', 312, 123, 0, '312', 21, 'c4da53ea-a902-4f5b-a484-4189385f68b3.jpg', '2021-12-07 12:36:43', '2021-12-07 12:36:43');
INSERT INTO `coms_product` VALUES (30, '12', 123.00, NULL, '2021-12-16', 32, 123, 0, '231', 52, '21378008-dbac-4b3b-9914-86687273ed76.jpg', '2021-12-07 12:59:33', '2021-12-07 12:59:33');
INSERT INTO `coms_product` VALUES (31, '面粉', 50.00, '袋', '2021-12-07', 12, 100, 0, '顺发面粉厂', 101, '20211271371_6kut0vv0j.jpg', '2021-12-07 13:07:02', '2021-12-07 13:07:02');
INSERT INTO `coms_product` VALUES (32, '天立老醋', 15.00, '桶', '2021-11-01', 300, 200, 0, '独流2厂', 106, '2021127131025_5i0n8wiq1.jpg', '2021-12-07 13:10:26', '2021-12-07 13:10:26');

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
INSERT INTO `coms_stall` VALUES (2, '档口二', '123', '12563548524', 1, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_stall` VALUES (3, '档口三', '123', '18523654524', 0, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_stall` VALUES (4, '档口四', '123', '15236522254', 1, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_stall` VALUES (8, '山东煎饼档口', '123123', '18302249147', 1, '2021-08-15 23:06:59', '2021-12-07 13:41:57');
INSERT INTO `coms_stall` VALUES (9, '三年级数学', '123123', '18302249149', 0, '2021-08-15 23:07:20', '2021-08-15 23:07:20');
INSERT INTO `coms_stall` VALUES (10, '123', '123123', '123123123213', 1, '2021-08-15 23:07:34', '2021-08-15 23:07:34');
INSERT INTO `coms_stall` VALUES (11, '智慧医辅-基于区块链的智慧自珍平台', '123', '18302249142', 1, '2021-08-15 23:07:48', '2021-12-08 09:44:36');
INSERT INTO `coms_stall` VALUES (12, '武大郎烧饼', '123456', '18302249148', 1, '2021-12-07 13:19:18', '2021-12-07 13:19:18');

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
) ENGINE = InnoDB AUTO_INCREMENT = 208 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `coms_sup_category` VALUES (100, 23, 13, 4);
INSERT INTO `coms_sup_category` VALUES (101, 24, 13, 4);
INSERT INTO `coms_sup_category` VALUES (102, 25, 13, 4);
INSERT INTO `coms_sup_category` VALUES (103, 26, 13, 4);
INSERT INTO `coms_sup_category` VALUES (104, 27, 13, 4);
INSERT INTO `coms_sup_category` VALUES (105, 15, 13, 6);
INSERT INTO `coms_sup_category` VALUES (106, 16, 13, 6);
INSERT INTO `coms_sup_category` VALUES (107, 17, 13, 6);
INSERT INTO `coms_sup_category` VALUES (108, 18, 13, 6);
INSERT INTO `coms_sup_category` VALUES (109, 19, 13, 6);
INSERT INTO `coms_sup_category` VALUES (110, 20, 13, 6);
INSERT INTO `coms_sup_category` VALUES (111, 21, 13, 6);
INSERT INTO `coms_sup_category` VALUES (112, 22, 13, 6);
INSERT INTO `coms_sup_category` VALUES (195, 23, 2, 4);
INSERT INTO `coms_sup_category` VALUES (196, 24, 2, 4);
INSERT INTO `coms_sup_category` VALUES (197, 25, 2, 4);
INSERT INTO `coms_sup_category` VALUES (198, 26, 2, 4);
INSERT INTO `coms_sup_category` VALUES (199, 27, 2, 4);
INSERT INTO `coms_sup_category` VALUES (200, 15, 2, 6);
INSERT INTO `coms_sup_category` VALUES (201, 16, 2, 6);
INSERT INTO `coms_sup_category` VALUES (202, 17, 2, 6);
INSERT INTO `coms_sup_category` VALUES (203, 18, 2, 6);
INSERT INTO `coms_sup_category` VALUES (204, 19, 2, 6);
INSERT INTO `coms_sup_category` VALUES (205, 20, 2, 6);
INSERT INTO `coms_sup_category` VALUES (206, 21, 2, 6);
INSERT INTO `coms_sup_category` VALUES (207, 22, 2, 6);

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
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coms_supplier
-- ----------------------------
INSERT INTO `coms_supplier` VALUES (1, '供应商1', '123', '18472634753', '天津市南开区', 1, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (2, '供应商2', '123', '15623548524', '天津市西青区', 1, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (3, '供应商3', '', '13562458524', '天津市东丽区', 0, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (4, '供应商4', '', '17652345246', '天津市北辰区', 1, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (5, '供应商5', '', '18635247852', '天津市红桥区', 1, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (6, '供应商6', '', '18652354521', '天津市河西区', 0, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (7, 'test', '12345fsf', '12345523654', '天津市XX区', 0, '2021-07-08 10:00:24', '2021-07-08 10:00:24');
INSERT INTO `coms_supplier` VALUES (8, '供应商XX', '123456789', '13812345678', '天津市1', 1, '2021-07-08 11:05:54', '2021-07-08 11:21:22');
INSERT INTO `coms_supplier` VALUES (9, '供应商7', '2321321', '23232423432', '天津是津南区', 1, '2021-07-08 16:47:21', '2021-07-08 16:47:25');
INSERT INTO `coms_supplier` VALUES (10, '供应商8', '32323', '23123213213', '天津市北辰区', 1, '2021-07-08 16:48:38', '2021-07-08 16:48:53');
INSERT INTO `coms_supplier` VALUES (11, '供应商9', '22423', '12312323', '天津市红桥区', 1, '2021-07-08 16:49:48', '2021-07-08 16:49:52');
INSERT INTO `coms_supplier` VALUES (12, '供应商10', '11111', '2323232', '天津市东丽区', 1, '2021-07-08 16:50:29', '2021-07-08 16:50:32');
INSERT INTO `coms_supplier` VALUES (13, 'gongyi', '123456', '18302249148', '北京市', 1, '2021-08-20 14:54:38', '2021-08-20 14:54:38');
INSERT INTO `coms_supplier` VALUES (15, '测试供应商', '654321', '152365478523', '北京市', 0, '2021-08-20 15:08:13', '2021-08-20 15:08:13');
INSERT INTO `coms_supplier` VALUES (17, '1', '11111', '13562458524', '稳定性未得到', 1, '2021-09-08 19:03:29', '2021-09-08 19:03:29');
INSERT INTO `coms_supplier` VALUES (18, '1', '11111', '13562458524', '稳定性未得到', 1, '2021-09-08 19:03:36', '2021-09-08 19:03:36');
INSERT INTO `coms_supplier` VALUES (19, '123', '111111', '13812321222', '全文承认GV他', 1, '2021-09-08 19:20:25', '2021-09-08 19:20:25');
INSERT INTO `coms_supplier` VALUES (20, '111', '222222', '12343545454', '13123饿额3ewdsds', 1, '2021-09-08 19:22:54', '2021-09-08 19:22:54');

-- ----------------------------
-- View structure for orderitemview
-- ----------------------------
DROP VIEW IF EXISTS `orderitemview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `orderitemview` AS select `ord`.`prod_name` AS `prod_name`,`ord`.`num` AS `num`,`prod`.`pd` AS `pd`,`prod`.`fd` AS `fd`,`prod`.`brand` AS `brand`,`orp`.`sup_name` AS `sup_name`,`orp`.`sup_id` AS `sup_id`,`ord`.`create_time` AS `create_time` from ((`coms_order_item` `ord` left join `coms_order` `orp` on((`ord`.`order_id` = `orp`.`id`))) left join `coms_product` `prod` on((`ord`.`prod_id` = `prod`.`id`)));

SET FOREIGN_KEY_CHECKS = 1;
