Create Table dealerships(
 dealershipid int auto_increment primary key,
 name varchar(50)  not null,
 address varchar(50),
 phone varchar(12)
 );
 
 create table vehicles(
vin int primary key not null,
name varchar(50) not null,
type varchar(15) not null,
sold bit not null
);
 
 create table inventory(
dealershipID int,
Vin int
);

create table SalesContracts(
id int auto_increment primary key,
vin int,
foreign key (vin) references Vehicles (vin)
);

create table leaseContract(
id int auto_increment primary key,
vin int,
foreign key (vin) references Vehicles (vin)
);

-- Dealerships
INSERT INTO dealerships (name, address, phone) VALUES
('Sunshine Motors', '123 Main St', '123-456-7890'),
('AutoWorld', '456 Elm St', '234-567-8901'),
('DriveTime', '789 Oak Ave', '345-678-9012'),
('FastTrack Autos', '321 Pine Ln', '456-789-0123'),
('Greenlight Cars', '654 Maple Blvd', '567-890-1234');

-- Vehicles
INSERT INTO vehicles (vin, name, type, sold) VALUES
(10001, 'Toyota Camry', 'Sedan', 0),
(10002, 'Ford F-150', 'Truck', 1),
(10003, 'Honda Civic', 'Sedan', 0),
(10004, 'Chevy Tahoe', 'SUV', 1),
(10005, 'Tesla Model 3', 'Sedan', 0);
INSERT INTO vehicles (vin, name, type, sold) VALUES
(10006, 'Nissan Altima', 'Sedan', 0),
(10007, 'Ram 1500', 'Truck', 1),
(10008, 'Kia Sportage', 'SUV', 0),

(10009, 'Hyundai Sonata', 'Sedan', 0),
(10010, 'GMC Sierra', 'Truck', 1),
(10011, 'Mazda CX-5', 'SUV', 0),

(10012, 'Volkswagen Jetta', 'Sedan', 0),
(10013, 'Toyota Tundra', 'Truck', 1),
(10014, 'Ford Escape', 'SUV', 0),

(10015, 'Subaru Legacy', 'Sedan', 0),
(10016, 'Chevrolet Silverado', 'Truck', 1),
(10017, 'Jeep Cherokee', 'SUV', 0),

(10018, 'BMW 3 Series', 'Sedan', 0),
(10019, 'Honda Ridgeline', 'Truck', 1),
(10020, 'Audi Q5', 'SUV', 0);


-- Inventory (dealershipid, vin)
INSERT INTO inventory (dealershipid, vin) VALUES
(1, 10001),
(2, 10002),
(3, 10003),
(4, 10004),
(5, 10005);
-- Dealership 1: Sunshine Motors
INSERT INTO inventory (dealershipid, vin) VALUES
(1, 10006),
(1, 10007),
(1, 10008);

-- Dealership 2: AutoWorld
INSERT INTO inventory (dealershipid, vin) VALUES
(2, 10009),
(2, 10010),
(2, 10011);

-- Dealership 3: DriveTime
INSERT INTO inventory (dealershipid, vin) VALUES
(3, 10012),
(3, 10013),
(3, 10014);

-- Dealership 4: FastTrack Autos
INSERT INTO inventory (dealershipid, vin) VALUES
(4, 10015),
(4, 10016),
(4, 10017);

-- Dealership 5: Greenlight Cars
INSERT INTO inventory (dealershipid, vin) VALUES
(5, 10018),
(5, 10019),
(5, 10020);


-- SalesContracts (only for sold vehicles)
INSERT INTO SalesContracts (vin) VALUES
(10002),
(10004);

-- LeaseContract (can be for unsold vehicles on lease)
INSERT INTO leaseContract (vin) VALUES
(10001),
(10003),
(10005);

-- 1
select *
from dealerships;
-- 2
select name	
from vehicles v 	
join inventory i on v.vin = i.vin
where i.dealershipid = 1;

-- 3
SELECT * 
FROM vehicles 
WHERE vin = 10006;
-- 4
select name
from dealerships d
join inventory i  on d.dealershipID = i.dealershipID
where vin = 10004;
-- 5
select *
from dealerships d
join inventory i  on d.dealershipID = i.dealershipID
join vehicles v on v.vin = i.vin
where v.type = 'sedan';
 -- 6
-- FIRST UPDATE TABLES 
ALTER TABLE SalesContracts
ADD COLUMN dealershipid INT,
ADD COLUMN sale_date DATE,
ADD FOREIGN KEY (dealershipid) REFERENCES dealerships(dealershipid);

INSERT INTO SalesContracts (vin, dealershipid, sale_date) VALUES
(10002, 2, '2025-03-15'),
(10004, 4, '2025-03-20'),
(10007, 1, '2025-04-01'),
(10010, 2, '2025-04-03'),
(10013, 3, '2025-04-10'),
(10016, 4, '2025-04-18'),
(10019, 5, '2025-05-01');

SELECT *
FROM SalesContracts sc
JOIN dealerships d ON sc.dealershipid = d.dealershipid
JOIN vehicles v ON sc.vin = v.vin
WHERE sc.dealershipid = 2
  AND sc.sale_date BETWEEN '2025-01-01' AND '2025-12-31';





