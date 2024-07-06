create table seq_user
(
    next_val bigint
);

insert into seq_user
values (1);
create table user
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);