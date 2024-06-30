create table user
(
    id       bigint auto_increment primary key,
    username varchar(255),
    address_id bigint
);
create table address
(
    id     bigint auto_increment primary key,
    street varchar(255),
    city   varchar(255)
);
alter table user
    add constraint fk_address_id foreign key (address_id) references address (id);