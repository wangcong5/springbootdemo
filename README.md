# test sql
create table user
(
    id    bigint      not null comment '主键ID'
        primary key,
    name  varchar(30) null comment '姓名',
    age   int         null comment '年龄',
    email varchar(50) null comment '邮箱'
);

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone2', 18, 'test1@baomidou.com'),
(2, 'Jack2', 20, 'test2@baomidou.com'),
(3, 'Tom2', 28, 'test3@baomidou.com'),
(4, 'Sandy2', 21, 'test4@baomidou.com'),
(5, 'Billie2', 24, 'test5@baomidou.com');