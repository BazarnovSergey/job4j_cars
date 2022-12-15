ALTER TABLE auto_post ADD COLUMN sale_status BOOLEAN;

comment on table auto_post is 'Объявления';
comment on column auto_post.sale_status is 'Статус объявления';