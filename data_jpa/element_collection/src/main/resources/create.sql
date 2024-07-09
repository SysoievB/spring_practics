create table house
(
    id   bigint not null auto_increment,
    city varchar(255),
    primary key (id)
);
create table house_door
(
    list_index integer not null,
    house_id   bigint  not null,
    door       varchar(255),
    primary key (list_index, house_id)
);
create table house_room
(
    house_id      bigint       not null,
    room_name     varchar(255),
    square_meters varchar(255) not null,
    primary key (house_id, square_meters)
);
create table house_windows
(
    house_id bigint not null,
    windows  varchar(255)
);
alter table house_door
    add constraint FK_house_door foreign key (house_id) references house (id);
alter table house_room
    add constraint FK_house_room foreign key (house_id) references house (id);
alter table house_windows
    add constraint FK_house_windows foreign key (house_id) references house (id);
