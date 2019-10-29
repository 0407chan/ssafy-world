DROP SCHEMA IF EXISTS ssafyworld ;

CREATE SCHEMA IF NOT EXISTS `ssafyworld` DEFAULT CHARACTER SET utf8 ;
USE `ssafyworld` ;
-- -----------------------------------------------------
-- Table `ssafyworld`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`user` (
  `uid` VARCHAR(50) NOT NULL,
  `uname` VARCHAR(10) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `img` VARCHAR(100) , 
  `staff` INT(1) default 0,
  PRIMARY KEY (`uid`));
-- -----------------------------------------------------
-- Table `ssafyworld`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`room` (
  `rid` INT AUTO_INCREMENT,
  `rname` VARCHAR(20) NULL,
  PRIMARY KEY (`rid`));

-- -----------------------------------------------------
-- Table `ssafyworld`.`friend`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`friend` (
  `fid` INT AUTO_INCREMENT,
  `uid` VARCHAR(50) NOT NULL,
  `friend` VARCHAR(50) NOT NULL,
  FOREIGN KEY (`uid`)
  REFERENCES `ssafyworld`.`user` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (`friend`)
  REFERENCES `ssafyworld`.`user` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  PRIMARY KEY (`fid`)); 
  
-- -----------------------------------------------------
-- Table `ssafyworld`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`message` (
  `mid` INT AUTO_INCREMENT,
  `text` VARCHAR(200) NULL,
  `time` DATETIME NULL,
  `uid` VARCHAR(50) NOT NULL,
  `rid` INT NOT NULL,
  PRIMARY KEY (`mid`),
  FOREIGN KEY (`uid`)
  REFERENCES `ssafyworld`.`user` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
  FOREIGN KEY (`rid`)
  REFERENCES `ssafyworld`.`room` (`rid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION);
-- -----------------------------------------------------
-- Table `ssafyworld`.`friend`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`friend` (
  `fid` INT AUTO_INCREMENT,
  `uid` VARCHAR(50) NOT NULL,
  `uid_friend` VARCHAR(50) NOT NULL,
  `img` VARCHAR(100) , 
  PRIMARY KEY (`uid`));


-- -----------------------------------------------------
-- Table `ssafyworld`.`room_has_user`
-- -----------------------------------------------------
 CREATE TABLE IF NOT EXISTS `ssafyworld`.`room_has_user` (
  `rid` INT NOT NULL,
  `uid` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`rid`, `uid`),
    FOREIGN KEY (`rid`)
    REFERENCES `ssafyworld`.`room` (`rid`),
    FOREIGN KEY (`uid`)
    REFERENCES `ssafyworld`.`user` (`uid`) );
