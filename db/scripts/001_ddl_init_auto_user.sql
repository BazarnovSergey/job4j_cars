CREATE TABLE if not exists auto_user (
   id SERIAL PRIMARY KEY,
   login varchar(20),
   password varchar(20)
);