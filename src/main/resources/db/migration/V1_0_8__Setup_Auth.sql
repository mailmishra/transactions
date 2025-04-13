INSERT INTO USERS (u_name, username, user_password, email) VALUES ('Admin', 'admin', '$2a$12$D59n.HzUBdFdIyWhJRVPXukm2vg4wM87jLD4PQMNbiAQ57/XG/2tu', 'admin@transactions.com');
INSERT INTO USERS (u_name, username, user_password, email) VALUES ('Reports', 'report', '$2a$12$D59n.HzUBdFdIyWhJRVPXukm2vg4wM87jLD4PQMNbiAQ57/XG/2tu', 'reports@transactions.com');

INSERT INTO ROLES (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLES (role_name) VALUES ('ROLE_REPORTS');

INSERT INTO USERS_ROLES (user_id,role_id) VALUES (1,1);
INSERT INTO USERS_ROLES (user_id,role_id) VALUES (1,2);
INSERT INTO USERS_ROLES (user_id,role_id) VALUES (2,2);