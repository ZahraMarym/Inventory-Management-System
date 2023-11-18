use inventory;
create table users(
userId int primary key auto_increment,
firstName varchar(15),
lastName varchar(15),
email varchar(30) unique,
userName varchar(20),
userPassword varchar(20)
);
create table customers(
custId int primary key auto_increment,
custName varchar(20),
custAddress varchar(30),
custPhone int,
custAccNo int,
userId int,
constraint userId foreign key(userId) references users(userId)
);
create table categories(
catId int primary key auto_increment,
catName varchar(20)
);
create table products(
productId int primary key auto_increment,
catId int,
productName varchar(20),
productQty int,
retailPrice decimal(10, 2),
salePrice decimal(10, 2),
constraint catId foreign key (catId) references categories(catId)
);
create table orders(
orderId int primary key auto_increment,
custId int,
orderDate date,
profit decimal(10,2),
constraint custId_f foreign key(custId) references customers (custId));
drop table orders;
create table sales(
saleNo int primary key auto_increment,
profit decimal(10, 2),
produstSaleDate date
);
create table order_product(
orderId int,
productId int,
constraint orderId foreign key (orderId) references orders(orderId),
constraint productId foreign key (productId) references products(productId),
primary key(orderId, productId)
);
create table Cust_prod (
custId int,
prodId int,
pQauantity int,
pAmount decimal(10,2),
constraint fk_cust foreign key(custId) references customers (custId),
constraint fk_prdo foreign key (prodId) references products (productId),
primary key(custId,prodId)
);
drop table Cust_prod;
create table payment(
paymentNo int auto_increment,
orderId int,
paymentAmount decimal(10, 2),
accNo int,
paymentDate date,
remainingAmount int,
constraint order_Id foreign key (orderId) references orders(orderId),
primary key(paymentNo, orderId)
);

INSERT INTO `inventory`.`users` (`firstName`, `lastName`, `email`, `userPassword`) 
VALUES ( 'Shaiza', 'Akhtar', 'shaizach07@gmail.com', '9345');
delimiter //
CREATE TRIGGER insert_user
    before INSERT ON `users`
    FOR EACH ROW
BEGIN
declare user_id int;
set user_id := (select max(userId) from users);
if NEW.email is not null then
set NEW.userName = concat (NEW.firstName,"",user_id+1);
END IF;
END //
drop trigger insert_user;
DELETE FROM `inventory`.`users` WHERE (`userId` = '337');
drop table users;
drop table customers;
drop table categories;
drop table products;
drop table orders;
drop table sales;
drop table cust_prod;
drop table order_product;
drop table payment;
INSERT INTO `inventory`.`customers` (`custName`, `custAddress`, `custPhone`, `custAccNo`, `userId`) VALUES ('Shamail', 'MCS', '657849930', '645372901', '2');
INSERT INTO `inventory`.`categories`('catName') VALUES ('Footwear');
INSERT INTO `inventory`.`products` (`catId`, `productName`, `productQty`, `retailPrice`, `salePrice`) VALUES ('111', 'Erasers', '200', '10', '15');
INSERT INTO `inventory`.`sales` (`profit`, `produstSaleDate`) VALUES ('43', '2022-3-7');
INSERT INTO `inventory`.`orders` (`custId`, `orderDate`) VALUES ('11', '2022-5-8');
INSERT INTO `inventory`.`payment` (`orderId`, `paymentAmount`, `accNo`, `paymentDate`, `remainingAmount`) VALUES ('800', '2500', '345678', '2022-7-8', '0');


select * from inventory.users;
update `inventory`.`orders` set `userName`='Zahra33' and `userPassword`='1234' where `userId`='1';
UPDATE `inventory`.`users` SET `userPassword` = '1234' WHERE (`userId` = '1');

truncate table customers;
truncate table categories;
truncate table products;
truncate table payment;
truncate table orders;
truncate table sales;
truncate table cust_prod;
truncate table order_product;
truncate table users;

drop table customers;
drop table categories;
drop table products;
drop table payment;
drop table orders;
drop table sales;
drop table cust_prod;
drop table order_product;
drop table users;


create table chart(
cMonth varchar(12) primary key,
cProfit decimal(10,2)
);
drop table chart;
