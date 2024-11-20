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
/*
The relationship may appear "less visible" than with @JoinColumn
because there isn’t a separately labeled foreign key column,
but it's directly defined in the schema.*/