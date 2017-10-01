--Person-1 ---- SSN-1

DROP TABLE IF EXISTS ssns;
DROP TABLE IF EXISTS people;
CREATE TABLE people
(
  person_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  last_name VARCHAR(30),
  first_name VARCHAR(20)
);
INSERT INTO people (last_name,first_name)
VALUES
  ('Anderson','Jillian'),
  ('Kenton','Leo'),
  ('McGavin','Darrin');

CREATE TABLE ssns
(
  ssn_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  number INT,
  person_id INT NOT NULL,
  FOREIGN KEY (ssn_id) REFERENCES people (person_id)
);
INSERT INTO ssns(number, person_id)
VALUES
  (123, 1),
  (456, 2),
  (789, 3);
SELECT first_name, last_name, number as 'ssn-number'
FROM people JOIN ssns ON people.person_id = ssns.person_id;
