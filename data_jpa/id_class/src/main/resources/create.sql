create table book
(
    price  integer,
    author varchar(255) not null,
    genre  varchar(255),
    name   varchar(255) not null,
    primary key (author, name)
);

select b.author, b.name, b.genre, b.price
from book b
where b.author = 'Palanin';
