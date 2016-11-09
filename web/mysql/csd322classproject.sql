# CSD320 PHP and MySQL Assignment 1
# Aaron Knight
# September 7 2014

# Product Database

-- Use the product_ak Database 
USE fred_carella_csd322classproject;


-- Create table for Supplier Region
#DROP TABLE IF EXISTS supplier_region;
CREATE TABLE IF NOT EXISTS supplier_region (
	region_code INT(2) NOT NULL AUTO_INCREMENT,
	region_desc CHAR(45),
	PRIMARY KEY (region_code)
)engine=innodb;

-- Create Table for Suppliers
#DROP TABLE IF EXISTS suppliers;
CREATE TABLE IF NOT EXISTS suppliers (
	supplier_code INT(2) NOT NULL AUTO_INCREMENT,
	supplier_name CHAR(45),
	region INT(2),
	PRIMARY KEY (supplier_code),
	INDEX `fk_suppliers_region_idx`(region ASC),
	CONSTRAINT `fk_suppliers_region`
	FOREIGN KEY (region) REFERENCES supplier_region(region_code)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
)engine=innodb;

-- Create Table for Product Category
#DROP TABLE IF EXISTS product_category;
CREATE TABLE IF NOT EXISTS product_category (
	product_cat_code INT (3) NOT NULL AUTO_INCREMENT,
	product_cat_desc varchar(45) NOT NULL,
	PRIMARY KEY (product_cat_code)
)engine=innodb;

-- Create Table for Products
#DROP TABLE IF EXISTS products;
CREATE TABLE IF NOT EXISTS products(
	product_id INT(3) NOT NULL AUTO_INCREMENT,
	product_name CHAR(30),
	unit_price FLOAT,
	units_on_hand INT,
	units_on_reorder INT,
	reorder_lvl INT,
	discontinued_status BOOL,
	supplier_code INT(2),
	PRIMARY KEY (product_id),
	INDEX `fk_products_product_id_idx`(product_id ASC),
	CONSTRAINT `fk_products_product_id`
	FOREIGN KEY (product_id) REFERENCES product_category(product_cat_code),
	INDEX `fk_products_supplier_code_idx`(supplier_code ASC),
	CONSTRAINT `fk_products_supplier_code`
	FOREIGN KEY (supplier_code) REFERENCES suppliers(supplier_code)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
)engine=innodb;

-- Create Table for Customers

#DROP TABLE IF EXISTS customers;
CREATE TABLE IF NOT EXISTS customers(
	customer_id INT(3) NOT NULL AUTO_INCREMENT,
	customer_FName CHAR(15),
	customer_LName CHAR(15),
	customer_address VARCHAR(30),
	customer_city CHAR(30),
	customer_PC VARCHAR(6),
	PRIMARY KEY(customer_id)
)engine=innodb;

#DROP TABLE IF EXISTS discount;
CREATE TABLE IF NOT EXISTS discount(
	discount_code INT,
	discount_percentage FLOAT NOT NULL,
	PRIMARY KEY(discount_code)
)engine=innodb;
-- Create table for Orders

#DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders(
	order_id INT NOT NULL AUTO_INCREMENT,
	customer_id INT (3),
	order_date DATE,
	product_ordered CHAR(25),
	PRIMARY KEY(order_id),
	INDEX `fk_orders_customer_id_idx`(customer_id ASC),
	CONSTRAINT `fk_orders_customer_id`
	FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
)engine=innodb;

-- Create table order invoice

#DROP TABLE IF EXISTS order_invoice;
CREATE TABLE IF NOT EXISTS order_invoice(
	invoice_id INT NOT NULL AUTO_INCREMENT,
	product_id INT NOT NULL,
	order_id INT NOT NULL,
	quantity INT,
	discount_code INT,
	PRIMARY KEY (invoice_id), 
	INDEX `fk_order_invoice_product_id_idx`(product_id ASC),
	CONSTRAINT `fk_order_invoice_product_id`
	FOREIGN KEY (product_id) REFERENCES products(product_id),
	INDEX `fk_order_invoice_order_id_idx`(order_id ASC),
	CONSTRAINT `fk_order_invoice_order_id`
	FOREIGN KEY (order_id) REFERENCES orders(order_id),
	INDEX `fk_order_invoice_discount_code_idx`(discount_code ASC),
	CONSTRAINT `fk_order_invoice_discount_code`
	FOREIGN KEY (discount_code) REFERENCES discount(discount_code)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
)engine=innodb;


-- Product Database population Section

-- Insert supplier Regions


INSERT INTO supplier_region(region_desc) 
VALUES ("British Columbia"), -- Region 1
	   ("Alberta"),  		 -- Region 2
	   ("Maintoba"),		 -- Region 3
	   ("Saskatchewan"),	 -- Region 4
       ("Ontario"),	         -- Region 5
	   ("Quebec"),           -- Region 6
	   ("PEI"),              -- Region 7
	   ("NovaScotia"),       -- Region 8
	   ("Newfoundland"),     -- Region 9
	   ("Yukon"),            -- Region 10
	   ("NWT"),              -- Region 11
	   ("Nunavut");          -- Region 12



-- Insert Suppliers

INSERT INTO suppliers(supplier_name,region)
VALUES ("Walmart", 6), 			-- Supplier Code 1
	   ("KMART", 4), 			-- Supplier Code 2
	   ("Target", 4), 			-- Supplier Code 3
       ("Canadian Tire", 2), 	-- Supplier Code 4
       ("Sears", 9), 			-- Supplier Code 5
       ("Walmart", 8);			-- Supplier Code 6
 


-- Insert Product Category

INSERT INTO product_category (product_cat_desc) 
VALUES ("Paper"),		-- Cat 1
	   ("Toys"),		-- Cat 2
	   ("Automotive"),	-- Cat 3
	   ("Sports"),      -- Cat 4
	   ("Clothing"),    -- Cat 5
	   ("Grocery"),     -- Cat 6
	   ("Electronics"); -- Cat 7
-- Insert Products

INSERT INTO products (product_name, unit_price, units_on_hand, units_on_reorder, reorder_lvl, discontinued_status, supplier_code)
VALUES ("32 LED TV", 329.99, 70, 20, 4, 0, 1),						-- PC 1
	   ("Windshield Wiper Blades", 12.99, 70, 20, 3, 0, 2),		-- PC 2
	   ("Kids Shoes", 49.99, 70, 40, 1, 0, 5),					-- PC 3
       ("Baseball Blove ", 79.99, 70, 10, 2, 0, 2),				-- PC 4
       ("Blanket Set", 109.99, 70, 0, 5, 1, 2);  					-- PC 5

-- Insert Customers

INSERT INTO customers (customer_FName, customer_LName, customer_address, customer_city, customer_PC)
VALUES ("Aaron","Knight","78 Pawating Place","Sault Ste Marie","P6B6J2"),	-- Customer 1
       ("Amber","Almeida","123 Some Street","Sudbury","T5U3R5"),			-- Customer 2
       ("Evan","Dupuis","1232 Fake Street","Montreal","U7F4L8"),			-- Customer 3
       ("Abigail","Dupuis","231 Fake Street","Calgary","T4W3Y6"),			-- Customer 4
       ("Khaleesi","Knight","21421 That Street","Vancouver","Y7R4J6"),	    -- Customer 5
	   ("Chuck","Wagon","23423 This Street","Whitehorse","I9Y7R5");         -- Customer 6

-- Insert Discounts

INSERT INTO discount (discount_code, discount_percentage)
VALUES  (0,1.0),	-- 0-19 units
		(1,1.1), 	-- 20-39 units
		(2,1.2),	-- 40-59 units
		(3,1.3);	-- 60+ units
-- Insert Orders

INSERT INTO orders (customer_id,order_date,product_ordered)
VALUES	(2,"2014-07-14","32 LED TV"),
	    (2,"2014-07-14","Kids Shoes"),
		(4,"2014-07-14","Windshield Wiper Blades"),
		(6,"2014-5-24","Blanket Set"),
		(5,"2014-5-24","Kids Shoes"),
		(4,"2014-5-17","Kids Shoes"),
		(1,"2014-5-17","32 LED TV");
		
	
-- Invoice

INSERT INTO order_invoice (product_id, order_id,quantity,discount_code)
VALUES	(1,1,10,0),
		(3,1,20,1),
		(3,2,30,1),
		(2,3,40,2),
		(5,4,50,2),
		(3,5,60,3),
		(1,3,61,3);
	
