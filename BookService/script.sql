create database BookShop;
go

use BookShop;
go
if(OBJECT_ID('Books') IS NULL) BEGIN
create table Books
(
	id bigint primary key not null identity(0, 1),
	title varchar(200) not null,
	author varchar(100) not null,
	coverPhotoURL varchar(2000) not null,
	isbnNumber bigint not null,
	price decimal(10,2) not null,
	language varchar(100) not null,
	genre varchar(100) not null
)
END
go

create table Roles
(
	id bigint primary key not null identity(0, 1),
	name varchar(200) not null
)
go

create table Users
(
	id bigint primary key not null identity(0, 1),
	name varchar(200) not null,
	email varchar(50) not null unique,
	mobile varchar(16),
	password varchar(255) not null,
	role_id bigint not null foreign key references Roles(id)
)
go

select * from Users
select * from Books

insert into Roles values ('jopa1'), ('jopa2');
-- delete from Roles;
-- delete from users;
-- delete from books;
select * from Roles;
