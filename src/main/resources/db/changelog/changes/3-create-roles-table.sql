--liquibase formatted sql

--changeset Shahad:3

CREATE TABLE roles
(
    role_id SERIAL PRIMARY KEY,
    role VARCHAR(255)
);