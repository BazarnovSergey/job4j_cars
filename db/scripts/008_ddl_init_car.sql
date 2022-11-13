CREATE TABLE if not exists car (
       id SERIAL PRIMARY KEY,
       name TEXT,
       engine_id INT NOT NULL UNIQUE REFERENCES engine(id)
);