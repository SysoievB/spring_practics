create table user
(
    id   bigint not null,
    name varchar(255),
    primary key (id)
);
create table user_ids
(
    gen_value bigint,
    gen_name  varchar(255) not null,
    primary key (gen_name)
);
insert into user_ids(gen_name, gen_value)
values ('user', 0);