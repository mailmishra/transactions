CREATE TABLE IF NOT EXISTS customer (
    customer_id bigint,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255),
    location_country VARCHAR(100),
    PRIMARY KEY (customer_id)
);

