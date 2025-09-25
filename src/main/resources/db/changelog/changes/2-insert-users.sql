--liquibase formatted sql

--changeset Shahad:2

INSERT INTO all_users (first_name, last_name, dob)
VALUES
    ('Alya', 'Khalid', '2000-05-15'),
    ('Rawan', 'Alharbi', '2002-12-20'),
    ('Nada', 'Saad', '2002-03-01');