/*
noninteractive

tabular or tab-delimited output
$ mysql cookbook < 07-output-and-format.sql

HTML output
$ mysql -H -e "SELECT * FROM limbs WHERE legs=0" cookbook
save output in a file.
$ mysql -H -e "SELECT * FROM limbs WHERE legs=0" cookbook > limbs.html
$ open -a safari limbs.html

XML output
$ mysql -X -e "SELECT * FROM limbs WHERE legs=0" cookbook
XSLT transforms
$ mysql -X -e "SELECT * FROM limbs WHERE legs=0" cookbook | xsltproc mysql-xml.xsl -

suppressing column headings in query output
$ mysql --skip-column-names -e "SELECT arms FROM limbs" cookbook
$ mysql -ss -e "SELECT arms FROM limbs" cookbook

control verbosity level
$ echo "SELECT NOW()" | mysql
$ echo "SELECT NOW()" | mysql -v
$ echo "SELECT NOW()" | mysql -vv
$ echo "SELECT NOW()" | mysql -vvv
*/

# interactive
SELECT * FROM limbs WHERE legs=0;
