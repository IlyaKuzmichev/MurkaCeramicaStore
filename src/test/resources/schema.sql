CREATE TABLE products
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255)  NOT NULL DEFAULT 'product',
    price    NUMERIC(8, 2) NOT NULL DEFAULT 0,
    quantity INTEGER       NOT NULL DEFAULT 0
);