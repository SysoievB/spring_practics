create table employees
(
    salary     float(53),
    created_at datetime(6),
    id         bigint       not null auto_increment,
    name       varchar(255) not null,
    primary key (id)
);
INSERT INTO employees (id, name, salary, created_at) VALUES (?, ?, ?, NOW());