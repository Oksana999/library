create table read_books(
  id bigserial primary key,
  user_id bigint not null references users,
  book_id bigint not null references book,
  date timestamp
);