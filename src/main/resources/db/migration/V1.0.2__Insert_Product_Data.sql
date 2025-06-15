-- 插入电子产品
INSERT INTO `sys_product` (`name`, `category`, `price`, `stock`, `status`, `description`) VALUES
('MacBook Pro', 'electronics', 12999.00, 50, 1, 'Apple M2芯片，16GB内存，512GB存储'),
('iPhone 15', 'electronics', 6999.00, 100, 1, 'A17芯片，6.1英寸屏幕，256GB存储'),
('华为 MateBook', 'electronics', 5999.00, 30, 1, 'AMD处理器，16GB内存，512GB存储');

-- 插入服装产品
INSERT INTO `sys_product` (`name`, `category`, `price`, `stock`, `status`, `description`) VALUES
('男士休闲西装', 'clothing', 899.00, 200, 1, '商务休闲风格，羊毛混纺面料'),
('女士连衣裙', 'clothing', 299.00, 150, 1, '夏季新款，雪纺面料'),
('运动套装', 'clothing', 199.00, 300, 1, '速干面料，适合运动健身');

-- 插入食品产品
INSERT INTO `sys_product` (`name`, `category`, `price`, `stock`, `status`, `description`) VALUES
('有机蔬菜礼盒', 'food', 199.00, 100, 1, '当季新鲜有机蔬菜'),
('进口坚果礼盒', 'food', 299.00, 80, 1, '混合坚果，营养丰富'),
('有机水果礼盒', 'food', 159.00, 120, 1, '当季新鲜水果'); 