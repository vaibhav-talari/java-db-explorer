-- Customers
INSERT INTO customers (name, email) VALUES
('Alice Johnson', 'alice@example.com'),
('Bob Smith', 'bob@example.com');

-- Products
INSERT INTO products (name, description, price, stock_quantity, category) VALUES
('Laptop', '15-inch display', 1299.99, 10, 'ELECTRONICS'),
('Headphones', 'Noise-cancelling', 199.99, 25, 'ACCESSORIES'),
('Coffee Mug', 'Ceramic, 350ml', 9.99, 100, 'HOME');

-- Orders
INSERT INTO orders (customer_id, status, total_amount) VALUES
(1, 'NEW', 1500.00),
(2, 'NEW', 209.98);

-- Order Items
INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES
(1, 1, 1, 1299.99),
(1, 2, 1, 199.99),
(2, 2, 1, 199.99),
(2, 3, 1, 9.99);

-- Payments
INSERT INTO payments (order_id, payment_method, amount, successful) VALUES
(1, 'CARD', 1499.98, TRUE),
(2, 'PAYPAL', 209.98, TRUE);
