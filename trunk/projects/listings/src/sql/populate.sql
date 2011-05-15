USE listings;

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
  (SELECT role_id FROM roles WHERE name = 'ROLE_PROVIDER')
);
INSERT INTO user_role_assn (user_id, role_id) VALUES (
  (SELECT user_id FROM users WHERE username = 'carlsz'),
  (SELECT role_id FROM roles WHERE name = 'ROLE_USER')
);

INSERT INTO posts (created_at, avg_rating, content, expires_at, location, num_reviews, phone, status, summary, `type`, author_id, guid, display_name) VALUES (
  NOW(),
  0,
  'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metus.',
  ADDDATE(NOW(), 30),
  '94085',
  0,
  '(408) 555-1212',
  'ACTIVE',
  'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean ultricies mi vitae est mauris placerat. Vestibulum erat wisi, condimentum sed, commodo vitae, enim.',
  'FEATURED',
  (SELECT user_id FROM users WHERE username = 'carlsz'),
  'qa8w6xp7jm9a',
  'Special Ed'
);

INSERT INTO post_attributes (name, type, value) VALUES
('Age', 'DETAIL', '{"Age":"36"}'),
('Hair', 'DETAIL', '{"Hair":"Blonde"}'),
('Eyes', 'DETAIL', '{"Eyes":"Blude"}'),
('Height', 'DETAIL', '{"Height":"5'' 9\""}'),
('Weight', 'DETAIL', '{"Weight":"195 lbs."}'),
('Twitter', 'LINK', '{"Twitter":"http://twitter.com/carlsz"}'),
('Facebook', 'LINK', '{"Facebook":"http://facebook.com/carlsz"}');