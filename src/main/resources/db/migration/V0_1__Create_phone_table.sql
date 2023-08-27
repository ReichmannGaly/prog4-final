create extension if not exists "uuid-ossp";

create table if not exists phone
(
    id    varchar  constraint phone_pk primary key    default uuid_generate_v4(),
    phone_number     varchar      not null
    );
