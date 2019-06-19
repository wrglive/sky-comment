create table comment
(
    id        bigint                        not null
        primary key,
    user_id   bigint                        not null,
    media_id  bigint(13)                    not null,
    content   varchar(200)                  null,
    create_at bigint(13)                    not null,
    type      tinyint(2) unsigned           not null,
    status    tinyint(1) unsigned default 0 null
);

