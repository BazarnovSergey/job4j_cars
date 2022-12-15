CREATE TABLE if not exists driver (
       id SERIAL PRIMARY KEY,
       name TEXT NOT NULL,
       user_id INT REFERENCES auto_user(id)
);

comment on table driver is 'Владелецы автомобилей';
comment on column driver.id is 'Идентификатор владельца автомобиля';
comment on column driver.name is 'Имя владельца автомобиля';
comment on column driver.user_id is 'Пользователь';