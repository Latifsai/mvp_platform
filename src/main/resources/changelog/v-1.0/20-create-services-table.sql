create table services
(
    id              bigint         not null auto_increment,
    max_price       decimal(38, 2) not null,
    min_price       decimal(38, 2) not null,
    service_title   varchar(255)   not null,
    type_of_service enum ('Accounting','Construction','Cosmetic','Delivery','Educational','Entertainment_and_recreation','Financial','Housing_and_communal','IT','Legal','Medical_and_sanatorium','Security','Tourist','Trade', 'Service') not null,
    user_id         binary(16),
    primary key (id)
) engine=InnoDB
