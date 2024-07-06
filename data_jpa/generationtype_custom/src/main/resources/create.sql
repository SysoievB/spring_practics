create table string_prefixed_generator
(
    next_val bigint
);
insert into string_prefixed_generator
values (1);
create table user
(
    id   varchar(255) not null,
    name varchar(255),
    primary key (id)
);