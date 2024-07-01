create table user
(
    id         bigint auto_increment primary key,
    username   varchar(255),
    address_id bigint
);
create table address
(
    id     bigint auto_increment primary key,
    street varchar(255),
    city   varchar(255)
);
create table user_address
(
    address_id bigint,
    user_id    bigint not null,
    primary key (user_id)
);

alter table user_address
    add constraint fk_address_id foreign key (address_id) references address (id);
alter table user_address
    add constraint fk_user_id foreign key (user_id) references user (id);