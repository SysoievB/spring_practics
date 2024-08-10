# spring.jpa.properties.hibernate.validator.apply_to_ddl=false
create table employee
(
    experience integer      not null,
    id         bigint       not null auto_increment,
    email      varchar(255) not null,
    name       varchar(255),
    primary key (id)
);

# spring.jpa.properties.hibernate.validator.apply_to_ddl=true
create table employee
(
    experience integer      not null,
    id         bigint       not null auto_increment,
    email      varchar(255) not null,
    name       varchar(255) not null,
    primary key (id)
);
