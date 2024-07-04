create table house
(
    id       bigint auto_increment primary key,
    color varchar(255),
    door_id bigint
);
create table door
(
    id     bigint auto_increment primary key,
    color varchar(255)
);
alter table house
    add constraint fk_door_id foreign key (door_id) references door (id) on delete cascade;
