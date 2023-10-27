create database SchoolManager;
use SchoolManager;

create table students(
	id int primary key not null auto_increment,
    name varchar(50) not null,
    email  varchar(50) not null,
    age int not null,
    gender varchar(5)
);

create table courses(
	id int primary key not null auto_increment,
    name varchar(50) not null
);

create table inscriptions(
	id int primary key not null auto_increment,
    student int not null,
    course int not null,
    foreign key (student) references students(id),
    foreign key (course) references courses(id)
);