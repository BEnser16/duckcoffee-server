CREATE DATABASE IF NOT EXISTS `duckcoffeedatabase`;
USE `duckcoffeedatabase`;
SET NAMES utf8mb4;

-- create table for user
CREATE TABLE IF NOT EXISTS `user` (
                                      `id` INT PRIMARY KEY AUTO_INCREMENT,
                                      `name` VARCHAR(255) DEFAULT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` VARCHAR(20) DEFAULT 'USER',
    `img` VARCHAR(255) DEFAULT NULL
    );

-- create account for admin
INSERT INTO user(name , email , password , role , img) VALUES
                                                           ("admin" , "admin" , "admin123" , "admin" , ''),
                                                           ("test1" , "test1@gmail.com" , "test1pw" , "member" , ""),
                                                           ("test2" , "test2@gmail.com" , "test2pw" , "member" , "");

-- crete table for order form
CREATE TABLE IF NOT EXISTS `order_form` (
                                            `id` INT AUTO_INCREMENT PRIMARY KEY,
                                            `form_status` varchar(255),
    `create_time` DATETIME,
    `lastupdate_time` DATETIME,
    `table_number` INT,
    `total_price` INT
    );

-- create table for table seat
CREATE TABLE IF NOT EXISTS `table_seat` (
                                            `id` INT AUTO_INCREMENT PRIMARY KEY,
                                            `table_number` INT,
                                            `seat` INT

);

-- create table for reservation
drop table if exists `reservation`;
CREATE TABLE `reservation` (
                               `id` INT AUTO_INCREMENT PRIMARY KEY,
                               `booking_date` DATE NOT NULL,
                               `person_name` VARCHAR(255) NOT NULL,
                               `person_phone` VARCHAR(255) NOT NULL,
                               `start_time` TIME NOT NULL,
                               `end_time` TIME NOT NULL,
                               `remark` TEXT,
                               `table_id` INT,
                               CONSTRAINT `fk_table_seat` FOREIGN KEY (table_id) REFERENCES table_seat(id)
);

-- create table for post
CREATE TABLE `post` (
                        `id` INT PRIMARY KEY AUTO_INCREMENT,
                        `title` VARCHAR(255) ,
                        `description` TEXT,
                        `cover_img` VARCHAR(255),
                        `create_time` DATETiME
);

INSERT INTO `post` (`title`, `description`, `cover_img`, `create_time`) VALUES
                                                                            ('春季特選咖啡', '介紹我們春季限定的特選咖啡豆，來自埃塞俄比亞的頂級咖啡豆。', 'https://i.imgur.com/KegxcT7.png', '2023-04-01 10:00:00'),
                                                                            ('夏日冰飲促銷', '炎炎夏日，來杯冰涼的冰咖啡或冰茶吧！本月特價促銷中。', 'https://i.imgur.com/8Z50obB.png', '2023-06-15 09:30:00'),
                                                                            ('秋季咖啡藝術工作坊', '參加我們的咖啡拉花工作坊，學習如何創作美麗的咖啡藝術。', 'https://i.imgur.com/dYbOXV2.png', '2023-10-05 14:00:00'),
                                                                            ('冬季節日優惠', '聖誕節及新年來臨之際，享受我們的特別節日優惠。', 'https://i.imgur.com/eTyk3VU.png', '2023-12-20 18:00:00');


-- create table for menu
CREATE TABLE `menu` (
                        `id` INT PRIMARY KEY AUTO_INCREMENT,
                        `name` VARCHAR(255) ,
                        `description` TEXT,
                        `img` VARCHAR(255),
                        `price` INT,
                        `category` VARCHAR(255)
);

-- 咖啡
INSERT INTO menu (name, description, img, price, category) VALUES
                                                               ('拿鐵咖啡', '香濃的咖啡配以絲滑的牛奶，絕佳的平衡。', 'https://i.imgur.com/5ZGMpYc.jpg', 65, 'coffee'),
                                                               ('美式咖啡', '簡單純粹的美式咖啡，帶來濃郁的咖啡香。', 'https://i.imgur.com/3uzB6LO.jpg', 70, 'coffee'),
                                                               ('卡布奇諾', '濃縮咖啡和蒸汽牛奶的完美結合，帶有一層柔軟的奶泡。', 'https://i.imgur.com/a38X8co.jpg', 80, 'coffee'),
                                                               ('香草拿鐵', '香草風味的拿鐵咖啡，帶來一絲甜蜜的味道。', 'https://i.imgur.com/7TI3RMv.jpg', 70, 'coffee'),
                                                               ('摩卡咖啡', '巧克力和咖啡的絕妙融合，豐富的口感令人愉悅。', 'https://i.imgur.com/U1dJVgE.jpg', 100, 'coffee'),
                                                               ('義式濃縮', '其口感醇厚渾重、豐富飽滿，表面有一層咖啡脂，香濃可口。', 'https://i.imgur.com/vOhUokY.jpg', 120, 'coffee'),
                                                               ('黃金曼特寧', '煙燻、辛香料味，細膩口感且醇厚回甘，餘韻如優質巧克力持久。', 'https://i.imgur.com/0apigOa.jpg', 125, 'coffee'),
                                                               ('巧克力冰沙', '以牛奶及可可碎片調製，加上細緻鮮奶油及摩卡醬，濃厚的巧克力風味及多重口感，深獲歡迎。', 'https://i.imgur.com/0USVOw6.jpg', 80, 'coffee');

-- 三明治
INSERT INTO menu (name, description, img, price, category) VALUES
                                                               ('火腿三明治', '美味的火腿搭配新鮮的蔬菜，夾在酥脆的麵包中。', 'https://images.unsplash.com/photo-1481070414801-51fd732d7184?q=80&w=3024&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 75, 'sandwich'),
                                                               ('雞肉牛油果三明治', '新鮮的雞肉搭配香濃的牛油果，口感豐富。', 'https://images.unsplash.com/photo-1603903631889-b5f3ba4d5b9b?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 80, 'sandwich'),
                                                               ('鮭魚三明治', '優雅的鮭魚配上新鮮的生菜，健康又美味。', 'https://plus.unsplash.com/premium_photo-1664478250378-4afecb3f977c?q=80&w=2960&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 85, 'sandwich'),
                                                               ('義式烤蔬菜三明治', '多種蔬菜經義式烤製，濃郁的義式風味。', 'https://images.unsplash.com/photo-1553909489-cd47e0907980?q=80&w=2825&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 90, 'sandwich'),
                                                               ('素食三明治', '清爽的素食選擇，適合注重健康的你。', 'https://images.unsplash.com/photo-1626414375188-805f321bd821?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 70, 'sandwich'),
                                                               ('早餐三明治', '早餐的完美搭配，蛋、芝士和培根的美味。', 'https://plus.unsplash.com/premium_photo-1700948867066-db7372041154?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 85, 'sandwich');

-- 甜點
INSERT INTO menu (name, description, img, price, category) VALUES
                                                               ('草莓慕斯', '柔軟的慕斯上面鋪滿了新鮮的草莓，帶來一絲甜蜜。', 'https://images.unsplash.com/photo-1693857226065-3b3f482ecad0?q=80&w=2835&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 80, 'dessert'),
                                                               ('巧克力蛋糕', '濃郁的巧克力蛋糕，給你滿滿的甜蜜。', 'https://images.unsplash.com/photo-1606890737304-57a1ca8a5b62?q=80&w=2304&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 90, 'dessert'),
                                                               ('藍莓乳酪蛋糕', '藍莓的酸甜搭配乳酪的滑順，美味可口。', 'https://images.unsplash.com/photo-1533134242443-d4fd215305ad?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 95, 'dessert'),
                                                               ('提拉米蘇', '咖啡浸泡的手指餅乾夾心，層次豐富的義式經典。', 'https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?q=80&w=2873&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 100, 'dessert'),
                                                               ('杯子蛋糕', '迷你杯子蛋糕，一口一個，方便分享。', 'https://images.unsplash.com/photo-1586985290301-8db40143d525?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 60, 'dessert'),
                                                               ('鮮果塔', '新鮮水果擺設在香脆塔皮上，清新美味。', 'https://images.unsplash.com/photo-1515088167831-556650cef41d?q=80&w=3075&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 75, 'dessert'),
                                                               ('法式香檳千層', '千層酥皮夾上香檳奶油，層次豐富的法式甜點。', 'https://images.unsplash.com/photo-1464349095431-e9a21285b5f3?q=80&w=3036&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 90, 'dessert');

-- 沙拉
INSERT INTO menu (name, description, img, price, category) VALUES
                                                               ('義式沙拉', '新鮮的生菜搭配番茄、蘋果和堅果，義式風味十足。', 'https://images.unsplash.com/photo-1608032077018-c9aad9565d29?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 80, 'salad'),
                                                               ('凱撒沙拉', '羅馬生菜搭配帕瑪森芝士和酥脆麵包丁，經典的凱撒風味。', 'https://images.unsplash.com/photo-1551248429-40975aa4de74?q=80&w=3090&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 75, 'salad'),
                                                               ('地中海風味沙拉', '地中海風味的沙拉，有著蕃茄、青瓜、橄欖和羊奶酪的獨特搭配。', 'https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 85, 'salad'),
                                                               ('亞洲風味沙拉', '亞洲風味的沙拉，有著新鮮的生菜、紅椒、花生和辣椒醬的獨特風味。', 'https://images.unsplash.com/photo-1644131447391-b1fe869476a9?q=80&w=2874&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 70, 'salad');

-- create order item table
drop table if exists `order_item`;
CREATE TABLE `order_item` (
                              `id` INT AUTO_INCREMENT PRIMARY KEY,
                              `quantity` INT,
                              `menu_item_id` int not null,
                              `orderform_id` int not null,
                              `remark` VARCHAR(255) ,
                              `sugar` VARCHAR(255),
                              `ice` VARCHAR(255),
                              FOREIGN KEY (menu_item_id) REFERENCES menu(id),
                              FOREIGN KEY (orderform_id) REFERENCES order_form(id)
);

