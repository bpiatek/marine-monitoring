create table ship_type
(
    id            SERIAL not null,
    number        varchar(5) unique,
    value         varchar(100),
    primary key (id)
);
