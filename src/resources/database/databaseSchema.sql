drop database if exists YourNotepad;
create database if not exists YourNotepad;

USE YourNotepad;

create table User
(
    id        int primary key auto_increment,
    firstName varchar(55),
    lastName  varchar(55),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table Account
(
    id       int          not null primary key auto_increment,
    username    varchar(254) not null,
    password varchar(255) not null,
    userId   int          not null,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table Note
(
    id        int          not null primary key auto_increment,
    title     varchar(500) not null,
    html      text         not null,
    accountId int          not null,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



ALTER TABLE Account
    ADD FOREIGN KEY (userId)
        REFERENCES User (id);

ALTER TABLE Note
    ADD FOREIGN KEY (accountId)
        REFERENCES Account (id);
ALTER TABLE Account
    ADD UNIQUE KEY (username);