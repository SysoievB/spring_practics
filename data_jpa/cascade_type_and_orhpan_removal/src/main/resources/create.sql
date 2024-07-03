create table article
(
    id   bigint auto_increment primary key,
    name varchar(255)
);
create table comment
(
    id    bigint auto_increment primary key,
    description varchar(255),
    article_id bigint,
    foreign key (article_id) references article(id)
);
create table prime_comment
(
    id    bigint auto_increment primary key,
    description varchar(255),
    article_id bigint,
    foreign key (article_id) references article(id)
);