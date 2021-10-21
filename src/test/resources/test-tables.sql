DROP TABLE IF EXISTS tbl_employees, tbl_departments, tbl_roles;

CREATE TABLE IF NOT EXISTS tbl_roles (
rl_id serial PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
rl_name VARCHAR (255) UNIQUE
);

CREATE TABLE IF NOT EXISTS tbl_departments(
dp_id serial PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
dp_name VARCHAR (255) UNIQUE
);

CREATE TABLE IF NOT EXISTS tbl_employees (
emp_id serial PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
emp_name VARCHAR (255) UNIQUE,
emp_active BOOLEAN ,
emp_password VARCHAR (255),
dp_id INT references tbl_departments(dp_id),
rl_id INT references tbl_roles(rl_id)
);

