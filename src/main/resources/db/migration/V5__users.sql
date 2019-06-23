create table users(
  id BIGSERIAL primary key,
  user_name varchar(50) not null unique,
  user_email varchar(100) not null unique,
  password_hash varchar(60) not null,
  created_at timestamp default now() not null,
  deleted_at timestamp

)