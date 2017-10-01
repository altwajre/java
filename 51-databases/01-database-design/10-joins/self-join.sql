-- Self Join
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username varchar(45) DEFAULT NULL,
  referred_by INT DEFAULT NULL,
  FOREIGN KEY (referred_by) REFERENCES users (user_id)
);
INSERT INTO users (username, referred_by)
VALUES
  ('Tom', null),
  ('Dick', 1),
  ('Harry', 1),
  ('Ken',2),
  ('Will',2);
SELECT v1.username, v2.username as 'referred_by'
FROM users as v1
JOIN users as v2
ON v1.referred_by = v2.user_id;
