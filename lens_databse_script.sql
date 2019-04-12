
create database if not exists lens_db;

use lens_db;

drop table if exists lens_table;

create table if not exists lens_table(
	id int primary key auto_increment,
    lens_name varchar(50) unique not null,
    price int check (price > 0),
    focus_distance int check (focus_distance > 0),
    suitable_for_canon varchar(3),
    suitable_for_nikon varchar(3)
);

insert lens_table (lens_name, price, focus_distance, suitable_for_canon, suitable_for_nikon)
values  ('Canon EF 35/1.4L USM', 1000 , 35 ,'yes', 'no'),
	    ('Canon EF 50/1.2L USM', 1500 , 50 ,'yes', 'no'),
		('Canon EF 50/1.4 USM', 2300 , 50 ,'yes', 'no'),
		('Canon EF 85/1.2L II USM', 1700 , 85 ,'yes', 'no'),
		('Canon EF 85/1.8 USM', 2400 , 85 ,'yes', 'no'),
		('AF-S Nikkor 24/1.4G ED', 1200 , 24 ,'no', 'yes'),
		('AF-S DX Nikkor 35/1.8G', 2700 , 35 ,'no', 'yes'),
		('AF-S Nikkor 50/1.8G', 3200 , 50 ,'no', 'yes'),
		('AF Nikkor 50/1.8D', 800 , 50 ,'no', 'yes'),
		('AF-S Nikkor 85/1.4G', 1900 , 85 ,'no', 'yes');
