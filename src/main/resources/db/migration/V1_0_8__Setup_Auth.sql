INSERT INTO USERS (u_name, username, user_password, email) VALUES ('Admin', 'admin', '$2a$12$D59n.HzUBdFdIyWhJRVPXukm2vg4wM87jLD4PQMNbiAQ57/XG/2tu', 'admin@transactions.com');

INSERT INTO ROLES (role_name) VALUES ('transactions.admin');

INSERT INTO USERS_ROLES (user_id,role_id) VALUES (1,1);