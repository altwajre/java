
# issue a SET NAMES statement after you connect
SET NAMES 'utf8';

# SET NAMES permits the connection collation to be specified
SET NAMES 'utf8' COLLATE 'utf8_general_ci';
/*
Java-style
jdbc:mysql://localhost/cookbook?characterEncoding=UTF-8
*/