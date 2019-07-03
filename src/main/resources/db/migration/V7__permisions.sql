create table permissions(
    id bigserial primary key,
    name varchar(100) not null unique
);
create table roles(
    id bigserial primary key,
    name varchar(100) not null unique
);
insert into roles(name) values ('admin');

create table permission_to_role(
    id bigserial primary key ,
    permission_id bigint not null references permissions(id),
    roles_id bigint  not null references roles(id),

    unique (permission_id, roles_id)
)