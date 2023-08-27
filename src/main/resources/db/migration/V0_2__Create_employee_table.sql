create extension if not exists "uuid-ossp";

create table if not exists employee
    (
          id    varchar  constraint employee_pk primary key    default uuid_generate_v4(),
          first_name    varchar     not null,
          last_name     varchar,
          birth_date    date    not null,
          gender    varchar(1)     not null,
          personal_email    varchar     not null,
          professional_email    varchar     not null,
          address      varchar      not null,
          id_card_number    varchar  unique   not null,
          id_card_issue_place      varchar      not null,
          id_card_issue_date        date       not null,
          children_number       int,
          socio_professional_category   varchar not null,
          position      varchar      not null,
          hire_date     date    not null,
          resignation_date     date,
          cnaps_number     varchar   unique   not null,
          ref     varchar,
          image     bytea
    );
