DROP TABLE if exists orders;
DROP TABLE if exists customers;

create table if not exists customers (
    gkey INT GENERATED ALWAYS AS IDENTITY,
    customer_name varchar(255),
    primary key (gkey)
);

create table if not exists orders (
    gkey INT GENERATED ALWAYS AS IDENTITY,
    order_amount NUMERIC(6,2),
    customer_gkey int,
    primary key (gkey),
    CONSTRAINT fk_order_customer
      FOREIGN KEY(customer_gkey) 
	  REFERENCES customers(gkey)
);