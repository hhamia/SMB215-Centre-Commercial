-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 24, 2016 at 04:40 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `stock`
--

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE IF NOT EXISTS `company` (
  `idcomp` int(33) NOT NULL AUTO_INCREMENT,
  `customerPhone` varchar(33) NOT NULL,
  `fname` varchar(33) NOT NULL,
  `lastName` varchar(33) NOT NULL,
  `phone` varchar(33) NOT NULL,
  PRIMARY KEY (`idcomp`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`idcomp`, `customerPhone`, `fname`, `lastName`, `phone`) VALUES
(4, 'mohamed asd', 'sd', 'asda', 'sd'),
(5, 'mohamed asd', 'asd', 'asd', 'asd'),
(6, 'mohamed asd', 'asda', 'sda', 'sd123'),
(7, 'asd', 'hamza', 'asd', '123'),
(8, 'qweq', '123', '123', 'asdasdas'),
(9, 'asda', 'asd', 'asd', 'asd'),
(10, 'asdas', 'das', 'das', 'd'),
(11, 'asd', 'asd', 'asd', 'asd'),
(12, 'sda', 'asd', 'asd', 'asd'),
(13, 'asd', 'as', 'dasd', 'asd');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `idcustomer` int(33) NOT NULL AUTO_INCREMENT,
  `fname` varchar(33) NOT NULL,
  `lname` varchar(33) NOT NULL,
  `company` varchar(33) NOT NULL,
  `phone` varchar(33) NOT NULL,
  `str1` varchar(33) NOT NULL,
  `str2` varchar(33) NOT NULL,
  `city` varchar(33) NOT NULL,
  `state` varchar(33) NOT NULL,
  `zip` varchar(33) NOT NULL,
  `country` varchar(33) NOT NULL,
  `fax` varchar(33) NOT NULL,
  `type` varchar(33) NOT NULL,
  `picture` varchar(333) NOT NULL,
  PRIMARY KEY (`idcustomer`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`idcustomer`, `fname`, `lname`, `company`, `phone`, `str1`, `str2`, `city`, `state`, `zip`, `country`, `fax`, `type`, `picture`) VALUES
(24, 'asd', 'asd', 'asd', 'asd', 'asd', 'as', 'asd', 'asd', 'asd', 'Andorra', 'asd', 'Company', ''),
(25, 'qwe', 'qwe', 'qwe', 'qweq', 'qwe', 'weq', 'qwe', 'weq', 'qweq', 'Andorra', 'we', 'Company', ''),
(26, 'asd', 'asd', 'asd', 'asda', 'asd', 'sdas', 'asd', 'da', 'asd', 'Andorra', 'asd', 'Family', ''),
(27, 'asd', 'asd', 'asd', 'asdas', 'asdasd', 'das', 'asda', 'das', 'sdas', 'Andorra', 'dasd', 'Family', ''),
(28, 'asd', 'asd', 'asd', 'asd', 'asda', 'asda', 'sda', 'sd', 'sda', 'Andorra', 'sda', 'Family', ''),
(29, 'asd', 'asda', 'asd', 'sda', 'asda', 'sda', 'sdas', 'sd', 'das', 'Andorra', 'dasd', 'Family', ''),
(30, 'asd', 'sdasd', 'asd', 'asd', 'asd', 'asdasdasd', 'asd', 'asd', 'asd', 'Andorra', 'asda', 'Family', '');

-- --------------------------------------------------------

--
-- Table structure for table `extra`
--

CREATE TABLE IF NOT EXISTS `extra` (
  `idextra` int(33) NOT NULL AUTO_INCREMENT,
  `name` varchar(33) NOT NULL,
  `qty` int(33) NOT NULL,
  `price` decimal(33,0) NOT NULL,
  `description` varchar(33) NOT NULL,
  `date` varchar(33) NOT NULL,
  PRIMARY KEY (`idextra`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `extra`
--

INSERT INTO `extra` (`idextra`, `name`, `qty`, `price`, `description`, `date`) VALUES
(1, '1w1e', 123, '123', 'asd', '2016/07/23 23:23:54'),
(4, 'asdasd', 123, '123', 'asd', '2016/07/23 23:52:12'),
(5, 'bashar', 2, '2', 'sda', '2016/07/24 00:02:24'),
(7, 'ddddd', 22, '12', '2asd', '2016/07/24 00:05:13');

-- --------------------------------------------------------

--
-- Table structure for table `family`
--

CREATE TABLE IF NOT EXISTS `family` (
  `idfam` int(33) NOT NULL AUTO_INCREMENT,
  `customerPhone` varchar(33) NOT NULL,
  `fname` varchar(33) NOT NULL,
  `lastName` varchar(33) NOT NULL,
  `phone` varchar(33) NOT NULL,
  PRIMARY KEY (`idfam`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `family`
--

INSERT INTO `family` (`idfam`, `customerPhone`, `fname`, `lastName`, `phone`) VALUES
(8, 'asd', 'asd', 'asdas', 'dasd');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE IF NOT EXISTS `items` (
  `iditem` int(33) NOT NULL AUTO_INCREMENT,
  `name` varchar(33) NOT NULL,
  `description` varchar(333) NOT NULL,
  `qty` int(33) NOT NULL,
  `price` decimal(33,0) NOT NULL,
  `barcode` varchar(33) NOT NULL,
  `fond` int(33) NOT NULL,
  PRIMARY KEY (`iditem`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`iditem`, `name`, `description`, `qty`, `price`, `barcode`, `fond`) VALUES
(1, 'cds', 'asdasdasd', 1, '14500', '1234', 5),
(2, 'dvds', 'adasdas', 0, '23000', '111', 2);

-- --------------------------------------------------------

--
-- Table structure for table `salesman`
--

CREATE TABLE IF NOT EXISTS `salesman` (
  `idsalesman` int(33) NOT NULL AUTO_INCREMENT,
  `fname` varchar(33) NOT NULL,
  `lname` varchar(33) NOT NULL,
  `company` varchar(33) NOT NULL,
  `phone` varchar(33) NOT NULL,
  `str1` varchar(33) NOT NULL,
  `str2` varchar(33) NOT NULL,
  `city` varchar(33) NOT NULL,
  `state` varchar(33) NOT NULL,
  `zip` varchar(33) NOT NULL,
  `country` varchar(33) NOT NULL,
  `fax` varchar(33) NOT NULL,
  `type` varchar(33) NOT NULL,
  `picture` varchar(333) NOT NULL,
  PRIMARY KEY (`idsalesman`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `salesman`
--

INSERT INTO `salesman` (`idsalesman`, `fname`, `lname`, `company`, `phone`, `str1`, `str2`, `city`, `state`, `zip`, `country`, `fax`, `type`, `picture`) VALUES
(32, 'asd', 'a', 'asd', 'd', 'sd', 's', 'sd', 'a', 's', 'Andorra', 'as', 'Simgle', 'C:\\Users\\skynete10\\Desktop\\WhatsApp-Image-20160605 (3).jpg');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `idtransaction` int(33) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(33) NOT NULL,
  `qty` int(33) NOT NULL,
  `price` decimal(33,0) NOT NULL,
  `date` varchar(33) NOT NULL,
  `time` varchar(33) NOT NULL,
  PRIMARY KEY (`idtransaction`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`idtransaction`, `item_name`, `qty`, `price`, `date`, `time`) VALUES
(36, 'dvds', 1, '23000', '2016/07/24 16:49:01', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `iduser` int(33) NOT NULL AUTO_INCREMENT,
  `email` varchar(33) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(33) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(33) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(33) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=18 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`iduser`, `email`, `username`, `password`, `type`) VALUES
(15, 'hhamiya@hotmail.com', 'skynete10', 'sabine10', 'admin'),
(16, 'hhamiya@hotmail.com', 'hshsjs', 'shsjsj', ''),
(17, 'hhamiya@hotmail.com', 'hshsjs', 'shsjsj', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
