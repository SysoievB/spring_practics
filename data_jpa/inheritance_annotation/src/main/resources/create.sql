create table users
(
    salary     integer,
    user_type  integer,
    id         bigint not null auto_increment,
    address    varchar(255),
    name       varchar(255),
    occupation varchar(255),
    primary key (id)
);

insert into users (name) values (?);

insert into users (name,occupation,salary,user_type) values (?,?,?,1);

insert into users (name,address,user_type) values (?,?,2);