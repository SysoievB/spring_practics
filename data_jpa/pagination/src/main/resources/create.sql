create table address
(
    id           bigint primary key auto_increment,
    street       varchar(255),
    city         varchar(255),
    house_number int,
    flat_number  int
);
