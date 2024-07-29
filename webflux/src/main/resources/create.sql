create table user
(
    id   bigint primary key auto_increment,
    name varchar(255),
    age  int
);

INSERT INTO user (name, age)
VALUES ('John Doe', 25),
       ('Jane Smith', 30),
       ('Alice Johnson', 22),
       ('Bob Brown', 28),
       ('Charlie Davis', 35),
       ('Diana Evans', 27),
       ('Edward Frank', 31),
       ('Fiona Green', 24),
       ('George Harris', 29),
       ('Hannah Ivy', 26);

create table employee
(
    id   bigint primary key auto_increment,
    name varchar(255),
    age  int
);

INSERT INTO employee (name, age)
VALUES ('John Doe', 25),
       ('Jane Smith', 30),
       ('Alice Johnson', 22),
       ('Bob Brown', 28),
       ('Charlie Davis', 35),
       ('Diana Evans', 27),
       ('Edward Frank', 31),
       ('Fiona Green', 24),
       ('George Harris', 29),
       ('Hannah Ivy', 26);
