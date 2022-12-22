ALTER TABLE auto_post ADD COLUMN photo BYTEA;

comment on table auto_post is 'Объявления';
comment on column auto_post.photo is 'Фото автомобиля';