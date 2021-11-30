create table ais_token
(
    id            SERIAL not null,
    access_token  varchar(1500),
    token_type    varchar(20),
    expires_in    varchar(20),
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    primary key (id)
);
