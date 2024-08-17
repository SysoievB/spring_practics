create table products
(
    price   float(53),
    version integer,
    id      bigint not null auto_increment,
    name    varchar(255),
    primary key (id)
);
