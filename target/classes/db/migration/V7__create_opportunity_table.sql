CREATE TABLE `opportunity` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(100) NOT NULL COMMENT '商机标题',
  `customer_id` bigint NOT NULL COMMENT '客户ID',
  `amount` decimal(10,2) NOT NULL COMMENT '商机金额',
  `expected_date` date NOT NULL COMMENT '预计成交日期',
  `stage` varchar(20) NOT NULL COMMENT '商机阶段：initial-初步接触, negotiation-谈判中, proposal-方案制定, contract-合同签订, success-成交, fail-失败',
  `probability` int NOT NULL COMMENT '成交概率(0-100)',
  `description` text COMMENT '商机描述',
  `user_id` bigint NOT NULL COMMENT '负责人ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商机表';

-- 插入测试数据
INSERT INTO `opportunity` (`title`, `customer_id`, `amount`, `expected_date`, `stage`, `probability`, `description`, `user_id`) VALUES
('腾讯云服务器采购', 1, 100000.00, '2024-07-01', 'negotiation', 80, '腾讯云服务器年度采购项目', 1),
('阿里云数据库服务', 2, 50000.00, '2024-07-15', 'proposal', 60, '阿里云数据库服务升级项目', 2),
('百度AI解决方案', 3, 200000.00, '2024-08-01', 'initial', 30, '百度AI解决方案定制开发', 3),
('字节跳动广告投放', 4, 150000.00, '2024-07-20', 'contract', 90, '字节跳动广告投放年度合作', 4),
('美团外卖配送系统', 5, 300000.00, '2024-08-15', 'proposal', 70, '美团外卖配送系统升级项目', 5); 