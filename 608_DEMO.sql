DROP DATABASE IF EXISTS INVENTORY_DEMO;
CREATE DATABASE IF NOT EXISTS INVENTORY_DEMO;
USE INVENTORY_DEMO;


CREATE TABLE CUSTOMER (
	Customer_id 		integer not null,
	Fname 				varchar(20) not null,  
	Lname 				varchar(20) not null,  
	Address 			varchar(50), 
	Postal_code 		varchar(7),  
	Phone_number 		varchar(12), 
	Customer_type 		char(10), 
	primary key (Customer_id)
); 

INSERT INTO CUSTOMER (Customer_id,  Fname,  Lname,  Address,  Postal_code,  Phone_number, Customer_type)
VALUES
('1',	'Fred',	'Flintstone',	'34 Flintrock Way',	'T3R 5B6',	'403-295-9076',	'C'),
('2',	'Bugs',	'Bunny',	'22 Rabbit Hole Park',	'W2A 5K8',	'403-288-7213',	'R'),
('3',	'Samuel',	'Ludlow III',	'2345 The Rich Man Way',	'X2R 3M6',	'403-324-9812',	'C'),
('4',	'Bill',	'Partley',	'11 Partway Road',	'V7Z 4G7',	'403-232-9876',	'R'),
('5',	'Dr.',	'Evil',	'Some Where in the Caribean',	'N4W 8H3',	'999-999-9999',	'C'),
('6',	'Perky',	'Volunteer',	'343 Volly Road',	'B4U 8V3',	'403-345-9876',	'R'),
('7',	'BobBob',	'Never',	'22 NeverReally Lane',	'C2K 2N0',	'403-674-7865',	'R'),
('8',	'Homer',	'Simpson',	'345 Doooah Drive',	'D0A 0H0',	'403-855-4287',	'R'),
('9',	'Wilbur',	'Wimp',	'44 WumpWump Street',	'W1M 3P3',	'403-555-8888',	'R'),
('10',	'Really',	'Depressed',	'33 Has No Life Drive',	'B2C 4S7',	'403-456-7890',	'R'),
('11',	'Sam',	'Packitaway',	'345 Stash Road',	'Y2X 1L6',	'403-546-9867',	'C'),
('12',	'Nurse',	'Betty',	'32 Way Out There',	'M5W 4E6',	'403-456-7766',	'R'),
('13',	'Donald',	'Duck',	'76 Duck Pond Lawn',	'Z5X 8F4',	'403-926-9165',	'C'),
('14',	'Joe',	'Blow',	'450 Blowout Road',	'Y8V 7N2',	'403-743-9854',	'R'),
('15',	'Pebbles',	'Flintstone',	'34 Flintrock Way',	'T3R 5B6',	'403-295-9076',	'C'),
('16',	'Barney',	'Rubble',	'876 Rimrock Place',	'Y4H 4M7',	'403-765-2763',	'C'),
('17',	'Huey',	'Duck',	'76 Duck Pond Lawn',	'Z5X 8F4',	'403-926-9165',	'R'),
('18',	'Wylie',	'Coyote',	'9 Desert Way',	'T9M 4G7',	'403-453-9876',	'C'),
('19',	'Foghorn',	'Leghorn',	'34 Chicken Coop Loop',	'B7Y 8N4',	'403-765-0834',	'C'),
('20',	'Sylvester',	'Cat',	'88 Kitchen Rug Way',	'Y4F 9Z5',	'403-876-3487',	'R');

CREATE TABLE SUPPLIER ( 
	 Supplier_id      	integer not null, 
	 Supplier_name		varchar(25) unique, 
	 Supplier_type 		varchar(25), 
	 Address      		varchar(50), 
	 Company_name 		varchar(25), 
	 Phone_number 		varchar(25), 
	 primary key (Supplier_id)
 );

INSERT INTO SUPPLIER ( Supplier_id,  Supplier_name,  Supplier_type,  Address,  Company_name,  Phone_number) 
VALUES 
('8001',	'Grommet Builders',	'International',	'788 30th St., SE, Calgary',	'Oracle',	'123-756-4578'),
('8002',	'Pong Works',	'Local',	'749 Dufferin Blvd., SE, Calgary',	'Oracle',	'452-478-1245'),
('8003',	'Wiley Inc.',	'International',	'26 40th St., SE, Calgary',	'Tesla',	'698-654-6547'),
('8004',	'Winork Manufacturing Inc.',	'International',	'156 51st Ave., SE, Calgary',	'Oracle',	'123-456-1478'),
('8005',	'Grab Bag Inc.',	'Local',	'138 42nd Ave., NE, Calgary',	'Tesla',	'123-456-7896'),
('8006',	'Paddy\'s Manufacturing',	'International',	'311 McCall Way, NE, Calgary',	'Tesla',	'123-456-7852'),
('8007',	'Smickles Industries',	'International',	'966 Smed Lane, SE, Calgary',	'Tesla',	'124-589-6325'),
('8008',	'Thangs Inc.',	'International',	'879 37th Ave., NE, Calgary',	'Tesla',	'456-789-2546'),
('8009',	'Flip Works Inc.',	'International',	'472 Ogden Dale Rd., SE, Calgary',	'Tesla',	'796-254-1248'),
('8010',	'Irkle Industries',	'International',	'754 Sunridge Way, NE, Calgary',	'Oracle',	'369-547-3149'),
('8011',	'Biff Builders',	'International',	'690 19th St., NE, Calgary',	'Tesla',	'148-962-4579'),
('8012',	'Twinkles Inc.',	'Local',	'318 29th St.,NE, Calgary',	'UofC',	'145-124-1247'),
('8013',	'Piddles Industries',	'International',	'238 112th Ave., NE, Calgary',	'UofC',	'125-124-1256'),
('8014',	'Tic Tac Manufacturing',	'Local',	'598 Palmer Rd., NE, Calgary',	'UofC',	'127-825-4547');

 CREATE TABLE ITEM ( 
	 Item_id 			integer not null, 
	 Item_name 			varchar(25) unique,
	 Item_quantity 		integer, 
	 Item_price 		decimal(10,2), 
	 Item_type 			varchar(25),
	 Supplier_id 		integer, 
	 primary key (Item_id), 
	 foreign key (Supplier_id) references SUPPLIER(Supplier_id) ON UPDATE CASCADE ON DELETE SET NULL
 );
 
 
 INSERT INTO ITEM ( Item_id,  Item_name,  Item_quantity,  Item_price,  Item_type,  Supplier_id) 
 VALUES 
('1000',	'Knock Bits',	'88',	'12.67',	'Electrical',	'8012'),
('1001',	'Widgets',	'10',	'35.50',	'Non-Electrical',	'8004'),
('1002',	'Grommets',	'20',	'23.45',	'Electrical',	'8001'),
('1003',	'Wedges',	'15',	'10.15',	'Non-Electrical',	'8011'),
('1004',	'Wing Bats',	'11',	'11.25',	'Electrical',	'8003'),
('1005',	'Twinkies',	'75',	'15.75',	'Electrical',	'8012'),
('1006',	'Wiggles',	'30',	'12.34',	'Electrical',	'8010'),
('1007',	'Bing Bobs',	'25',	'2.39',	'Non-Electrical',	'8005'),
('1008',	'Wog Wits',	'300',	'19.99',	'Electrical',	'8004'),
('1009',	'Barney Bits',	'21',	'23.59',	'Non-Electrical',	'8006'),
('1010',	'Willie Widgits',	'89',	'12.99',	'Electrical',	'8003');
 
 CREATE TABLE PURCHASE (
	 Item_id 			integer not null, 
	 Customer_id  		integer not null, 
	 primary key (Item_id,Customer_id),
	 foreign key (Item_id) references ITEM(Item_id) ON UPDATE CASCADE, 
	 foreign key (Customer_id) references CUSTOMER(Customer_id) ON UPDATE CASCADE 
 );
 
 
 CREATE TABLE ORDERS (
	 Order_id 			integer not null, 
	 Order_date  		date, 
	 primary key (Order_id) 
 );
 
 
 
 CREATE TABLE ORDERLINE ( 
	 Order_id 			integer not null, 
	 Item_id 			integer not null,
	 Supplier_id 		integer , 
	 Amount_ordered 	integer, 
	 primary key (Order_id, Item_id),
	 foreign key (Item_id) references ITEM(Item_id) ON UPDATE CASCADE , 
	 foreign key (Order_id) references ORDERS(Order_id) ON UPDATE CASCADE , 
	 foreign key (Supplier_id) references SUPPLIER(Supplier_id)	ON UPDATE CASCADE ON DELETE SET NULL
 );
 
CREATE TABLE ELECTRICAL_ITEM (
	 Item_id 			integer not null, 
	 Power_type 		varchar(10), 
	 primary key (Item_id) 
 );
 
 
CREATE TABLE INTERNATIONAL_SUPPLIER (
	 Supplier_id 		integer not null, 
	 Import_tax 		decimal , 
	 primary key (Supplier_id) 
 );
 
 
