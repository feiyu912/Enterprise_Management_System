DROP TABLE IF EXISTS sys_product;
CREATE TABLE sys_product (
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
  name VARCHAR(100) NOT NULL COMMENT '产品名称',
  category VARCHAR(50) NOT NULL COMMENT '产品类别',
  price DECIMAL(10,2) NOT NULL COMMENT '产品价格',
  stock INT NOT NULL DEFAULT 0 COMMENT '库存数量',
  status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：0-下架 1-上架',
  description VARCHAR(500) COMMENT '产品描述',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品表';

INSERT INTO sys_product (name, category, price, stock, status, description) VALUES
('iPhone 14', 'electronics', 6999.00, 100, 1, 'Apple最新款手机'),
('MacBook Pro', 'electronics', 12999.00, 50, 1, '专业级笔记本电脑'),
('男士休闲西装', 'clothing', 899.00, 200, 1, '商务休闲西装'),
('女士连衣裙', 'clothing', 299.00, 150, 1, '夏季新款连衣裙'),
('有机蔬菜礼盒', 'food', 199.00, 300, 1, '新鲜有机蔬菜组合'),
('进口水果礼盒', 'food', 299.00, 200, 1, '精选进口水果'); 