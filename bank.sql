-- phpMyAdmin SQL Dump
-- version 4.6.4deb1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Gegenereerd op: 21 feb 2017 om 15:14
-- Serverversie: 10.1.21-MariaDB-1~xenial
-- PHP-versie: 7.0.13-0ubuntu0.16.10.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oldbank`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `card`
--

CREATE TABLE `card` (
  `userid` int(11) NOT NULL,
  `cardid` int(11) NOT NULL,
  `pinhash` text NOT NULL,
  `trys` tinyint(1) NOT NULL DEFAULT '0',
  `blocked` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `card`
--

INSERT INTO `card` (`userid`, `cardid`, `pinhash`, `trys`, `blocked`) VALUES
(1, 3252453, '21f9ff061b755faaa06d71434f31330582901810871c228a4d67f9e52f2adf82ea6b445bb4c98d49da68fb140717911e4fc5e1c49ec0becf0aadec0f7517f8bc', 0, 0);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `log`
--

CREATE TABLE `log` (
  `transactionid` int(11) NOT NULL,
  `userid` char(36) NOT NULL,
  `action` varchar(255) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `money`
--

CREATE TABLE `money` (
  `userid` int(11) NOT NULL,
  `balance` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `money`
--

INSERT INTO `money` (`userid`, `balance`) VALUES
(1, 1805);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `user`
--

CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `user`
--

INSERT INTO `user` (`userid`, `firstname`, `lastname`) VALUES
(1, 'Thomas', 'van Tilburg');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`userid`),
  ADD UNIQUE KEY `userid` (`userid`),
  ADD UNIQUE KEY `cardid` (`cardid`),
  ADD KEY `userid_2` (`userid`),
  ADD KEY `userid_3` (`userid`),
  ADD KEY `userid_4` (`userid`);

--
-- Indexen voor tabel `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`transactionid`);

--
-- Indexen voor tabel `money`
--
ALTER TABLE `money`
  ADD PRIMARY KEY (`userid`),
  ADD UNIQUE KEY `userid` (`userid`),
  ADD KEY `userid_2` (`userid`);

--
-- Indexen voor tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`),
  ADD UNIQUE KEY `userid` (`userid`),
  ADD KEY `userid_2` (`userid`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `card`
--
ALTER TABLE `card`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT voor een tabel `log`
--
ALTER TABLE `log`
  MODIFY `transactionid` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT voor een tabel `money`
--
ALTER TABLE `money`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT voor een tabel `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
