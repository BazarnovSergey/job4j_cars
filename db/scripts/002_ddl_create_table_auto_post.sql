CREATE TABLE if not exists auto_post (
   id SERIAL PRIMARY KEY,
   text TEXT,
   created TIMESTAMP,
   auto_user_id INT REFERENCES auto_user(id)
);

comment on table auto_post is 'Объявления';
comment on column auto_post.text is 'Идентификатор объявления';
comment on column auto_post.created is 'Дата создания объявления';
comment on column auto_post.auto_user_id is 'Пользователь разместивший объявление';