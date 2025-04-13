CREATE TABLE IF NOT EXISTS users_roles (
    user_id INTEGER,
    role_id INTEGER,
    CONSTRAINT fk_users FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_roles FOREIGN KEY(role_id) REFERENCES roles(id)
);