create database test;

use test;

DROP TABLE IF EXISTS `user`;

DROP TABLE `user`;
CREATE TABLE `user`
(
    `id`          bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
    `username`    varchar(32) NOT NULL COMMENT '用户名',
    `password`    varchar(32) NOT NULL COMMENT '密码',
    `gender`      varchar(32) NOT NULL COMMENT '性别',
    `nick_name`   varchar(32) NOT NULL COMMENT '昵称',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_time` datetime    NOT NULL COMMENT '修改时间'
) COMMENT '用户';
