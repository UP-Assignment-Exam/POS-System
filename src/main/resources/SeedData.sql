USE
pos_system;

INSERT INTO roles (name)
VALUES ('admin'),
       ("user");

INSERT INTO users (first_name, last_name, email, phone_number, password, role_id)
VALUES ('John', 'Doe', 'admin@gmail.com', '555-123-4567',
        '$2a$10$cj0K8OkB.eQ8SEFsok7BMOfC6v3kpgd5nPETGiVZi8T8VqBo4CWuW', 1),
       ('Jane', 'Smith', 'user@gmail.com', '555-234-5678',
        '$2a$10$cj0K8OkB.eQ8SEFsok7BMOfC6v3kpgd5nPETGiVZi8T8VqBo4CWuW', 2),
       ('Alice', 'Johnson', 'alice@gmail.com', '555-345-6789',
        '$2a$10$cj0K8OkB.eQ8SEFsok7BMOfC6v3kpgd5nPETGiVZi8T8VqBo4CWuW', 2),
       ('Bob', 'Brown', 'bob@gmail.com', '555-456-7890', '$2a$10$cj0K8OkB.eQ8SEFsok7BMOfC6v3kpgd5nPETGiVZi8T8VqBo4CWuW',
        2),
       ('Charlie', 'Davis', 'chare@gmail.com', '555-567-8901',
        '$2a$10$cj0K8OkB.eQ8SEFsok7BMOfC6v3kpgd5nPETGiVZi8T8VqBo4CWuW', 2),
       ('Alice', 'Johnson', 'ale@gmail.com', '555-345-6789',
        '$2a$10$cj0K8OkB.eQ8SEFsok7BMOfC6v3kpgd5nPETGiVZi8T8VqBo4CWuW', 2),
       ('Bob', 'Brown', 'bobsa@gmail.com', '555-456-7890',
        '$2a$10$cj0K8OkB.eQ8SEFsok7BMOfC6v3kpgd5nPETGiVZi8T8VqBo4CWuW', 2),
       ('Charlie', 'Davis', 'chawrlie@gmail.com', '555-567-8901',
        '$2a$10$cj0K8OkB.eQ8SEFsok7BMOfC6v3kpgd5nPETGiVZi8T8VqBo4CWuW', 2),
       ('Alice', 'Johnson', 'alqice@gmail.com', '555-345-6789',
        '$2a$10$cj0K8OkB.eQ8SEFsok7BMOfC6v3kpgd5nPETGiVZi8T8VqBo4CWuW', 2),
       ('Bob', 'Brown', 'boba@gmail.com', '555-456-7890',
        '$2a$10$cj0K8OkB.eQ8SEFsok7BMOfC6v3kpgd5nPETGiVZi8T8VqBo4CWuW', 2),
       ('Charlie', 'Davis', 'charclie@gmail.com', '555-567-8901',
        '$2a$10$cj0K8OkB.eQ8SEFsok7BMOfC6v3kpgd5nPETGiVZi8T8VqBo4CWuW', 2);

INSERT INTO categories (name, image, type)
VALUES ('Clothing', 'https://images.unsplash.com/photo-1503342394128-c104d54dba01?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Women'),
       ('Clothing', 'https://images.unsplash.com/photo-1564859228273-274232fdb516?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Men'),
       ('Clothing', 'https://plus.unsplash.com/premium_photo-1697612943610-fc079bab10cf?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Kid'),
       ('Accessories', 'https://plus.unsplash.com/premium_photo-1697612943610-fc079bab10cf?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Others'),
       ('Shoes', 'https://images.unsplash.com/photo-1503342394128-c104d54dba01?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Women'),
       ('Formal Wear', 'https://images.unsplash.com/photo-1564859228273-274232fdb516?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Men'),
       ('Baby Clothing', 'https://plus.unsplash.com/premium_photo-1697612943610-fc079bab10cf?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Kid'),
       ('Handbags', 'https://images.unsplash.com/photo-1503342217505-b0a15ec3261c?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Women'),
       ('Activewear', 'https://images.unsplash.com/photo-1564859228273-274232fdb516?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Men'),
       ('Toys', 'https://plus.unsplash.com/premium_photo-1697612943610-fc079bab10cf?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'Kid');

INSERT INTO products (name, description, price, discount, quantity, category_id)
VALUES ('Casual Dress', 'A stylish casual dress for women.', 49.99, 10.00, 100, 1),
       ('Formal Shirt', 'A formal shirt suitable for office wear.', 39.99, 5.00, 150, 2),
       ('Sneakers', 'Comfortable sneakers for daily wear.', 59.99, 15.00, 200, 3),
       ('Kids T-Shirt', 'A fun T-shirt for kids.', 19.99, 0.00, 250, 4),
       ('Leather Jacket', 'A classic leather jacket for men.', 89.99, 20.00, 50, 5),
       ('Denim Jeans', 'Stylish denim jeans for men.', 49.99, 10.00, 75, 2),
       ('Summer Hat', 'A cool hat for summer days.', 19.99, 0.00, 120, 1),
       ('Sporty Backpack', 'A stylish backpack for school or outings.', 39.99, 5.00, 80, 4),
       ('Wool Sweater', 'Warm wool sweater for cold weather.', 69.99, 15.00, 60, 2),
       ('Running Shoes', 'Lightweight shoes for running.', 79.99, 10.00, 40, 3);

INSERT INTO product_sizes (product_id, sizes)
VALUES (1, "S"),
       (1, 'M'),
       (1, 'L'),
       (2, 'M'),
       (2, 'L'),
       (2, 'XL'),
       (3, 'M'),
       (3, 'L'),
       (3, 'XL'),
       (4, 'S'),
       (4, 'M'),
       (5, 'M'),
       (5, 'L'),
       (6, 'M'),
       (6, 'L'),
       (7, 'M'),
       (7, 'L'),
       (8, 'M'),
       (8, 'L'),
       (9, 'S'),
       (9, 'M'),
       (10, 'S'),
       (10, 'M');

INSERT INTO product_colors (product_id, colors)
VALUES (1, 'Red'),
       (1, 'Blue'),
       (1, 'Green'),
       (2, 'White'),
       (2, 'Black'),
       (3, 'Black'),
       (3, 'White'),
       (4, 'Blue'),
       (4, 'Yellow'),
       (5, 'Black'),
       (6, 'Dark Blue'),
       (6, 'Light Blue'),
       (7, 'Beige'),
       (7, 'Black'),
       (8, 'Blue'),
       (8, 'Red'),
       (9, 'Gray'),
       (9, 'Navy'),
       (10, 'Black'),
       (10, 'Red');

INSERT INTO product_images (product_id, images)
VALUES (1, 'https://i5.walmartimages.com/seo/Womens-Casual-Fashion-Dress-Floral-Printed-Bohemian-V-Neck-Elegant-Dress-A-Line-Short-Sleeve-Casual-Dress_5234691a-bbaa-4e81-8e79-e32f3e0c2886.bdbd6e5f0db09c2a04ccbf51c975ca02.jpeg?odnHeight=2000&odnWidth=2000&odnBg=FFFFFF'),
       (2, 'https://i.ebayimg.com/images/g/hj4AAOSwZq5bvxTY/s-l1200.jpg'),
       (3, 'https://cms-cdn.thesolesupplier.co.uk/2021/08/air-jordan-1-high-via-afewstore.jpeg.webp'),
       (4, 'https://assets.ajio.com/medias/sys_master/root/20240704/6PeF/668731cc1d763220fae9478e/-473Wx593H-700162578-white-MODEL.jpg'),
       (5, 'https://leatherskinshop.com/cdn/shop/articles/how-should-a-leather-jacket-fit-mens_4ab0478c-51be-483e-ae03-d2b9150c14a7.png?v=1719189267'),
       (6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSi3NtLEQemcWi6CWPWT-En1aTDRw5PxpSYTw&s'),
       (7, 'https://media.sketchfab.com/models/0081f8f6bc794f47b0b1ade9aade70db/thumbnails/1f7255c4e6894892a2f727d5b04b58cc/68cb29fcb5b844e0851e12b3c2ff564f.jpeg'),
       (8, 'https://eastsport.com/cdn/shop/files/111010-JS5_1.jpg?v=1722371115'),
       (9, 'https://cdn11.bigcommerce.com/s-j3t79x/images/stencil/1280x1280/products/854/3218/C1347@Mlead__00972__76376.1518007484.jpg?c=2'),
       (10, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkIAP0MHnQywt5qc60GS-u_svGrjQea2aAuw&s');

INSERT INTO review_product (product_id, user_id, comment, star)
VALUES (1, 1, 'Great product!', 5),
       (1, 2, 'Average quality.', 3),
       (1, 3, 'Not worth the price.', 2),
       (1, 4, 'Excellent value for money.', 4),
       (1, 5, 'Will buy again.', 5),
       (2, 1, 'Great fit!', 4),
       (2, 2, 'Really good quality.', 5),
       (2, 3, 'Not what I expected.', 2),
       (2, 4, 'Very comfortable.', 5),
       (2, 5, 'Okay.', 3),
       (3, 1, 'Comfortable and stylish.', 5),
       (3, 2, 'Too small.', 2),
       (3, 3, 'Great for workouts.', 4),
       (3, 4, 'Not worth the hype.', 2),
       (3, 5, 'Perfect for running.', 5),
       (4, 1, 'Great for kids.', 5),
       (4, 2, 'Very colorful.', 4),
       (4, 3, 'Not a good fit.', 2),
       (4, 4, 'Kids loved it!', 5),
       (4, 5, 'Cute design.', 4),
       (5, 1, 'Amazing jacket!', 5),
       (5, 2, 'Too tight.', 2),
       (5, 3, 'Nice quality.', 4),
       (5, 4, 'Good for winter.', 5),
       (5, 5, 'Not worth the money.', 2);

INSERT INTO vouchers (name, discount, description, image, expired_date, start_date, status)
VALUES ('Summer Sale', 20.0, '20% off on all summer items.', 'https://i.pinimg.com/564x/d8/7a/16/d87a16cc7badf14ff80c02052f305f8c.jpg',
        DATE_ADD(CURDATE(), INTERVAL 1 MONTH), CURDATE(), TRUE),
       ('Winter Sale', 25.0, '25% off on selected winter items.', 'https://i.pinimg.com/564x/d8/7a/16/d87a16cc7badf14ff80c02052f305f8c.jpg',
        DATE_ADD(CURDATE(), INTERVAL 1 MONTH), CURDATE(), TRUE),
       ('Spring Discount', 15.0, '15% off on spring collection.', 'https://i.pinimg.com/564x/d8/7a/16/d87a16cc7badf14ff80c02052f305f8c.jpg',
        DATE_ADD(CURDATE(), INTERVAL 1 MONTH), CURDATE(), TRUE),
       ('Fall Savings', 30.0, '30% off on all fall apparel.', 'https://i.pinimg.com/564x/d8/7a/16/d87a16cc7badf14ff80c02052f305f8c.jpg',
        DATE_ADD(CURDATE(), INTERVAL 1 MONTH), CURDATE(), TRUE),
       ('Holiday Promo', 10.0, '10% off on holiday gifts.', 'https://i.pinimg.com/564x/d8/7a/16/d87a16cc7badf14ff80c02052f305f8c.jpg',
        DATE_ADD(CURDATE(), INTERVAL 1 MONTH), CURDATE(), TRUE);

INSERT INTO invoices (user_id, total_price, total_item, voucher_id)
VALUES (1, 250.00, 3, NULL),
       (2, 300.50, 2, NULL),
       (3, 150.75, 1, NULL),
       (4, 400.20, 4, NULL),
       (5, 275.30, 2, NULL);
INSERT INTO invoice_product (invoice_id, product_id, quantity, color, size)
VALUES (1, 1, 1, 'Red', 'M'),
       (1, 2, 2, 'Blue', 'L'),
       (1, 3, 1, 'Black', 'S'),
       (2, 4, 1, 'Green', 'M'),
       (2, 5, 1, 'White', 'L'),
       (3, 6, 1, 'Yellow', 'M'),
       (4, 7, 2, 'Gray', 'L'),
       (4, 8, 1, 'Pink', 'M'),
       (4, 9, 1, 'Purple', 'S'),
       (4, 10, 1, 'Orange', 'XL'),
       (5, 2, 2, 'Brown', 'M'),
       (5, 1, 1, 'Navy', 'L');


UPDATE products p
    JOIN (
    SELECT product_id, AVG(star) as average_rating
    FROM review_product
    GROUP BY product_id
    ) rp ON p.id = rp.product_id
    SET p.rating = rp.average_rating;

ALTER TABLE categories CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE products CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE users CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;