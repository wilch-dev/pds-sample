create table department(
    id SERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    assignedLocation VARCHAR(750) NOT NULL,
    constraint department_pk primary key (id)
    constraint name UNIQUE (name)
);
