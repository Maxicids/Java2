create table Books
(
    id bigint identity(0, 1)
        primary key,
    title varchar(200) not null,
    author varchar(100) not null,
    coverPhotoURL varchar(2000) not null,
    isbnNumber bigint not null,
    price decimal(10,2) not null,
    language varchar(100) not null,
    genre varchar(100) not null
)
go

create table Roles
(
    id bigint identity(0, 1)
        primary key,
    name varchar(200) not null
)
go

create table Users
(
    id bigint identity(0, 1)
        primary key,
    name varchar(200) not null,
    email varchar(50) not null
        unique,
    mobile varchar(16),
    password varchar(255) not null,
    role_id bigint not null
        references Roles
)
go


insert into Roles
values ('ADMIN'),
       ('USER');
insert into Users
values ('test1', 'test1@test.com', '0', 'pass', 1),
       ('test2', 'test2@test.com', '0', 'pass', 1),
       ('test3', 'test3@test.com', '0', 'pass', 1)
insert into Books
values ('test1', 't1', '0', 0, 1.11, 'EN', 'no'),
       ('test2', 't2', '0', 0, 1.11, 'EN', 'no'),
       ('test3', 't3', '0', 0, 1.11, 'EN', 'no')

