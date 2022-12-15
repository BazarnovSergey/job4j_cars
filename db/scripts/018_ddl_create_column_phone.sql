ALTER TABLE driver ADD COLUMN phone TEXT;

comment on table driver is 'Владелецы автомобилей';
comment on column driver.phone is 'Номер телефона владельца';