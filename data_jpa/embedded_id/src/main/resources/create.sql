create table book
(
    price  integer,
    author varchar(255) not null,
    genre  varchar(255),
    name   varchar(255) not null,
    primary key (author, name)
);