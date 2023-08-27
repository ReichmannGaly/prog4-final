create extension if not exists "uuid-ossp";

create table if not exists employee_phone
(
    id    varchar  constraint employee_phone_pk   primary key    default uuid_generate_v4(),
    employee_id     varchar
        constraint employee_phone_employee_id_fk   references employee(id),
    phone_id       varchar
        constraint employee_phone_phone_id_fk      references phone(id)
);
