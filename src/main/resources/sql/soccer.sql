/*
 Navicat Premium Data Transfer

 Source Server         : my_test_db
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : school

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 02/06/2023 15:23:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for soccer
-- ----------------------------
DROP TABLE IF EXISTS `soccer`;
CREATE TABLE `soccer`  (
  `matchType` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `matchTime` datetime(0) NULL DEFAULT NULL,
  `matchLeague` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `guest` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `eurWin` decimal(5, 3) NULL DEFAULT NULL,
  `eurDraw` decimal(5, 3) NULL DEFAULT NULL,
  `eurLose` decimal(5, 3) NULL DEFAULT NULL,
  `asWin` decimal(5, 3) NULL DEFAULT NULL,
  `asLose` decimal(5, 3) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0)
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
