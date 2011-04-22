USE listings;

INSERT INTO categories (created_at, name, slug) VALUES (NOW(), 'escorts', 'escorts');
SET @var_category_id = (SELECT category_id FROM categories WHERE name = 'escorts');
INSERT INTO categories (created_at, name, slug, parent_id) VALUES (NOW(), 'incall', 'escorts/incall', @var_category_id);
INSERT INTO categories (created_at, name, slug, parent_id) VALUES (NOW(), 'outcall', 'escorts/outcall', @var_category_id);

INSERT INTO categories (created_at, name, slug) VALUES (NOW(), 'massage', 'massage');
SET @var_category_id = (SELECT category_id FROM categories WHERE name = 'massage');
INSERT INTO categories (created_at, name, slug, parent_id) VALUES (NOW(), 'fbsm', 'massage/fbsm', @var_category_id);
INSERT INTO categories (created_at, name, slug, parent_id) VALUES (NOW(), 'amp', 'massage/amp', @var_category_id);

INSERT INTO roles (name, description) VALUES ('ROLE_ADMIN', 'Administrator role');
INSERT INTO roles (name, description) VALUES ('ROLE_AFFILIATE', 'Affiliate role');
INSERT INTO roles (name, description) VALUES ('ROLE_AGENCY', 'Agency role');
INSERT INTO roles (name, description) VALUES ('ROLE_MODERATOR', 'Moderator role');
INSERT INTO roles (name, description) VALUES ('ROLE_PROVIDER', 'Provider role');
INSERT INTO roles (name, description) VALUES ('ROLE_USER', 'User role');

INSERT INTO users (created_at, email, password, username) VALUES (NOW(), 'carlsz@gmail.com', '$2a$10$uwdyzmQXEI72w0IkUjRqdOiNB5HZweq4ODR3TF0DbR1q5OFxMgOk6', 'carlsz');
INSERT INTO user_role_assn (user_id, role_id) VALUES (
  (SELECT user_id FROM users WHERE username = 'carlsz'),
  (SELECT role_id FROM roles WHERE name = 'ROLE_ADMIN')
);
INSERT INTO user_role_assn (user_id, role_id) VALUES (
  (SELECT user_id FROM users WHERE username = 'carlsz'),
  (SELECT role_id FROM roles WHERE name = 'ROLE_USER')
);

INSERT INTO users (created_at, email, password, username) VALUES (NOW(), 'raz@streamray.com', '$2a$10$uwdyzmQXEI72w0IkUjRqdOiNB5HZweq4ODR3TF0DbR1q5OFxMgOk6', 'raz');
INSERT INTO user_role_assn (user_id, role_id) VALUES (
  (SELECT user_id FROM users WHERE username = 'raz'),
  (SELECT role_id FROM roles WHERE name = 'ROLE_ADMIN')
);
INSERT INTO user_role_assn (user_id, role_id) VALUES (
  (SELECT user_id FROM users WHERE username = 'raz'),
  (SELECT role_id FROM roles WHERE name = 'ROLE_USER')
);