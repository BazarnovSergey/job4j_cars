CREATE TABLE if not exists engine (
   id SERIAL PRIMARY KEY,
   name TEXT
);

comment on table engine is 'Двигатели';
comment on column engine.id is 'Идентификатор двигателя';
comment on column engine.name is 'Название двигателя';