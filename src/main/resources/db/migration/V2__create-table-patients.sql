CREATE TABLE IF NOT EXISTS patients(
    id bigint not null auto_increment,
    name varchar(100) not null,
    avatar varchar(300) not null,
    email varchar(100) not null unique,
    identity_document varchar(6) not null unique,
    phone_number varchar(15) not null,
    street varchar(100) not null,
    district varchar(100) not null,
    complement varchar(100),
    number varchar(20),
    city varchar(100) not null,

    primary key(id)
);