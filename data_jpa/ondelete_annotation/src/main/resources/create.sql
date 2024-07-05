create table house
(
    id      bigint auto_increment primary key,
    color   varchar(255),
    door_id bigint,
    foreign key (door_id) references door (id) on delete cascade
);
create table door
(
    id    bigint auto_increment primary key,
    color varchar(255)
);
