CREATE TABLE if not exists auto_user (
   id SERIAL PRIMARY KEY,
   text TEXT,
   created timestamp,
   auto_user_id int
);