
DROP TABLE IF EXISTS profile_contact;

CREATE TABLE profile_contact
(
  profile_id   INT UNSIGNED NOT NULL, # ID from profile table
  service      VARCHAR(20) NOT NULL,  # social media service name
  contact_name VARCHAR(25) NOT NULL,  # name to use for contacting person
  INDEX(profile_id)
);

INSERT INTO profile_contact (profile_id,service,contact_name) VALUES(1,'Fackbook','user1-fbid');
INSERT INTO profile_contact (profile_id,service,contact_name) VALUES(1,'Twitter','user1-twtrid');
INSERT INTO profile_contact (profile_id,service,contact_name) VALUES(2,'Fackbook','user2-msnid');
INSERT INTO profile_contact (profile_id,service,contact_name) VALUES(2,'LinkedIn','user2-msnid');
INSERT INTO profile_contact (profile_id,service,contact_name) VALUES(2,'Twitter','user2-fbrid');
INSERT INTO profile_contact (profile_id,service,contact_name) VALUES(4,'LinkedIn','user4-lnkdid');
