ALTER TABLE auto_post ADD COLUMN car_id INT REFERENCES car(id);

comment on table auto_post is 'Объявления';
comment on column auto_post.car_id is 'Автомобиль';