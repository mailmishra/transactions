CREATE TABLE IF NOT EXISTS customer_transaction (
    transaction_id SERIAL PRIMARY KEY NOT NULL,
    transaction_time TIMESTAMP,
    customer_id BIGINT,
    quantity BIGINT,
    product_code VARCHAR(255)
    -- Not adding any foreign keys here, to ensure performance while Inserting. 
);