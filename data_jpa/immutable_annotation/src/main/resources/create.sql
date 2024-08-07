create table address
(
    id           bigint primary key auto_increment,
    street       varchar(255),
    city         varchar(255),
    flat_number  int
);

INSERT INTO address (street, city, house_number, flat_number)
VALUES ('Main Street', 'New York', 1),
       ('Broadway', 'Los Angeles', 2),
       ('Broadway', 'Los Angeles', 2),
       ('Broadway', 'Los Angeles', 2),
       ('Broadway', 'Los Angeles', 2),
       ('Broadway', 'Los Angeles', 2),
       ('Broadway', 'Los Angeles', 2),
       ('Broadway', 'Los Angeles', 2),
       ('Broadway', 'Los Angeles', 2),
       ('King Street', 'Chicago', 3),
       ('Park Avenue', 'San Francisco', 4),
       ('Ocean Drive', 'Miami', 5),
       ('High Street', 'London', 6),
       ('High Street', 'London', 6),
       ('High Street', 'London', 6),
       ('Queen Street', 'Sydney', 7),
       ('Sunset Boulevard', 'Los Angeles', 8),
       ('Sunset Boulevard', 'Los Angeles', 8),
       ('Sunset Boulevard', 'Los Angeles', 8),
       ('Sunset Boulevard', 'Los Angeles', 8),
       ('Sunset Boulevard', 'Los Angeles', 8),
       ('Sunset Boulevard', 'Los Angeles', 8),
       ('Sunset Boulevard', 'Los Angeles', 8),
       ('Golden Gate Avenue', 'San Francisco', 9),
       ('Rodeo Drive', 'Beverly Hills', 10);
