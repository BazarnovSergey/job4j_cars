CREATE TABLE if not exists driver (
       id SERIAL PRIMARY KEY,
       name TEXT NOT NULL,
       user_id INT
);