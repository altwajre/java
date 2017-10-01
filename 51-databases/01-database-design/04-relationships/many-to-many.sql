--classes -- intermediary-table -- students
-- one  <---   many     many   --->  one
--  PK          FK       FK          PK

DROP TABLE IF EXISTS classes_students;
DROP TABLE IF EXISTS classes;
DROP TABLE IF EXISTS students;
CREATE TABLE classes
(
  class_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  class_name VARCHAR(30)
);
INSERT INTO classes (class_name)
VALUES
  ('Math'),
  ('Accounting'),
  ('History');
CREATE TABLE students
(
  student_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  student_name VARCHAR(50)
);
INSERT INTO students(student_name)
VALUES
  ('Tom'),
  ('Dick'),
  ('Harry');
--  intermediary table
CREATE TABLE classes_students
(
  class_student_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  class_id INT NOT NULL,
  FOREIGN KEY (class_id) REFERENCES classes (class_id),
  student_id INT NOT NULL,
  FOREIGN KEY (student_id) REFERENCES students (student_id)
);
INSERT INTO classes_students(class_id, student_id)
VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (2, 1),
  (2, 2),
  (3, 2),
  (3, 3);

SELECT class_name, student_name
FROM classes
JOIN classes_students ON classes.class_id = classes_students.class_id
JOIN students ON classes_students.student_id = students.student_id;
