create table city
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
);
create table person
(
    address_city        bigint,
    id                  bigint not null auto_increment,
    address_postal_code varchar(255),
    address_street      varchar(255),
    name                varchar(255),
    surname             varchar(255),
    primary key (id)
);
alter table person
    add constraint FK9244t3k3jygi4vofjra7vuy7a foreign key (address_city) references city (id);