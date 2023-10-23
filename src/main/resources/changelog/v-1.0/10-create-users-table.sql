create table users
(
    id                     binary(16) not null,
    credits                integer      not null,
    experience             integer      not null,
    firma_title            varchar(255) not null,
    first_name             varchar(255) not null,
    information_about_user varchar(255) not null,
    password               varchar(255) not null,
    surname                varchar(255) not null,
    unique_number          varchar(255) not null,
    reputation             enum ('DIAMOND','GOOD_SPECIALIST','NO_TRUST','PRACTITIONER_BEGINNER','SENIOR') not null,
    role                   enum ('ADMIN','USER') not null,
    username               varchar(255) not null,
    email                  varchar(255) not null,
    photo                  varchar(600) not null,
    primary key (id)
) engine=InnoDB
