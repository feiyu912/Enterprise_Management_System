CREATE TABLE customer (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL COMMENT '客户名称',
    contact VARCHAR(50) COMMENT '联系人',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(100) COMMENT '邮箱',
    address VARCHAR(200) COMMENT '地址',
    status TINYINT DEFAULT 1 COMMENT '状态：1-活跃，0-非活跃',
    salesman_id BIGINT COMMENT '销售人员ID',
    supervisor_id BIGINT COMMENT '销售主管ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_salesman (salesman_id),
    INDEX idx_supervisor (supervisor_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户表'; 