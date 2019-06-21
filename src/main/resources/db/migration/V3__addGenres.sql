create table genres(
  id BIGSERIAL primary key,
  name varchar(50) not null unique
)