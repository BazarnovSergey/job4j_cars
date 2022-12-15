CREATE TABLE if not exists history_owner (
       id SERIAL PRIMARY KEY,
       driver_id INT NOT NULL REFERENCES driver(id),
       car_id INT NOT NULL REFERENCES car(id)
);

comment on table history_owner is 'История владения автомобилем';
comment on column history_owner.id is 'Идентификатор истории владения автомобилем';
comment on column history_owner.driver_id is 'Владелец автомобиля';
comment on column history_owner.car_id is 'Автомобиль';