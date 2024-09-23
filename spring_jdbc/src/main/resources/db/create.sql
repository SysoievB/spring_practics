CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    surname       VARCHAR(255) NOT NULL,
    date_of_birth DATE         NOT NULL,
    adult         BOOLEAN      NOT NULL
);

delete from users;
