--`User` and `Review`, one `User: Tom` has many `Review: r1, r2`, and one `Review: r1` belongs to one `User: Tom`.
--          / Review-1
--User-1 ---- Review-2
--          \ Review-3

DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
  user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  last_name VARCHAR(30),
  first_name VARCHAR(20)
);
INSERT INTO users (last_name,first_name)
VALUES
  ('Anderson','Jillian'),
  ('Kenton','Leo'),
  ('McGavin','Darrin');

CREATE TABLE reviews
(
  review_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  review VARCHAR(50),
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);
INSERT INTO reviews(review, user_id)
VALUES
  ('Review-a-1 from user-1', 1),
  ('Review-b-1 from user-2', 2),
  ('Review-a-2 from user-1', 1),
  ('Review-a-3 from user-1', 1),
  ('Review-b-2 from user-2', 2),
  ('Review-c-1 from user-3', 3);
SELECT first_name, last_name, review
FROM users JOIN reviews ON users.user_id = reviews.user_id;
