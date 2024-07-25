create table employees
(
    id        bigint primary key auto_increment,
    name      varchar(255),
    salary    int,
    hire_date date
);

INSERT INTO employees (name, salary, hire_date)
VALUES ('Alice', 50000, '2020-01-15'),
       ('Bob', 55000, '2019-02-20'),
       ('Charlie', 60000, '2021-03-25'),
       ('David', 65000, '2018-04-30'),
       ('Eve', 70000, '2022-05-10'),
       ('Frank', 75000, '2017-06-15'),
       ('Grace', 80000, '2016-07-20'),
       ('Hank', 85000, '2015-08-25'),
       ('Ivy', 90000, '2023-09-05'),
       ('Jack', 95000, '2014-10-10');
