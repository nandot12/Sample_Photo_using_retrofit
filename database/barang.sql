-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 26, 2021 at 04:52 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `upload_photo`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `barang_id` int(10) NOT NULL,
  `barang_name` varchar(50) NOT NULL,
  `barang_jenis` varchar(50) NOT NULL,
  `barang_suplier` varchar(50) NOT NULL,
  `barang_jumlah` int(10) NOT NULL,
  `barang_harga` int(10) NOT NULL,
  `image` varchar(119) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`barang_id`, `barang_name`, `barang_jenis`, `barang_suplier`, `barang_jumlah`, `barang_harga`, `image`) VALUES
(3, 'nando', 'ee', 'ee', 4, 500, 'Screen Shot 2021-02-25 at 18.51.03.png'),
(4, 'nando', 'ee', 'ee', 4, 500, 'Screen Shot 2021-02-25 at 18.51.03.png'),
(5, '33', '44', '44', 4, 4, 'PurchaseImage679179.png'),
(6, 'ERE', 'RER', 'RER', 4, 4, 'IMG_20210226_220412.jpg'),
(7, 'nando', 'ee', 'ee', 4, 500, 'Screen Shot 2021-02-25 at 18.51.03.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`barang_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `barang_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
