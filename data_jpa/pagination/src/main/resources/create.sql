create table address
(
    id           bigint primary key auto_increment,
    street       varchar(255),
    city         varchar(255),
    house_number int,
    flat_number  int
);

INSERT INTO address (street, city, house_number, flat_number)
VALUES ('Main Street', 'New York', 10, 1),
       ('Broadway', 'Los Angeles', 20, 2),
       ('Broadway', 'Los Angeles', 20, 2),
       ('Broadway', 'Los Angeles', 20, 2),
       ('Broadway', 'Los Angeles', 20, 2),
       ('Broadway', 'Los Angeles', 20, 2),
       ('Broadway', 'Los Angeles', 20, 2),
       ('Broadway', 'Los Angeles', 20, 2),
       ('Broadway', 'Los Angeles', 20, 2),
       ('King Street', 'Chicago', 30, 3),
       ('Park Avenue', 'San Francisco', 40, 4),
       ('Ocean Drive', 'Miami', 50, 5),
       ('High Street', 'London', 60, 6),
       ('High Street', 'London', 60, 6),
       ('High Street', 'London', 60, 6),
       ('Queen Street', 'Sydney', 70, 7),
       ('Sunset Boulevard', 'Los Angeles', 80, 8),
       ('Sunset Boulevard', 'Los Angeles', 80, 8),
       ('Sunset Boulevard', 'Los Angeles', 80, 8),
       ('Sunset Boulevard', 'Los Angeles', 80, 8),
       ('Sunset Boulevard', 'Los Angeles', 80, 8),
       ('Sunset Boulevard', 'Los Angeles', 80, 8),
       ('Sunset Boulevard', 'Los Angeles', 80, 8),
       ('Golden Gate Avenue', 'San Francisco', 90, 9),
       ('Rodeo Drive', 'Beverly Hills', 100, 10);
