create table orders
(
    id        bigint not null auto_increment,
    person_id bigint,
    name      varchar(255),
    primary key (id)
);
create table person
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
);
alter table orders
    add constraint FK_person_id foreign key (person_id) references person (id);
