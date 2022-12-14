ALTER TABLE price_history DROP COLUMN auto_post_id;
ALTER TABLE price_history ADD COLUMN auto_post_id INT REFERENCES auto_post(id) ON DELETE CASCADE;