
DROP TABLE IF EXISTS profile;

CREATE TABLE profile
(
  id    INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name  VARCHAR(20) NOT NULL,
  birth DATE,
  color ENUM('blue','red','green','brown','black','white'),
  foods SET('lutefisk','burrito','curry','eggroll','fadge','pizza'),
  cats INT,
  PRIMARY KEY (id)
);

INSERT INTO profile (name,birth,color,foods,cats) VALUES('Sybil','1970-04-13','black','lutefisk,fadge,pizza',0);
INSERT INTO profile (name,birth,color,foods,cats) VALUES('Nancy','1969-09-30','white','burrito,curry,eggroll',3);
INSERT INTO profile (name,birth,color,foods,cats) VALUES('Ralph','1973-11-02','red','eggroll,pizza',4);
INSERT INTO profile (name,birth,color,foods,cats) VALUES('Lothair','1963-07-04','blue','burrito,curry',5);
INSERT INTO profile (name,birth,color,foods,cats) VALUES('Henry','1965-02-14','red','curry,fadge',1);
INSERT INTO profile (name,birth,color,foods,cats) VALUES('Aaron','1968-09-17','green','lutefisk,fadge',1);
INSERT INTO profile (name,birth,color,foods,cats) VALUES('Joanna','1952-08-20','green','lutefisk,fadge',0);
INSERT INTO profile (name,birth,color,foods,cats) VALUES('Stephen','1960-05-01','white','burrito,pizza',0);
