create table user_details
(
    user_id      bigint not null,
    address      varchar(255),
    phone_number varchar(255),
    primary key (user_id)
);
create table users
(
    id       bigint not null auto_increment,
    email    varchar(255),
    username varchar(255),
    primary key (id)
);
alter table user_details
    add constraint FKicouhgavvmiiohc28mgk0kuj5 foreign key (user_id) references users (id);