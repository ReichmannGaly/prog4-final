create extension if not exists "uuid-ossp";

create table if not exists company_phone
(
    id    varchar  constraint company_phone_pk   primary key    default uuid_generate_v4(),
    company_id     varchar
        constraint company_phone_company_id_fk   references company(id),
    phone_id       varchar
        constraint company_phone_phone_id_fk      references phone(id)
);
