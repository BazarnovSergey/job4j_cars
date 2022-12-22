CREATE TABLE if not exists participates (
   id serial PRIMARY KEY,
   user_id int not null REFERENCES auto_user(id),
   post_id int not null REFERENCES auto_post(id)
);

comment on table participates is 'Подписки пользователей';
comment on column participates.id is 'Идентификатор подписки пользователей';
comment on column participates.user_id is 'Пользователь подписанный на объявление';
comment on column participates.post_id is 'Объявление на которое подписан пользователь';