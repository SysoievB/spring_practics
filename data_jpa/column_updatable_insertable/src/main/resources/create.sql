create table products
(
    id         bigint primary key not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    code       varchar(10)        not null,
    brand      varchar(50)        not null,
    price      decimal
);
alter table products
    add constraint UK_code unique (code);
