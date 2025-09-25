--liquibase formatted sql

--changeset Shahad:7

UPDATE roles
SET role=LOWER(role)