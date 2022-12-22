ALTER TABLE car ADD COLUMN driver_id INT REFERENCES driver(id);

comment on table car is 'Автомобили';
comment on column car.driver_id is 'Владелец автомобиля';