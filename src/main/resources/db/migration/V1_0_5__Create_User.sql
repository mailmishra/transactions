CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY NOT NULL,
    u_name VARCHAR(100),
    username VARCHAR(100),
    user_password VARCHAR(255),
    email VARCHAR(255)
);
