create table car
(
    id    bigint not null auto_increment,
    model varchar(255),
    year  integer,
    primary key (id)
);

create table user
(
    id    bigint not null auto_increment,
    name varchar(255),
    age  integer,
    primary key (id)
);

CREATE PROCEDURE GET_TOTAL_CARS_BY_MODEL(IN model_in VARCHAR(50), OUT count_out INT)
BEGIN
    SELECT COUNT(*) into count_out from car WHERE model = model_in;
END;


CREATE PROCEDURE GET_USERS_BY_AGE(IN ageParam INT)
BEGIN
    SELECT * FROM user WHERE age = ageParam;
END;