CREATE DATABASE db_department_dev;

/c db_department_dev

CREATE TABLE IF NOT EXISTS department(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL UNIQUE,
    assigned_location VARCHAR(750) NOT NULL
);

INSERT INTO department (id,name, assigned_location) VALUES (1,'IT','16F'),(2,'Marketing','7F'),(3,'Meals','Bridge');

CREATE DATABASE db_department_test;

/c db_department_test

CREATE TABLE IF NOT EXISTS db_department_test.department(
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255) NOT NULL UNIQUE,
    assigned_location VARCHAR(750) NOT NULL
);

INSERT INTO department (id,name, assigned_location) VALUES (1,'IT','16F'),(2,'Marketing','7F'),(3,'Meals','Bridge');