CREATE TABLE users_roles
(
    user_id SERIAL NOT NULL REFERENCES all_users(user_id),
    role_id SERIAL NOT NULL REFERENCES roles(role_id) ,
    PRIMARY KEY (user_id,role_id)

);