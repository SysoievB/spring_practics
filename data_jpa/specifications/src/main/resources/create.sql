create table user
(
    id         bigint not null auto_increment,
    birth_date date,
    country    varchar(255),
    created_at datetime(6),
    name       varchar(255),
    surname    varchar(255),
    updated_at datetime(6),
    primary key (id)
);