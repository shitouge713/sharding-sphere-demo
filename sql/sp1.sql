/*
 Navicat Premium Data Transfer

 Source Server         : SP
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : localhost:3306
 Source Schema         : sp1

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : 65001

 Date: 25/01/2022 09:03:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `role_id` int(32) NOT NULL COMMENT '角色id',
  `user_id` int(32) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色用户关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user1
-- ----------------------------
DROP TABLE IF EXISTS `sys_user0`;
CREATE TABLE `sys_user1`  (
  `id` int(32) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user2
-- ----------------------------
DROP TABLE IF EXISTS `sys_user1`;
CREATE TABLE `sys_user2`  (
  `id` int(32) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user3
-- ----------------------------
DROP TABLE IF EXISTS `sys_user2`;
CREATE TABLE `sys_user3`  (
  `id` int(32) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


--分库分表时，如果使用数据库主键，需要根据表的数量设置初始主键值和增长步长
ALTER TABLE sys_user0 AUTO_INCREMENT = 1, AUTO_INCREMENT = 4;
ALTER TABLE sys_user1 AUTO_INCREMENT = 2, AUTO_INCREMENT = 4;
ALTER TABLE sys_user2 AUTO_INCREMENT = 3, AUTO_INCREMENT = 4;
ALTER TABLE sys_user3 AUTO_INCREMENT = 4, AUTO_INCREMENT = 4;