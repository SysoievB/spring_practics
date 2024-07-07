create table company
(
    id                 integer not null auto_increment,
    address            varchar(255),
    contact_first_name varchar(255),
    contact_last_name  varchar(255),
    contact_phone      varchar(255),
    name               varchar(255),
    phone              varchar(255),
    primary key (id)
);

