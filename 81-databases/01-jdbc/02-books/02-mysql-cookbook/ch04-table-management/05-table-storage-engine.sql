
SELECT ENGINE FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cookbook' AND TABLE_NAME = 'mail';
/*
InnoDB supports transactions, whereas MyISAM does not.

+--------+
| ENGINE |
+--------+
| InnoDB |
+--------+
1 row in set (0.00 sec)
*/

SHOW TABLE STATUS LIKE 'mail'\G
/*
*************************** 1. row ***************************
           Name: mail
         Engine: InnoDB
        Version: 10
     Row_format: Dynamic
           Rows: 16
 Avg_row_length: 1024
    Data_length: 16384
Max_data_length: 0
   Index_length: 16384
      Data_free: 0
 Auto_increment: NULL
    Create_time: 2015-11-25 09:24:40
    Update_time: 2015-11-25 09:50:43
     Check_time: NULL
      Collation: utf8_general_ci
       Checksum: NULL
 Create_options:
        Comment:
1 row in set (0.00 sec)
*/

SHOW CREATE TABLE mail\G
/*
*************************** 1. row ***************************
       Table: mail
Create Table: CREATE TABLE `mail` (
  `t` datetime DEFAULT NULL,
  `srcuser` varchar(8) DEFAULT NULL,
  `srchost` varchar(20) DEFAULT NULL,
  `dstuser` varchar(8) DEFAULT NULL,
  `dsthost` varchar(20) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  KEY `t` (`t`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
1 row in set (0.00 sec)
*/

ALTER TABLE mail ENGINE = MyISAM;
SELECT ENGINE FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'cookbook' AND TABLE_NAME = 'mail';
/*
+--------+
| ENGINE |
+--------+
| MyISAM |
+--------+
1 row in set (0.00 sec)
*/

ALTER TABLE mail ENGINE = InnoDB;