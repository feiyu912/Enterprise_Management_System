CREATE TABLE IF NOT EXISTS `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_name` varchar(100) NOT NULL COMMENT '客户名称',
  `contact_person` varchar(50) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `salesman_id` bigint(20) DEFAULT NULL COMMENT '销售人员ID',
  `status` varchar(20) DEFAULT 'active' COMMENT '状态',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_salesman_id` (`salesman_id`),
  KEY `idx_customer_name` (`customer_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户表'; 