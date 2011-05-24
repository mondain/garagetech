-- phpMyAdmin SQL Dump
-- version 2.11.10
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 01, 2010 at 08:51 AM
-- Server version: 5.0.77
-- PHP Version: 5.1.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `us_districts`
--

-- --------------------------------------------------------

--
-- Table structure for table `fips_regions`
--

CREATE TABLE IF NOT EXISTS `fips_regions` (
  `code` varchar(2) NOT NULL,
  `name` varchar(64) NOT NULL,
  KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fips_regions`
--

INSERT INTO `fips_regions` (`code`, `name`) VALUES
('01', 'Alabama'),
('02', 'Alaska'),
('04', 'Arizona'),
('05', 'Arkansas'),
('06', 'California'),
('08', 'Colorado'),
('09', 'Connecticut'),
('10', 'Delaware'),
('11', 'District of Columbia'),
('12', 'Florida'),
('13', 'Georgia'),
('15', 'Hawaii'),
('16', 'Idaho'),
('17', 'Illinois'),
('18', 'Indiana'),
('19', 'Iowa'),
('20', 'Kansas'),
('21', 'Kentucky'),
('22', 'Louisiana'),
('23', 'Maine'),
('24', 'Maryland'),
('25', 'Massachusetts'),
('26', 'Michigan'),
('27', 'Minnesota'),
('28', 'Mississippi'),
('29', 'Missouri'),
('30', 'Montana'),
('31', 'Nebraska'),
('32', 'Nevada'),
('33', 'New Hampshire'),
('34', 'New Jersey'),
('35', 'New Mexico'),
('36', 'New York'),
('37', 'North Carolina'),
('38', 'North Dakota'),
('39', 'Ohio'),
('40', 'Oklahoma'),
('41', 'Oregon'),
('42', 'Pennsylvania'),
('44', 'Rhode Island'),
('45', 'South Carolina'),
('46', 'South Dakota'),
('47', 'Tennessee'),
('48', 'Texas'),
('49', 'Utah'),
('50', 'Vermont'),
('51', 'Virginia'),
('53', 'Washington'),
('54', 'West Virginia'),
('55', 'Wisconsin'),
('56', 'Wyoming'),
('AE', 'Armed Forces Africa'),
('AP', 'Armed Forces Pacific'),
('78', 'Virgin Islands of the U.S.'),
('72', 'Puerto Rico'),
('66', 'Guam'),
('60', 'American Samoa'),
('64', 'Federated States of Micronesia'),
('70', 'Palau'),
('68', 'Marshall Islands'),
('69', 'Northern Mariana Islands'),
('72', 'Puerto Rico '),
('74', 'U.S. Minor Outlying Islands ');
