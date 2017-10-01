DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS memberships;
-- Parent
CREATE TABLE memberships
(
  membership_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  membership_name VARCHAR(30),
  price INT   -- adding complexity to describe the type of the membership
);
INSERT INTO memberships (membership_name,price)
VALUES
  ('Gold',60),
  ('Silver',30),
  ('Bronze',10);
-- Child
CREATE TABLE members
(
  member_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  member_name VARCHAR(30),
  membership_id INT NOT NULL,
  FOREIGN KEY (membership_id) REFERENCES memberships (membership_id) ON DELETE CASCADE
);
INSERT INTO members(member_name, membership_id)
VALUES
  ('Tom', 1),
  ('Dick', 1),
  ('Harry', 2),
  ('Ken', 2),
  ('Will', 3);
DELETE FROM memberships WHERE membership_id = 3;
SELECT membership_name, member_name
FROM memberships JOIN members ON memberships.membership_id = members.membership_id;
