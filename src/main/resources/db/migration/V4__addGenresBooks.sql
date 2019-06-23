create table book_genres(
  book_id BIGINT not null references book,
  genre_id BIGINT not null references genres,
  unique (book_id, genre_id)
)