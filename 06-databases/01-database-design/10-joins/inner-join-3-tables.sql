-- tom and video-X are excluded in join on 3 tables.
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS videos;
CREATE TABLE users
(
  user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(30)
);
INSERT INTO users (username)
VALUES
  ('anderson'),
  ('kenton'),
  ('tom');
CREATE TABLE videos
(
  video_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  video_title VARCHAR(30)
);
INSERT INTO videos (video_title)
VALUES
  ('video-1'),
  ('video-2'),
  ('video-X');
CREATE TABLE comments
(
  comment_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  comment VARCHAR(50),
  user_id INT,
  video_id INT,
  FOREIGN KEY (user_id) REFERENCES users (user_id),
  FOREIGN KEY (video_id) REFERENCES videos (video_id)
);
INSERT INTO comments(comment, user_id, video_id)
VALUES
  ('comment-v-1-a-1 from user-1', 1, 1),
  ('comment-v-1-a-2 from user-1', 1, 1),
  ('comment-v-1-b from user-2', 1, 2),
  ('comment-v-2-a from user-1', 2, 1);
SELECT username, video_title, comment
FROM users
JOIN comments ON users.user_id = comments.user_id
JOIN videos ON comments.video_id = videos.video_id;
