CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `role` varchar(20) NOT NULL COMMENT '角色',
  `status` varchar(20) NOT NULL COMMENT '状态',
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 插入测试数据
INSERT INTO `user` (`username`, `password`, `name`, `phone`, `email`, `role`, `status`)
VALUES
-- 管理员
('admin', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '系统管理员', '13800138000', 'admin@example.com', 'admin', 'active'),
('superadmin', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '超级管理员', '13800138001', 'superadmin@example.com', 'admin', 'active'),

-- 销售人员
('sales1', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '张三', '13800138002', 'zhangsan@example.com', 'salesman', 'active'),
('sales2', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '李四', '13800138003', 'lisi@example.com', 'salesman', 'active'),
('sales3', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '王五', '13800138004', 'wangwu@example.com', 'salesman', 'active'),
('sales4', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '赵六', '13800138005', 'zhaoliu@example.com', 'salesman', 'active'),
('sales5', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '钱七', '13800138006', 'qianqi@example.com', 'salesman', 'active'),

-- 普通用户
('user1', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '用户一', '13800138007', 'user1@example.com', 'user', 'active'),
('user2', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '用户二', '13800138008', 'user2@example.com', 'user', 'active'),
('user3', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '用户三', '13800138009', 'user3@example.com', 'user', 'active'),

-- 非活跃用户
('sales6', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '孙八', '13800138010', 'sunba@example.com', 'salesman', 'inactive'),
('user4', '$2a$10$X/uMNuiw1UwMvqF3Uxw2sO9J9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9Z9', '用户四', '13800138011', 'user4@example.com', 'user', 'inactive'); 