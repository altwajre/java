/*
# execute sql
$ mysql cookbook < mail-table.sql
*/

DROP TABLE IF EXISTS mail;

CREATE TABLE mail
(
  t DATETIME, # when message was sent
  srcuser VARCHAR(8), # sender (source user and host)
  srchost VARCHAR(20),
  dstuser VARCHAR(8), # recipient (destination user and host)
  dsthost VARCHAR(20),
  size BIGINT, # message size in bytes
  INDEX (t)
);

INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-11 10:15:08','barb','saturn','tricia','mars',58274);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-12 12:48:13','tricia','mars','gene','venus',194925);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-12 15:02:49','phil','mars','phil','saturn',1048);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-12 18:59:18','barb','saturn','tricia','venus',271);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-14 09:31:37','gene','venus','barb','mars',2291);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-14 11:52:17','phil','mars','tricia','saturn',5781);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-14 14:42:21','barb','venus','barb','venus',98151);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-14 17:03:01','tricia','saturn','phil','venus',2394482);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-15 07:17:48','gene','mars','gene','saturn',3824);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-15 08:50:57','phil','venus','phil','venus',978);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-15 10:25:52','gene','mars','tricia','saturn',998532);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-15 17:35:31','gene','saturn','gene','mars',3856);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-16 09:00:28','gene','venus','barb','mars',613);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-16 23:04:19','phil','venus','barb','venus',10294);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-19 12:49:23','phil','mars','tricia','saturn',873);
INSERT INTO mail (t,srcuser,srchost,dstuser,dsthost,size)
VALUES('2014-05-19 22:21:51','gene','saturn','gene','venus',23992);
