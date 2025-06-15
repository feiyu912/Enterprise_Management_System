CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `code` varchar(50) NOT NULL COMMENT '角色编码',
  `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 插入测试数据
INSERT INTO `role` (`name`, `code`, `description`) VALUES
('系统管理员', 'admin', '系统管理员，拥有所有权限'),
('普通用户', 'user', '普通用户，拥有基本权限'),
('销售人员', 'salesman', '销售人员，负责客户管理和商机管理'),
('主管', 'supervisor', '主管，负责管理销售团队'); 