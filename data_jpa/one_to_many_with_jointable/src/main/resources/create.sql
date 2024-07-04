create table author
(
    id   bigint auto_increment primary key,
    name varchar(255)
);
create table book
(
    id    bigint auto_increment primary key,
    title varchar(255)
);
create table book_author
(
    author_id bigint not null,
    book_id   bigint not null,
    foreign key (author_id) references author (id),
    foreign key (book_id) references book (id)
);

