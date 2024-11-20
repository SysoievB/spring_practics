create table address
(
    id     bigint not null auto_increment,
    city   varchar(255),
    street varchar(255),
    primary key (id)
);
create table user
(
    id       bigint not null,
    username varchar(255),
    primary key (id)
);
