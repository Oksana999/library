create table book(
    id BIGSERIAL primary key,
    name VARCHAR(100) not null,
    description varchar(250) null,
    language integer not null
);

create table author(
    id BIGSERIAL primary key,
    name VARCHAR(100) not null
);

create table author_book(
    id BIGSERIAL primary key,
    book_id BIGINT not null references book,
    author_id BIGINT not null references author
);
