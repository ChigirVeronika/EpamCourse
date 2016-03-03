CREATE DATABASE `kuchumovamary`;
use `kuchumovamary`;
CREATE TABLE `photo` (
  `id_photo` int(11) NOT NULL AUTO_INCREMENT,
  `filePath` varchar(200) NOT NULL,
  PRIMARY KEY (`id_photo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `contact` (
  `id_contact` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `patronymicName` varchar(45) DEFAULT NULL,
  `dateOfBirth` date NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `citizenship` varchar(45) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `house` varchar(45) DEFAULT NULL,
  `apartment` varchar(45) DEFAULT NULL,
  `zipcode` varchar(10) DEFAULT NULL,
  `id_photo` int(11) NOT NULL,
  `lastModified` datetime NOT NULL,
  `isShown` tinyint(4) NOT NULL DEFAULT '1',
  `maritalStatus` varchar(10) NOT NULL,
  `companyName` varchar(45) NOT NULL,
  PRIMARY KEY (`id_contact`),
  KEY `id_photo` (`id_photo`),
  CONSTRAINT `id_photo` FOREIGN KEY (`id_photo`) REFERENCES `photo` (`id_photo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `attachment` (
  `id_attachment` int(11) NOT NULL AUTO_INCREMENT,
  `id_contact` int(11) NOT NULL,
  `filePath` varchar(200) NOT NULL,
  `lastModified` datetime NOT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `isShown` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_attachment`),
  KEY `id_contact_idx` (`id_contact`),
  CONSTRAINT `contact` FOREIGN KEY (`id_contact`) REFERENCES `contact` (`id_contact`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `phone_number` (
  `id_phoneNumber` int(11) NOT NULL AUTO_INCREMENT,
  `id_contact` int(11) NOT NULL,
  `countryCode` varchar(5) NOT NULL,
  `operatorCode` varchar(3) NOT NULL,
  `phoneNumber` varchar(10) NOT NULL,
  `phoneType` varchar(10) DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `lastModified` datetime NOT NULL,
  `isShown` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_phoneNumber`),
  KEY `id_contact` (`id_contact`),
  CONSTRAINT `id_contact` FOREIGN KEY (`id_contact`) REFERENCES `contact` (`id_contact`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `kuchumovamary`.`photo` (`id_photo`, `filePath`) VALUES ('1', '../res/img/default-profile.png');