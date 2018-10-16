-- includes all users, but Review-X is excluded in join.
-- This time the users table is on the right side "reviews RIGHT JOIN users"
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
  user_id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  last_name VARCHAR(30),
  first_name VARCHAR(20)
);
INSERT INTO users (last_name,first_name)
VALUES
  ('Anderson','Jillian'),
  ('Kenton','Leo'),
  ('McGavin','Darrin'),
  ('Tommy','Tom');

CREATE TABLE reviews
(
  review_id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
  comment VARCHAR(50),
  user_id INT,
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);
INSERT INTO reviews(comment, user_id)
VALUES
  ('comment-a-1 from user-1', 1),
  ('comment-b-1 from user-2', 2),
  ('comment-a-2 from user-1', 1),
  ('comment-a-3 from user-1', 1),
  ('comment-b-2 from user-2', 2),
  ('comment-c-1 from user-3', 3),
  ('comment-X', NULL);

SELECT first_name, last_name, comment
FROM users RIGHT JOIN reviews ON users.user_id = reviews.user_id;
