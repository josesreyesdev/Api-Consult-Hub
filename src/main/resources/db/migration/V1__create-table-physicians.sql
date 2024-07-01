CREATE TABLE IF NOT EXISTS physicians(
    id bigint not null auto_increment,
    name varchar(100) not null,
    avatar varchar(300) not null,
    email varchar(100) not null unique,
    document varchar(6) not null unique,
    specialty varchar(100) not null,
    street varchar(100) not null,
    district varchar(100) not null,
    complement varchar(100),
    number varchar(20),
    city varchar(100) not null,

    primary key(id)
);