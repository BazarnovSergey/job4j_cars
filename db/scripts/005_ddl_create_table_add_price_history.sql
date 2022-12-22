CREATE TABLE PRICE_HISTORY(
   id SERIAL PRIMARY KEY,
   before BIGINT not null,
   after BIGINT not null,
   created TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
   auto_post_id INT REFERENCES auto_post(id)
);

comment on table price_history is 'Истории изменения цены на автомобиль';
comment on column price_history.id is 'Идентификатор истории изменения цены';
comment on column price_history.before is 'Цена до изменения';
comment on column price_history.after is 'Цена после изменения';
comment on column price_history.created is 'Дата создания';
comment on column price_history.auto_post_id is 'Объявление которому принадлежит история изменения цены';