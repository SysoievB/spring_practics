create table employee
(
    gross_income integer,
    income_limit integer,
    id           bigint not null,
    name         varchar(255),
    primary key (id)
);