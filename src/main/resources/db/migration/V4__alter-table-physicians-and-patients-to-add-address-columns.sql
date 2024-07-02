ALTER TABLE patients
    ADD state_or_province varchar(100) not null after street,
    ADD zip_code varchar(10) not null after city,
    ADD country varchar(50) not null after zip_code;

ALTER TABLE patients
    CHANGE district municipality_or_delegation varchar(100) not null;

ALTER TABLE patients
    MODIFY number varchar(10) not null,
    MODIFY complement varchar(200);


ALTER TABLE physicians
    ADD state_or_province varchar(100) not null after street,
    ADD zip_code varchar(10) not null after city,
    ADD country varchar(50) not null after zip_code;

ALTER TABLE physicians
    CHANGE district municipality_or_delegation varchar(100) not null;

ALTER TABLE physicians
    MODIFY number varchar(10) not null,
    MODIFY complement varchar(200);