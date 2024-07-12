CREATE TABLE IF NOT EXISTS consults(
    id bigint not null auto_increment,
    patient_id bigint not null,
    physician_id bigint not null,

    primary key(id),

    constraint fk_consults_patient_id foreign key (patient_id) references patients(id),
    constraint fk_consults_physician_id foreign key (physician_id) references physicians(id)
);