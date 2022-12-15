CREATE TABLE if not exists car (
       id SERIAL PRIMARY KEY,
       name TEXT,
       engine_id INT NOT NULL UNIQUE REFERENCES engine(id)
);

comment on table car is 'Автомобили';
comment on column car.id is 'Идентификатор автомобиля';
comment on column car.name is 'Марка автомобиля';
comment on column car.engine_id is 'Двигатель установленный в автомобиле';