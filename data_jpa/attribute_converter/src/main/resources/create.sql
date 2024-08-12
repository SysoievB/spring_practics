create table email
(
    id        bigint primary key not null auto_increment,
    massage   blob,
    recipient varbinary(255)
);


