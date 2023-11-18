use inventory;
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
if user_id is null then
set NEW.userName = concat (NEW.firstName,"",+1);
END IF;
END //
drop trigger insert_user;
delimiter //
CREATE TRIGGER delete_prod
    before delete ON `categories`
    FOR EACH ROW
BEGIN
DELETE FROM `inventory`.`products` WHERE products.catId = old.catId;
END //

delimiter //
CREATE TRIGGER upd_sales
    before INSERT ON `sales`
    FOR EACH ROW
BEGIN
declare sale_No int;
declare sale_profit decimal(10, 2);
set sale_No := (select max(saleNo) from sales)+1;
set new.profit :=  (select sum(profit)
                    from orders
                    where orderDate = new.produstSaleDate);              
END //

drop trigger upd_sales;
delimiter //
CREATE TRIGGER insert_orders
    before INSERT On cust_prod
    FOR EACH ROW
BEGIN
	declare order_id int;
    declare order_Date date;
    declare profit decimal(10,2);
    declare cust_Id int;
    declare Quantity int;
    set Quantity = new.PQauantity;
    set cust_Id = new.custId;
    set Profit = (select salePrice-retailPrice from products where productId = new.prodId)*Quantity;
    set order_Date = CURDATE();
INSERT INTO `inventory`.`orders` (`custId`, `orderDate`,`profit`) VALUES (cust_Id, order_Date,Profit);
END //
drop trigger insert_orders;

delimiter //
CREATE TRIGGER update_productQty
before INSERT ON `cust_prod`
    FOR EACH ROW
BEGIN
declare newQty int;
declare oldQty int;
declare prod_Id int;
set newQty := new.pQauantity;
set prod_Id := new.prodId;
set oldQty := (select productQty from products where productId = prod_Id);
if (newQty < oldQty) then
update products
set productQty =  oldQty - newQty where productId = prod_Id;
END IF;
END //
drop trigger update_productQty;

delimiter //
CREATE TRIGGER upd_sales
    before INSERT ON `sales`
    FOR EACH ROW
BEGIN
declare sale_No int;
declare sale_profit decimal(10, 2);
set sale_No := (select max(saleNo) from sales)+1;
set new.profit :=  (select sum(profit)
                    from orders
                    where orderDate = new.produstSaleDate);              
END //


DELIMITER //
create trigger atInsertPayment
before INSERT ON payment FOR EACH ROW  
BEGIN
declare tAmount decimal(10, 2);
set new.paymentDate = curdate();
set tAmount := (select pAmount from cust_prod
                where prodId = (select productId
from order_product where orderId = new.orderId));
if (new.paymentAmount <= tAmount) then
set new.remainingAmount = tAmount-new.paymentAmount;
end if;
if(new.orderId=any(select orderId from payment))  then
set new.remainingAmount=(select remainingAmount from payment where orderId=new.orderId)-new.paymentAmount;
end if;
end //
drop trigger atInsertPayment


delimiter //
create trigger insert_orderprod
after insert on cust_prod
for each row
begin
  declare product_Id int;
  declare order_id int;
  set product_Id=new.prodId ;
  set order_Id := (select max(orderId) from orders where custId=new.custId);
  INSERT INTO `inventory`.`order_product` (`orderId`, `productId`) VALUES (order_Id,product_Id);
end//

delimiter //
 create trigger updatepAmount
 before insert on cust_prod
 for each row
 begin
       set new.pAmount=(select salePrice from products where productId=new.prodId)* new.pQauantity;
 end//
 
 drop trigger updatepAmount


delimiter //
create trigger insert_chart
after insert on sales for each row
begin
declare monthName varchar(20);
declare Cprofit decimal(10,2);
set monthName = monthname(new.produstSaleDate);
set Cprofit = (select sum(profit)
 from orders where monthname(orderDate) = monthName);
if monthname(new.produstSaleDate) not in (select cMonth from chart) then
INSERT INTO `inventory`.`chart` (`cMonth`,`cProfit`) 
VALUES ( monthName ,Cprofit);
end if;
end//
drop trigger insert_chart;