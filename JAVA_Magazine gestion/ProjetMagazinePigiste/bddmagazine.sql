-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 26 jan. 2023 à 20:59
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bddmagazine`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `idArticle` int(11) NOT NULL,
  `titreArticle` varchar(100) NOT NULL,
  `nbFeuillets` int(11) NOT NULL,
  `numPig` int(11) NOT NULL,
  `numMag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`idArticle`, `titreArticle`, `nbFeuillets`, `numPig`, `numMag`) VALUES
(1, 'La clé USB', 4, 2, 1),
(2, 'Le processeur Intel I7', 2, 2, 1),
(3, 'Windows 10 VS Windows 11', 4, 4, 1),
(4, 'Série DELL XPS', 3, 3, 1),
(5, 'La blessure de Rapha', 2, 1, 3),
(6, 'Tout sur la quinzaine à Paris', 6, 4, 3),
(7, 'La nouvelle Kangoo', 2, 2, 6),
(8, 'Le nouveau moteur de la Twingo', 1, 2, 6),
(9, 'La saison des tomates', 3, 4, 5),
(10, 'Le mildiou se développe', 3, 5, 5),
(11, 'Comment bien corder sa raquette', 2, 4, 3),
(12, 'Paris près des étoiles', 4, 5, 4),
(13, 'Bien choisir ses patates', 5, 5, 5),
(14, 'Kanté positif au Covid-19', 2, 5, 4),
(15, 'La politique des transferts', 3, 1, 4),
(16, 'Le peuplier des landes', 2, 3, 2),
(17, 'L\'arbre le plus vieux', 3, 5, 2),
(18, 'Bien tailler ses haies', 1, 3, 2),
(28, 'Sata ou SSD', 6, 2, 1),
(31, 'dede', 1, 1, 3),
(32, 'Le tournoi de Bercy', 3, 4, 3),
(33, 'Les pâtes', 4, 4, 5),
(34, 'aaaa', 1, 2, 6),
(35, 'L\'automne arrive', 3, 5, 2),
(36, 'bbb', 3, 2, 6),
(37, 'Le Quatar éliminé', 3, 5, 4),
(38, 'azerty', 3, 1, 3),
(39, 'dede aime les fraise', 3, 4, 5),
(40, 'hyhy', 2, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `magazine`
--

CREATE TABLE `magazine` (
  `idMag` int(11) NOT NULL,
  `nomMag` varchar(100) NOT NULL,
  `numSpecialite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `magazine`
--

INSERT INTO `magazine` (`idMag`, `nomMag`, `numSpecialite`) VALUES
(1, 'Le monde du PC', 1),
(2, 'Arbres et arbustes', 4),
(3, 'Gazette Roland Garros', 6),
(4, 'France Football', 5),
(5, 'La cuisine BIO', 3),
(6, 'Renault mag', 2);

-- --------------------------------------------------------

--
-- Structure de la table `pigiste`
--

CREATE TABLE `pigiste` (
  `idPigiste` int(11) NOT NULL,
  `nomPigiste` varchar(100) NOT NULL,
  `prixFeuillet` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `pigiste`
--

INSERT INTO `pigiste` (`idPigiste`, `nomPigiste`, `prixFeuillet`) VALUES
(1, 'Fortin', 1.76),
(2, 'Garnier', 1.33),
(3, 'Alison', 1.89),
(4, 'Muller', 1.15),
(5, 'Gand', 1.34);

-- --------------------------------------------------------

--
-- Structure de la table `posseder`
--

CREATE TABLE `posseder` (
  `numSpe` int(11) NOT NULL,
  `numPig` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `posseder`
--

INSERT INTO `posseder` (`numSpe`, `numPig`) VALUES
(1, 2),
(1, 3),
(1, 4),
(2, 2),
(3, 4),
(3, 5),
(4, 3),
(4, 5),
(5, 1),
(5, 4),
(5, 5),
(6, 1),
(6, 4);

-- --------------------------------------------------------

--
-- Structure de la table `specialite`
--

CREATE TABLE `specialite` (
  `idSpe` int(11) NOT NULL,
  `nomSpe` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `specialite`
--

INSERT INTO `specialite` (`idSpe`, `nomSpe`) VALUES
(1, 'Informatique'),
(2, 'Automobile'),
(3, 'Cuisine'),
(4, 'Jardin'),
(5, 'Football'),
(6, 'Tennis');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`idArticle`),
  ADD KEY `numPig` (`numPig`,`numMag`),
  ADD KEY `numMag` (`numMag`);

--
-- Index pour la table `magazine`
--
ALTER TABLE `magazine`
  ADD PRIMARY KEY (`idMag`),
  ADD KEY `numSpe` (`numSpecialite`);

--
-- Index pour la table `pigiste`
--
ALTER TABLE `pigiste`
  ADD PRIMARY KEY (`idPigiste`);

--
-- Index pour la table `posseder`
--
ALTER TABLE `posseder`
  ADD PRIMARY KEY (`numSpe`,`numPig`),
  ADD KEY `numPig` (`numPig`),
  ADD KEY `numSpe` (`numSpe`);

--
-- Index pour la table `specialite`
--
ALTER TABLE `specialite`
  ADD PRIMARY KEY (`idSpe`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `article`
--
ALTER TABLE `article`
  MODIFY `idArticle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT pour la table `magazine`
--
ALTER TABLE `magazine`
  MODIFY `idMag` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `pigiste`
--
ALTER TABLE `pigiste`
  MODIFY `idPigiste` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `specialite`
--
ALTER TABLE `specialite`
  MODIFY `idSpe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `article_ibfk_1` FOREIGN KEY (`numPig`) REFERENCES `pigiste` (`idPigiste`),
  ADD CONSTRAINT `article_ibfk_2` FOREIGN KEY (`numMag`) REFERENCES `magazine` (`idMag`);

--
-- Contraintes pour la table `magazine`
--
ALTER TABLE `magazine`
  ADD CONSTRAINT `magazine_ibfk_1` FOREIGN KEY (`numSpecialite`) REFERENCES `specialite` (`idSpe`);

--
-- Contraintes pour la table `posseder`
--
ALTER TABLE `posseder`
  ADD CONSTRAINT `posseder_ibfk_1` FOREIGN KEY (`numSpe`) REFERENCES `specialite` (`idSpe`),
  ADD CONSTRAINT `posseder_ibfk_2` FOREIGN KEY (`numPig`) REFERENCES `pigiste` (`idPigiste`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
