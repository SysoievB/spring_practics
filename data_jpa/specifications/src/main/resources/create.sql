create table user
(
    age        integer not null,
    birth_date date,
    is_adult   bit     not null,
    created_at datetime(6),
    id         bigint  not null auto_increment,
    updated_at datetime(6),
    country    varchar(255) null,
    name       varchar(255),
    surname    varchar(255),
    primary key (id)
);
