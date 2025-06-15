CREATE TABLE `plan` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `title` varchar(100) NOT NULL COMMENT '计划标题',
  `content` text NOT NULL COMMENT '计划内容',
  `plan_time` datetime NOT NULL COMMENT '计划时间',
  `status` varchar(20) NOT NULL DEFAULT 'pending' COMMENT '状态：pending-待处理, processing-处理中, completed-已完成',
  `customer_id` bigint NOT NULL COMMENT '客户ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关系计划表';

-- 插入测试数据
INSERT INTO `plan` (`title`, `content`, `plan_time`, `status`, `customer_id`, `user_id`) VALUES
('客户拜访计划', '拜访腾讯客户，了解项目需求', '2024-06-20 10:00:00', 'pending', 1, 1),
('项目启动会议', '与阿里巴巴团队进行项目启动会议', '2024-06-21 14:00:00', 'processing', 2, 2),
('需求分析会议', '与百度团队进行需求分析', '2024-06-22 09:30:00', 'completed', 3, 3),
('产品演示', '向字节跳动展示新产品功能', '2024-06-23 15:00:00', 'pending', 4, 4),
('合同谈判', '与美团进行合同细节谈判', '2024-06-24 11:00:00', 'processing', 5, 5); 