DROP SCHEMA IF EXISTS ssafyworld ;

CREATE SCHEMA IF NOT EXISTS `ssafyworld` DEFAULT CHARACTER SET utf8 ;
USE `ssafyworld` ;
-- -----------------------------------------------------
-- Table `ssafyworld`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`user` (
  `uidx` int(5) AUTO_INCREMENT,
  `uid` VARCHAR(50) NOT NULL unique,
  `uname` VARCHAR(10) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `img` VARCHAR(100) , 
  `staff` INT(1) default 0,
  PRIMARY KEY (`uidx`));
-- ----------------------------1-------------------------
-- Table `ssafyworld`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`room` (
  `ridx` INT AUTO_INCREMENT,
  `rname` VARCHAR(20) NULL,
  PRIMARY KEY (`ridx`));

-- -----------------------------------------------------
-- Table `ssafyworld`.`friend`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`friend` (
  `fidx` INT AUTO_INCREMENT,
  `uidx` int(5) NOT NULL,
  `friend` int(5) NOT NULL,
  FOREIGN KEY (`uidx`)
  REFERENCES `ssafyworld`.`user` (`uidx`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (`friend`)
  REFERENCES `ssafyworld`.`user` (`uidx`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  PRIMARY KEY (`fidx`)); 
  
-- -----------------------------------------------------
-- Table `ssafyworld`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`message` (
  `midx` INT AUTO_INCREMENT,
  `text` VARCHAR(200) NULL,
  `time` VARCHAR(50) NULL,
  `uidx` int(5) NOT NULL,
  `ridx` INT NOT NULL,
  PRIMARY KEY (`midx`),
  FOREIGN KEY (`uidx`)
  REFERENCES `ssafyworld`.`user` (`uidx`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (`ridx`)
  REFERENCES `ssafyworld`.`room` (`ridx`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `ssafyworld`.`room_has_user`
-- -----------------------------------------------------
 CREATE TABLE IF NOT EXISTS `ssafyworld`.`room_has_user` (
  `ridx` INT NOT NULL,
  `uidx` int(5) NOT NULL,
  PRIMARY KEY (`ridx`, `uidx`),
  FOREIGN KEY (`ridx`)
  REFERENCES `ssafyworld`.`room` (`ridx`),
  FOREIGN KEY (`uidx`)
  REFERENCES `ssafyworld`.`user` (`uidx`) );
