create table account
(
    deleted bit    not null comment 'Soft-delete indicator',
    id      bigint not null auto_increment,
    name    varchar(255),
    primary key (id)
);
create table active_account
(
    active bit    not null comment 'Soft-delete indicator',
    id     bigint not null auto_increment,
    name   varchar(255),
    primary key (id)
);

insert into account (name,deleted) values (?,0);

insert into active_account (name,active) values (?,1);

update account set deleted=1 where id=? and deleted=0;

update active_account set active=0 where id=? and active=1;