drop table If Exist `books` cascade;
create table books
(id bigint not null auto_increment, author varchar(255) not null, available varchar(255), reserved varchar(255), title varchar(255) not null, primary key (id))