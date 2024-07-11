create table person
(
    created_at  datetime(6),
    creator     datetime(6),
    id          bigint not null auto_increment,
    modified_at datetime(6),
    modifier    datetime(6),
    primary key (id)
);
