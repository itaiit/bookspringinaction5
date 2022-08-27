-- 自定义security用户认证所需用户表
CREATE TABLE `user`
(
    `id`          bigint(10)  NOT NULL AUTO_INCREMENT,
    `username`    varchar(45) NOT NULL DEFAULT '',
    `password`    varchar(45) NOT NULL DEFAULT '',
    `fullname`    varchar(45)          DEFAULT NULL,
    `street`      varchar(45)          DEFAULT NULL,
    `city`        varchar(45)          DEFAULT NULL,
    `state`       varchar(2)           DEFAULT NULL,
    `zip`         varchar(45)          DEFAULT NULL,
    `phonenumber` varchar(45)          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4