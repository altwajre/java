SELECT * FROM hotel.available_rooms_by_hotel_date;
SELECT * FROM available_rooms_by_hotel_date WHERE hotel_id='AZ123' and date>'2016-01-05' and date<'2016-01-12';
SELECT * FROM available_rooms_by_hotel_date WHERE date='2016-01-25' ALLOW FILTERING;
SELECT * FROM available_rooms_by_hotel_date WHERE hotel_id='AZ123' AND date IN ('2016-01-05', '2016-01-12');
SELECT * FROM available_rooms_by_hotel_date WHERE hotel_id='AZ123' and date>'2016-01-05' and date<'2016-01-12' ORDER BY date DESC;
