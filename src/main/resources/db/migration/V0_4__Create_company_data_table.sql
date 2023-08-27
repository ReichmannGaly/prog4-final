create extension if not exists "uuid-ossp";

create table if not exists company
(
    id      varchar  primary key    default uuid_generate_v4(),
    name    varchar     not null,
    description     varchar,
    slogan      varchar not null,
    address   varchar     not null,
    contact_email       varchar     not null,
    nif         varchar,
    stat        varchar,
    rcs         varchar,
    logo        bytea
);

insert into company
    (name,description,slogan,address,contact_email,nif,stat,rcs,logo)
        values
        ('Digital Experts',
         'Entreprise au service du numerique',
         'Empowering your digital future',
         'Ivandry Antananarivo',
         'contact@digitalexperts.co',
         '111 222 333',
         '202 202 120',
         '145 365 789',
         null);