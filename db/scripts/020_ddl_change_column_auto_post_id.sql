ALTER TABLE price_history DROP COLUMN auto_post_id;
ALTER TABLE price_history ADD COLUMN auto_post_id INT REFERENCES auto_post(id) ON DELETE CASCADE;

comment on table price_history is 'Истории изменения цены на автомобиль';
comment on column price_history.auto_post_id is 'Объявление которому принадлежит история изменения цены';