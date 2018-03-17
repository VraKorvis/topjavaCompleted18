DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id) VALUES
--   admin
  ('2018-03-12 14:00:00', 'Админ ланч', 510, 100001),
  ('2018-03-12 21:00:00', 'Админ ужин', 1500, 100001),
  ('2018-03-12 10:00:00', 'Админ завтрак', 810, 100001),
--   user
  ('2018-06-12 13:00:00', 'Юзер ланч', 900, 100000),
  ('2018-06-12 20:00:00', 'Юзер ужин', 700, 100000),
  ('2018-06-12 07:00:00', 'Юзер завтрак', 400, 100000)

