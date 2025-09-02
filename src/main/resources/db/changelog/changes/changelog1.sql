
CREATE TABLE allusers
(
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    dob date
);

INSERT INTO allusers (first_name, last_name, dob)
VALUES ('Shahad', 'Alqasem', '2003-01-01');

