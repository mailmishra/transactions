CREATE TABLE IF NOT EXISTS product (
    product_code VARCHAR(255),
    cost NUMERIC,
    product_status VARCHAR(100),
    PRIMARY KEY (product_code)
);

 CREATE INDEX IF NOT EXISTS idx_product_status ON product(product_status);
