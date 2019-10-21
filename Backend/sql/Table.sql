CREATE SCHEMA IF NOT EXISTS `ssafyworld` DEFAULT CHARACTER SET utf8 ;
USE `ssafyworld` ;
-- -----------------------------------------------------
-- Table `ssafyworld`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`user` (
  `uid` VARCHAR(50) NOT NULL,
  `uname` VARCHAR(10) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`uid`));
-- -----------------------------------------------------
-- Table `ssafyworld`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`room` (
  `rid` INT NOT NULL AUTO_INCREMENT,
  `rname` VARCHAR(20) NULL,
  PRIMARY KEY (`rid`));
 
-- -----------------------------------------------------
-- Table `ssafyworld`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafyworld`.`message` (
  `mid` INT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(200) NULL,
  `sendtime` DATETIME NULL,
  `uid` VARCHAR(50) NOT NULL,
  `rid` INT NOT NULL,
  PRIMARY KEY (`mid`, `uid`, `rid`),
    FOREIGN KEY (`uid`)
    REFERENCES `ssafyworld`.`user` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`rid`)
    REFERENCES `ssafyworld`.`room` (`rid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
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
