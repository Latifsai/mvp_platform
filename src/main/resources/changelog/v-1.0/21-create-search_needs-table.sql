create table search_needs
(
    id            bigint not null auto_increment,
    experience    integer,
    price         decimal(38, 2),
    reputation    enum ('DIAMOND','GOOD_SPECIALIST','NO_TRUST','PRACTITIONER_BEGINNER','SENIOR'),
    search_labels varchar(255),
    user_id       binary(16),
    primary key (id)
) engine=InnoDB