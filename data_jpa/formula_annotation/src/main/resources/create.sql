create table employee
(
    tax_in_percents integer not null,
    gross_income    bigint  not null,
    id              bigint  not null auto_increment,
    primary key (id)
);