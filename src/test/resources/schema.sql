DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  surname varchar(45) NOT NULL,
  address varchar(200) NOT NULL,
  email varchar(45) NOT NULL,
  phone_number varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  borrowed_books_number int(5) DEFAULT NULL,
  basket_id int(5) DEFAULT NULL,
  account_approved_by int(5) DEFAULT NULL,
  created_at datetime NOT NULL
);

DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  surname varchar(45) NOT NULL,
  address varchar(200) NOT NULL,
  email varchar(45) NOT NULL,
  phone_number varchar(45) NOT NULL,
  password varchar(45) NOT NULL
);

DROP TABLE IF EXISTS books;
CREATE TABLE books (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  description text DEFAULT NULL,
  image varchar(45) DEFAULT NULL,
  status_id int(5) NOT NULL,
  add_by int(5) NOT NULL,
  created_at datetime NOT NULL,
  basket_id int(11) DEFAULT NULL
);

DROP TABLE IF EXISTS authors;
CREATE TABLE authors (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  surname varchar(45) NOT NULL,
  description text DEFAULT NULL,
  image varchar(45) DEFAULT NULL,
  created_at datetime NOT NULL,
  add_by int(5) NOT NULL
);