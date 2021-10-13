create database EmployeeService;
go

use EmployeeService;
go

create table Roles
( 
    Id int primary key not null identity(0, 1),
    RoleName varchar(10) not null
)
go

create table EmployeeGroups
(
    Id int primary key not null identity(0, 1),
    GroupName varchar(20) not null
)
go

create table Users 
(
    Id int primary key not null identity(0, 1),
    RoleId int not null foreign key references Roles(Id),
    Email varchar(50) not null unique,
    EmployeeGroupId int not null foreign key references UserGroups(Id),
    Name varchar(25) not null,
    Surname varchar(25) not null,
    MiddleName varchar(25)
)
go

create table MessageContexts
(
    Id int primary key not null identity(0, 1),
    Text nvarchar(2000)
)
go

create table Messages
(
    Id int primary key not null identity(0, 1),
    SenderId int not null foreign key references Users(Id),
    RecipientId int foreign key references Users(Id),
    RecipientGroup int foreign key references UserGroups(Id),
    MessageId int foreign key references MessageContexts(Id)
)
go
