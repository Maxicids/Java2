create database EmployeeService;
go

DECLARE @number INT;
SET @number = 8233;
 
WHILE @number > 0
    begin

		declare @messageToSend nvarchar(2000) = CONVERT(nvarchar(2000), @number);
        exec SEND_MESSAGE @fromId = 1, @chatId = 1, @message = @messageToSend;
		--insert into Messages values (1, 1008, @messageToSend , GETDATE());
        SET @number = @number - 1
    end;

use Messenger;
go

select count(*) from Messages;

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
