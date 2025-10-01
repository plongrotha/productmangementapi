INSERT INTO employees (age, date_of_birth, email, first_name, full_name, last_name, password, phone, username)
VALUES
    (28, '1996-03-15', 'john.smith@company.com', 'John', 'John Smith', 'Smith', 'Pass123!', '555-1001', 'jsmith'),
    (34, '1990-07-22', 'sarah.johnson@company.com', 'Sarah', 'Sarah Johnson', 'Johnson', 'Pass123!', '555-1002', 'sjohnson'),
    (26, '1998-11-08', 'michael.chen@company.com', 'Michael', 'Michael Chen', 'Chen', 'Pass123!', '555-1003', 'mchen'),
    (42, '1982-05-30', 'emily.davis@company.com', 'Emily', 'Emily Davis', 'Davis', 'Pass123!', '555-1004', 'edavis'),
    (31, '1993-09-12', 'david.martinez@company.com', 'David', 'David Martinez', 'Martinez', 'Pass123!', '555-1005', 'dmartinez'),
    (29, '1995-02-18', 'jessica.wilson@company.com', 'Jessica', 'Jessica Wilson', 'Wilson', 'Pass123!', '555-1006', 'jwilson'),
    (45, '1979-12-04', 'robert.brown@company.com', 'Robert', 'Robert Brown', 'Brown', 'Pass123!', '555-1007', 'rbrown'),
    (27, '1997-08-25', 'amanda.taylor@company.com', 'Amanda', 'Amanda Taylor', 'Taylor', 'Pass123!', '555-1008', 'ataylor'),
    (38, '1986-04-16', 'christopher.lee@company.com', 'Christopher', 'Christopher Lee', 'Lee', 'Pass123!', '555-1009', 'clee'),
    (33, '1991-10-09', 'jennifer.anderson@company.com', 'Jennifer', 'Jennifer Anderson', 'Anderson', 'Pass123!', '555-1010', 'janderson'),
    (25, '1999-06-21', 'william.thomas@company.com', 'William', 'William Thomas', 'Thomas', 'Pass123!', '555-1011', 'wthomas'),
    (41, '1983-01-14', 'elizabeth.moore@company.com', 'Elizabeth', 'Elizabeth Moore', 'Moore', 'Pass123!', '555-1012', 'emoore'),
    (30, '1994-05-07', 'james.garcia@company.com', 'James', 'James Garcia', 'Garcia', 'Pass123!', '555-1013', 'jgarcia'),
    (36, '1988-09-28', 'linda.rodriguez@company.com', 'Linda', 'Linda Rodriguez', 'Rodriguez', 'Pass123!', '555-1014', 'lrodriguez'),
    (24, '2000-03-11', 'daniel.hernandez@company.com', 'Daniel', 'Daniel Hernandez', 'Hernandez', 'Pass123!', '555-1015', 'dhernandez'),
    (39, '1985-07-19', 'patricia.lopez@company.com', 'Patricia', 'Patricia Lopez', 'Lopez', 'Pass123!', '555-1016', 'plopez'),
    (32, '1992-11-23', 'mark.gonzalez@company.com', 'Mark', 'Mark Gonzalez', 'Gonzalez', 'Pass123!', '555-1017', 'mgonzalez'),
    (44, '1980-02-05', 'mary.white@company.com', 'Mary', 'Mary White', 'White', 'Pass123!', '555-1018', 'mwhite'),
    (27, '1997-12-17', 'kevin.harris@company.com', 'Kevin', 'Kevin Harris', 'Harris', 'Pass123!', '555-1019', 'kharris'),
    (35, '1989-08-03', 'susan.clark@company.com', 'Susan', 'Susan Clark', 'Clark', 'Pass123!', '555-1020', 'sclark');




INSERT INTO products (is_in_stock, price, quantity, category_id, create_at, update_at, image_url, product_name)
VALUES
    (TRUE, 799.99, 45, 1, '2024-01-15 09:00:00', '2024-01-15 09:00:00', 'https://images.unsplash.com/photo-1592286927505-2fd3f9d6dfd6', 'iPhone 15 Pro'),
    (TRUE, 1299.99, 30, 1, '2024-01-15 09:15:00', '2024-01-15 09:15:00', 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853', 'MacBook Pro 14 inch'),
    (TRUE, 299.99, 120, 1, '2024-01-15 09:30:00', '2024-01-15 09:30:00', 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e', 'Sony WH-1000XM5 Headphones'),
    (FALSE, 89.99, 0, 1, '2024-01-15 09:45:00', '2024-01-15 09:45:00', 'https://images.unsplash.com/photo-1585060544812-6b45742d762f', 'Wireless Mouse'),
    (TRUE, 449.99, 60, 1, '2024-01-15 10:00:00', '2024-01-15 10:00:00', 'https://images.unsplash.com/photo-1560343090-f0409e92791a', 'Samsung Galaxy Watch 6'),
    (TRUE, 49.99, 200, 2, '2024-01-16 10:15:00', '2024-01-16 10:15:00', 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab', 'Classic Cotton T-Shirt'),
    (TRUE, 79.99, 150, 2, '2024-01-16 10:30:00', '2024-01-16 10:30:00', 'https://images.unsplash.com/photo-1542272604-787c3835535d', 'Slim Fit Jeans'),
    (TRUE, 129.99, 80, 2, '2024-01-16 10:45:00', '2024-01-16 10:45:00', 'https://images.unsplash.com/photo-1551028719-00167b16eac5', 'Winter Jacket'),
    (TRUE, 39.99, 250, 2, '2024-01-16 11:00:00', '2024-01-16 11:00:00', 'https://images.unsplash.com/photo-1556821840-3a63f95609a7', 'Sports Running Shoes'),
    (TRUE, 24.99, 300, 2, '2024-01-16 11:15:00', '2024-01-16 11:15:00', 'https://images.unsplash.com/photo-1588117260148-b47818741c74', 'Baseball Cap'),
    (TRUE, 299.99, 40, 3, '2024-01-17 11:30:00', '2024-01-17 11:30:00', 'https://images.unsplash.com/photo-1556909172-54557c7e4fb7', 'Stainless Steel Blender'),
    (TRUE, 899.99, 25, 3, '2024-01-17 11:45:00', '2024-01-17 11:45:00', 'https://images.unsplash.com/photo-1574269909862-7e1d70bb8078', 'Smart Refrigerator'),
    (TRUE, 149.99, 70, 3, '2024-01-17 12:00:00', '2024-01-17 12:00:00', 'https://images.unsplash.com/photo-1585515320310-259814833e62', 'Coffee Maker Machine'),
    (TRUE, 59.99, 180, 3, '2024-01-17 12:15:00', '2024-01-17 12:15:00', 'https://images.unsplash.com/photo-1610701596007-11502861dcfa', 'Ceramic Dinnerware Set'),
    (TRUE, 199.99, 55, 3, '2024-01-17 12:30:00', '2024-01-17 12:30:00', 'https://images.unsplash.com/photo-1595515106969-1ce29566ff1c', 'Vacuum Cleaner'),
    (TRUE, 19.99, 500, 4, '2024-01-18 13:00:00', '2024-01-18 13:00:00', 'https://images.unsplash.com/photo-1544947950-fa07a98d237f', 'The Great Gatsby'),
    (TRUE, 24.99, 400, 4, '2024-01-18 13:15:00', '2024-01-18 13:15:00', 'https://images.unsplash.com/photo-1512820790803-83ca734da794', 'To Kill a Mockingbird'),
    (TRUE, 29.99, 350, 4, '2024-01-18 13:30:00', '2024-01-18 13:30:00', 'https://images.unsplash.com/photo-1543002588-bfa74002ed7e', '1984 by George Orwell'),
    (TRUE, 22.99, 450, 4, '2024-01-18 13:45:00', '2024-01-18 13:45:00', 'https://images.unsplash.com/photo-1541963463532-d68292c34b19', 'Pride and Prejudice'),
    (FALSE, 34.99, 0, 4, '2024-01-18 14:00:00', '2024-01-18 14:00:00', 'https://images.unsplash.com/photo-1524995997946-a1c2e315a42f', 'Harry Potter Collection'),
    (TRUE, 89.99, 90, 5, '2024-01-19 14:15:00', '2024-01-19 14:15:00', 'https://images.unsplash.com/photo-1517836357463-d25dfeac3438', 'Yoga Mat Premium'),
    (TRUE, 249.99, 50, 5, '2024-01-19 14:30:00', '2024-01-19 14:30:00', 'https://images.unsplash.com/photo-1571902943202-507ec2618e8f', 'Adjustable Dumbbells Set'),
    (TRUE, 399.99, 35, 5, '2024-01-19 14:45:00', '2024-01-19 14:45:00', 'https://images.unsplash.com/photo-1576678927484-cc907957088c', 'Mountain Bike'),
    (TRUE, 179.99, 65, 5, '2024-01-19 15:00:00', '2024-01-19 15:00:00', 'https://images.unsplash.com/photo-1551698618-1dfe5d97d256', 'Camping Tent 4-Person'),
    (TRUE, 129.99, 75, 5, '2024-01-19 15:15:00', '2024-01-19 15:15:00', 'https://images.unsplash.com/photo-1606902965551-dce093cda6e7', 'Basketball Official Size'),
    (TRUE, 45.99, 220, 6, '2024-01-20 15:30:00', '2024-01-20 15:30:00', 'https://images.unsplash.com/photo-1556228578-8c89e6adf883', 'Moisturizing Face Cream'),
    (TRUE, 29.99, 280, 6, '2024-01-20 15:45:00', '2024-01-20 15:45:00', 'https://images.unsplash.com/photo-1571875257727-256c39da42af', 'Shampoo and Conditioner Set'),
    (TRUE, 59.99, 160, 6, '2024-01-20 16:00:00', '2024-01-20 16:00:00', 'https://images.unsplash.com/photo-1596462502278-27bfdc403348', 'Perfume Eau de Parfum'),
    (TRUE, 19.99, 350, 6, '2024-01-20 16:15:00', '2024-01-20 16:15:00', 'https://images.unsplash.com/photo-1583241800698-6d0f6c562e96', 'Lipstick Matte Finish'),
    (TRUE, 79.99, 140, 6, '2024-01-20 16:30:00', '2024-01-20 16:30:00', 'https://images.unsplash.com/photo-1522335789203-aabd1fc54bc9', 'Skincare Routine Kit');


INSERT INTO categories (create_at, update_at, category_name, description)
VALUES
    ('2024-01-05 09:00:00', '2024-01-05 09:00:00', 'Electronics', 'Electronic devices and accessories including smartphones, laptops, and tablets'),
    ('2024-01-06 10:15:00', '2024-01-06 10:15:00', 'Clothing', 'Apparel and fashion items for men, women, and children'),
    ('2024-01-07 11:30:00', '2024-01-07 11:30:00', 'Home & Kitchen', 'Home appliances, kitchenware, and household essentials'),
    ('2024-01-08 14:20:00', '2024-01-08 14:20:00', 'Books', 'Physical and digital books across various genres and topics'),
    ('2024-01-09 08:45:00', '2024-01-09 08:45:00', 'Sports & Outdoors', 'Sports equipment, outdoor gear, and fitness accessories'),
    ('2024-01-10 13:10:00', '2024-01-10 13:10:00', 'Beauty & Personal Care', 'Cosmetics, skincare products, and personal hygiene items'),
    ('2024-01-11 15:30:00', '2024-01-11 15:30:00', 'Toys & Games', 'Toys, board games, puzzles, and entertainment for all ages'),
    ('2024-01-12 09:50:00', '2024-01-12 09:50:00', 'Automotive', 'Car parts, accessories, and maintenance products'),
    ('2024-01-13 12:25:00', '2024-01-13 12:25:00', 'Food & Beverages', 'Grocery items, snacks, drinks, and specialty food products'),
    ('2024-01-14 16:40:00', '2024-01-14 16:40:00', 'Furniture', 'Indoor and outdoor furniture for home and office use');