/*
 Navicat Premium Data Transfer

 Source Server         : Tencent Cloud MySQL
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 42.193.17.157:3306
 Source Schema         : wechat_test

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 07/10/2021 10:45:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people`  (
  `id` int(10) NOT NULL,
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `people_index` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `wechat_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES (1, NULL, '第一批第一个', 'LiuBH_0831', '刘碧豪一号');
INSERT INTO `people` VALUES (2, NULL, '第一批第二个', 'LiuBH0831', '刘碧豪二号');

SET FOREIGN_KEY_CHECKS = 1;
