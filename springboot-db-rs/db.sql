
drop table if exists order_0;
create table order_0
(
    id     bigint primary key comment 'id',
    status int not null default 1 comment '状态'
) comment '订单0';

drop table if exists order_1;
create table order_1
(
    id     bigint primary key comment 'id',
    status int not null default 1 comment '状态'
) comment '订单1';



create database test;