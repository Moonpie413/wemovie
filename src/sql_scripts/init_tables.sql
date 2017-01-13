DROP TABLE IF EXISTS sub_button;
DROP TABLE IF EXISTS button;
CREATE TABLE button (
  `button_id` INTEGER(20) NOT NULL PRIMARY KEY ,
  `name` VARCHAR(20) NOT NULL ,
  `type` VARCHAR(20) NOT NULL ,
  `key` VARCHAR(20) ,
  `url` VARCHAR(20) ,
  `media_id` VARCHAR(20)
) ENGINE = InnoDB DEFAULT CHARSET UTF8;

CREATE TABLE sub_button (
  `sub_button_id` INTEGER(20) NOT NULL PRIMARY KEY ,
  `button_id` INTEGER(20) NOT NULL ,
  `name` VARCHAR(20) NOT NULL ,
  `type` VARCHAR(20) NOT NULL ,
  `key` VARCHAR(20) ,
  `url` VARCHAR(20) ,
  `media_id` VARCHAR(20),
  CONSTRAINT `fk_button` FOREIGN KEY (`button_id`) REFERENCES button(`button_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET UTF8;

INSERT INTO button(`button_id`, `name`, `type`, `key`) VALUES (1, '菜单1', 'click', 'menu1');
INSERT INTO button(`button_id`, `name`, `type`, `key`) VALUES (2, '菜单2', 'click', 'menu2');
INSERT INTO button(`button_id`, `name`, `type`, `key`) VALUES (3, '菜单3', 'click', 'menu3');

INSERT INTO sub_button(`sub_button_id`, `button_id`, `name`, `type`, `url`) VALUES (1, 1, '子菜单1-1', 'view', 'http://www.baidu.com');
INSERT INTO sub_button(`sub_button_id`, `button_id`, `name`, `type`, `url`) VALUES (2, 1, '子菜单1-2', 'view', 'http://www.baidu.com');


