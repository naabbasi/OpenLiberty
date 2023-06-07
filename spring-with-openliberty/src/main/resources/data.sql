TRUNCATE orders CASCADE;
TRUNCATE customers CASCADE;

INSERT INTO customers (customer_name) VALUES ('Imtiaz Ali');
INSERT INTO customers (customer_name) VALUES ('Noman Ali');
INSERT INTO customers (customer_name) VALUES ('Farhan Ali');
INSERT INTO customers (customer_name) VALUES ('Arsalan Ali');

INSERT INTO orders (order_amount, customer_gkey) VALUES (1000.00, 1);
INSERT INTO orders (order_amount, customer_gkey) VALUES (10.00, 1);
INSERT INTO orders (order_amount, customer_gkey) VALUES (900.00, 2);
INSERT INTO orders (order_amount, customer_gkey) VALUES (800.00, 3);