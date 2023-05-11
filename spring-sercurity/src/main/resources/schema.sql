drop table if exists users;

create table users
(
    id         int auto_increment primary key,
    first_name varchar(250),
    last_name   varchar(250),
    username   varchar(250),
    password   varchar(250)
)