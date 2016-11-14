-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 14, 2016 at 04:02 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kambingsoondb`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category_name`) VALUES
(1, 'food'),
(2, 'drink');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `menu_name` varchar(100) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `parent_id`, `menu_name`, `image_url`, `price`, `description`, `category_id`) VALUES
(1, NULL, 'KAMBING', 'http://res.cloudinary.com/dlcxe58s7/image/upload/v1478465783/kambing_lk6b4c.jpg', '0', '', 1),
(2, NULL, 'SATE', 'http://res.cloudinary.com/dlcxe58s7/image/upload/v1478465792/satay_jltzd6.jpg', '0', '', 1),
(3, NULL, 'NASI & MIE GORENG', 'http://res.cloudinary.com/dlcxe58s7/image/upload/v1478465772/nasi-goreng_sikumt.jpg', '0', '', 1),
(4, NULL, 'RICE INCLUDED', 'http://res.cloudinary.com/dlcxe58s7/image/upload/v1478465763/rice_qpowbl.jpg', '0', '', 1),
(12, NULL, 'COFFEE', 'http://res.cloudinary.com/dlcxe58s7/image/upload/v1478445599/MENU_IMAGE/sldxklvfdxkvsmj3e6uw.jpg', '0', '', 2),
(14, 12, 'CAPPUCINO', 'http://res.cloudinary.com/dlcxe58s7/image/upload/v1478445599/MENU_IMAGE/sldxklvfdxkvsmj3e6uw.jpg', '25000', 'perfect blend of coffee and milk with a sense of art', 2),
(15, 3, 'NASI GORENG KAMBING MUDA', 'http://res.cloudinary.com/dlcxe58s7/image/upload/v1478465204/MENU_IMAGE/up9nxqxdnqhmbb4rhuot.jpg', '35000', NULL, 1),
(16, 1, 'KAMBING BAKAR ORIGINAL', 'http://res.cloudinary.com/dlcxe58s7/image/upload/v1478465783/kambing_lk6b4c.jpg', '40000', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sales_order`
--

CREATE TABLE IF NOT EXISTS `sales_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_date` datetime NOT NULL,
  `payment_method` varchar(12) DEFAULT NULL,
  `total_gross` decimal(10,0) NOT NULL,
  `discount` int(11) DEFAULT NULL,
  `total_nett` decimal(10,0) DEFAULT NULL,
  `table_no` varchar(11) NOT NULL,
  `status` varchar(25) DEFAULT 'UNPAID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `sales_order`
--

INSERT INTO `sales_order` (`id`, `transaction_date`, `payment_method`, `total_gross`, `discount`, `total_nett`, `table_no`, `status`) VALUES
(1, '2016-11-07 04:40:34', NULL, '40000', 0, NULL, '7', 'UNPAID'),
(2, '2016-11-10 22:41:35', NULL, '40000', 0, NULL, '7', 'UNPAID');

-- --------------------------------------------------------

--
-- Table structure for table `sales_order_detail`
--

CREATE TABLE IF NOT EXISTS `sales_order_detail` (
  `sales_order_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `subtotal` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales_order_detail`
--

INSERT INTO `sales_order_detail` (`sales_order_id`, `menu_id`, `qty`, `subtotal`) VALUES
(1, 16, 1, '40000'),
(2, 16, 2, '80000');

-- --------------------------------------------------------

--
-- Table structure for table `waiting_list`
--

CREATE TABLE IF NOT EXISTS `waiting_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `guest_name` varchar(55) NOT NULL,
  `guest_count` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `waiting_list`
--

INSERT INTO `waiting_list` (`id`, `guest_name`, `guest_count`) VALUES
(2, 'hadi', 2),
(3, 'hadi', 2),
(4, 'hadi', 2),
(5, 'hadi', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
