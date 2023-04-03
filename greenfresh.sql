-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 03, 2023 lúc 11:05 AM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `greenfresh`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `banner`
--

CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `link` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `banner`
--

INSERT INTO `banner` (`id`, `link`) VALUES
(1, 'https://media.gq-magazine.co.uk/photos/5e2987428f4e9e0008b5fdf0/master/pass/20200123-Vegetables.jpg'),
(2, 'https://vinmec-prod.s3.amazonaws.com/images/20210404_152411_886508_an-thit.max-1800x1800.jpg'),
(3, 'https://www.diabetes.co.uk/wp-content/uploads/2019/01/iStock-467652436.jpg'),
(4, 'https://images.healthshots.com/healthshots/en/uploads/2022/02/08134710/JUICES-1600x900.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `thumb` varchar(300) NOT NULL,
  `id_product` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`, `thumb`, `id_product`) VALUES
(1, 'Vegetables', 'https://www.starfrit.com/media/amasty/webp/contentmanager/content/top-10-vegetable-tools_1_jpg.webp', NULL),
(2, 'Meat', 'https://www.beefcentral.com/wp-content/uploads/2022/02/MeatiStock-1310910433-e1645596653685.jpg', NULL),
(3, 'Protein food', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSf0omyPx8EKBzstCJKuL3gph__6qephfiiIQ&usqp=CAU', NULL),
(4, 'Low fat', 'https://images-prod.healthline.com/hlcmsresource/images/AN_images/is-ultra-low-fat-healthy-1296x728-feature.jpg', NULL),
(5, 'Fruit', 'https://i2-vnexpress.vnecdn.net/2015/07/11/fruit-image-4717-1436585539.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=LNT284FboSe4wyC1yAaVjw', NULL),
(6, 'Juice', 'https://health.clevelandclinic.org/wp-content/uploads/sites/3/2018/04/Fruit-Juice-155376375-770x533-1-650x428.jpg', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `thumb` varchar(500) NOT NULL,
  `description` varchar(400) NOT NULL,
  `price` double NOT NULL,
  `unit` varchar(20) NOT NULL,
  `calories` int(11) NOT NULL,
  `star` double NOT NULL,
  `sale` int(11) NOT NULL,
  `numberInCart` int(11) DEFAULT NULL,
  `id_cate` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `name`, `thumb`, `description`, `price`, `unit`, `calories`, `star`, `sale`, `numberInCart`, `id_cate`) VALUES
(1, 'Bell Pepper', 'https://res.cloudinary.com/vku-university/image/upload/v1663725637/xovwssvbv6i1vmrbna5t.jpg', 'Bell peppers, also known as sweet peppers or capsicums, are incredibly nutritious. They contain antioxidants called carotenoids that may reduce inflammation, decrease cancer risk and protect cholester', 15, 'kg', 50, 4.6, 20, NULL, 0),
(2, 'Strawberry', 'https://res.cloudinary.com/vku-university/image/upload/v1663757344/qi0ya7niogeycwjiy9kd.jpg', 'These potent little packages protect your heart, increase HDL (good) cholesterol, lower your blood pressure, and guard against cancer. Packed with vitamins, fiber, and particularly high levels of antioxidants known as polyphenols, strawberries are a sodium-free, fat-free, cholesterol-free, low-calorie food.', 25, 'kg', 50, 4.8, 50, NULL, 0),
(3, 'Apple', 'https://res.cloudinary.com/dxzr2klk5/image/upload/v1662893516/product/product-10_dtfqqv.jpg', 'Apples are an incredibly nutritious fruit that offers multiple health benefits. They\'re rich in fiber and antioxidants. Eating them is linked to a lower risk of many chronic conditions, including diabetes, heart disease, and cancer.', 10, 'kg', 100, 5, 15, NULL, 0),
(4, 'Fruit Juice', 'https://res.cloudinary.com/dxzr2klk5/image/upload/v1662893517/product/product-8_nq9l7b.jpg', 'Fruit juices vary in nutritional value, but most have a variety of health benefits. They contain various antioxidants that help reduce the risk of certain health issues and vitamins that help the body function well.', 12, 'glass', 500, 4.8, 20, NULL, NULL),
(5, 'Waterlemon', 'https://res.cloudinary.com/dxzr2klk5/image/upload/v1662893518/product/product-13_jbtvso.png', 'In addition, several other nutrients in watermelon have specific benefits for heart health. Studies show that lycopene can help lower cholesterol and blood pressure. In obese people, postmenopausal women, and some men, lycopene helps reduce the stiffness and thickness of artery walls.', 30, 'kg', 100, 4, 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `avt` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `phone`, `password`, `avt`) VALUES
(1, 'chanh', 'chanh@gmail.com', '0123456789', '111', NULL),
(5, 'chanh', 'ashdadj@gmail.com', '546353453413123', '111', NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `banner`
--
ALTER TABLE `banner`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `banner`
--
ALTER TABLE `banner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
