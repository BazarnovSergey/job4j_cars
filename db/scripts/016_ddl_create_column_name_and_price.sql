ALTER TABLE auto_post ADD COLUMN name TEXT;
ALTER TABLE auto_post ADD COLUMN price BIGINT;

comment on table auto_post is 'Объявления';
comment on column auto_post.name is 'Название объявления';
comment on column auto_post.price is 'Цена автомобиля';