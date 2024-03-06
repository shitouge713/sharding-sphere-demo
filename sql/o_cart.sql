-- 调整表名称o_cart1，共创建16张
CREATE TABLE `o_cart1` (
    `id` bigint NOT NULL COMMENT '自增主键',
    `user_id` varchar(30) DEFAULT NULL,
    `biz_type` tinyint DEFAULT NULL COMMENT '业务线，1:到柜 2：到家',
    `shop_id` varchar(30) DEFAULT NULL COMMENT 'shopid',
    `merchant_code` varchar(255)  DEFAULT NULL COMMENT '商家ID',
    `sku_id` varchar(255) NOT NULL COMMENT 'skuid',
    `quantity` int NOT NULL DEFAULT '0' COMMENT '数量',
    `valid` tinyint NOT NULL DEFAULT '1' COMMENT '是否有效标识 1-有效 0-无效',
    `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `cart_user_id_index` (`user_id`),
    KEY `cart_user_id_sku_id_index` (`user_id`,`sku_id`)
) ENGINE=InnoDB  COMMENT='购物车';
