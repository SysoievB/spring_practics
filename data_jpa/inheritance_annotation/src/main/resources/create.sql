create table users
(
    salary     integer,
    user_type  integer,
    id         bigint not null auto_increment,
    address    varchar(255),
    name       varchar(255),
    occupation varchar(255),
    primary key (id)
);
