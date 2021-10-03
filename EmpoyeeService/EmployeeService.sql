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
    Email varchar(50) not null,
    EmployeeGroupId int not null foreign key references EmployeeGroups(Id),
    Name varchar(25) not null,
    Surname varchar(25) not null,
    Middlename varchar(25)
)
go

create table Message
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
    RecipientGroup int foreign key references EmployeeGroups(Id),
    MessageId int foreign key references Message(Id)
)
go
