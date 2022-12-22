ALTER TABLE auto_user DROP COLUMN login;
ALTER TABLE auto_user ADD COLUMN login VARCHAR UNIQUE;

comment on table auto_user is 'Пользователи';
comment on column auto_user.login is 'Логин пользователя';
