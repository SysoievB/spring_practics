DROP TABLE IF EXISTS employee;
CREATE TABLE employee
(
    id    BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255),
    name  VARCHAR(255),
    PRIMARY KEY (id)
);
