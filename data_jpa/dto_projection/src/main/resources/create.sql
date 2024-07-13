create table address
(
    id        bigint not null auto_increment,
    person_id bigint,
    city      varchar(255),
    state     varchar(255),
    street    varchar(255),
    zip_code  varchar(255),
    primary key (id)
);
create table person
(
    id         bigint not null auto_increment,
    first_name varchar(255),
    last_name  varchar(255),
    primary key (id)
);
alter table address
    add constraint U_address_person_id unique (person_id);
alter table address
    add constraint FK_address_person_id foreign key (person_id) references person (id);


INSERT INTO person(id, first_name, last_name)
VALUES (1, 'John', 'Doe');
INSERT INTO address(id, person_id, state, city, street, zip_code)
VALUES (1, 1, 'CA', 'Los Angeles', 'Standford Ave', '90001');