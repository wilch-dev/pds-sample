--create table department(
--    id SERIAL NOT NULL,
--    name VARCHAR(255) NOT NULL,
--    assignedLocation VARCHAR(750) NOT NULL,
--    constraint department_pk primary key (id)
--    constraint name UNIQUE (name)
--);

--snake case for column names please?

CREATE TABLE department(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL UNIQUE,
    assigned_location VARCHAR(750) NOT NULL
);

SELECT * FROM department;