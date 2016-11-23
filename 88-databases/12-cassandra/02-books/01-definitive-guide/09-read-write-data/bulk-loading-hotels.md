# Bulk loading hotels

**Bulk loading available_rooms_by_hotel_date**

```
cqlsh> USE hotel;
cqlsh:hotel> DESCRIBE available_rooms_by_hotel_date;

CREATE TABLE hotel.available_rooms_by_hotel_date (
    hotel_id text,
    date date,
    room_number smallint,
    is_available boolean,
    PRIMARY KEY (hotel_id, date, room_number)
) WITH CLUSTERING ORDER BY (date ASC, room_number ASC)
    AND bloom_filter_fp_chance = 0.01
    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
    AND comment = 'Q4. Find available rooms by hotel / date'
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND crc_check_chance = 1.0
    AND dclocal_read_repair_chance = 0.1
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 0.0
    AND speculative_retry = '99PERCENTILE';
cqlsh:hotel> COPY available_rooms_by_hotel_date FROM '/Users/whan/Desktop/java/88-databases/12-cassandra/02-books/01-definitive-guide/09-read-write-data/available_rooms.csv' WITH HEADER=true;
Using 7 child processes

Starting copy of hotel.available_rooms_by_hotel_date with columns ['hotel_id', 'date', 'room_number', 'is_available'].
Processed: 310 rows; Rate:     493 rows/s; Avg. rate:     727 rows/s
310 rows imported from 1 files in 0.427 seconds (0 skipped).
cqlsh:hotel> SELECT * FROM hotel.available_rooms_by_hotel_date;
cqlsh:hotel> SELECT * FROM available_rooms_by_hotel_date WHERE hotel_id='AZ123' and date>'2016-01-05' and date<'2016-01-12';
cqlsh:hotel> SELECT * FROM available_rooms_by_hotel_date WHERE date='2016-01-25' ALLOW FILTERING;
cqlsh:hotel> SELECT * FROM available_rooms_by_hotel_date WHERE hotel_id='AZ123' AND date IN ('2016-01-05', '2016-01-12');
cqlsh:hotel> SELECT * FROM available_rooms_by_hotel_date WHERE hotel_id='AZ123' and date>'2016-01-05' and date<'2016-01-12' ORDER BY date DESC;
```
