DROP DATABASE IF EXISTS `Library`;
CREATE DATABASE IF NOT EXISTS `Library`;
SHOW DATABASES;
USE `Library`;

DROP TABLE IF EXISTS member;
CREATE TABLE member(
	date DATE,
	member_id VARCHAR(10),
	name VARCHAR(45),
	address TEXT,
    sex VARCHAR(10),
    age VARCHAR(5),
	nic VARCHAR(15),
    email VARCHAR(45),
	contact VARCHAR(15)NOT NULL DEFAULT'no-contact',
	CONSTRAINT PRIMARY KEY(member_id)	
);
SHOW TABLES;
DESCRIBE member;

DROP TABLE IF EXISTS category;
CREATE TABLE category(
	category_name VARCHAR(15),
	category_id VARCHAR(15),
	CONSTRAINT PRIMARY KEY(category_name)
);
SHOW TABLES;
DESCRIBE category;

DROP TABLE IF EXISTS book;
CREATE TABLE book(
	book_id VARCHAR(15),
	book_name VARCHAR(25),
    title VARCHAR(25),
	author_name VARCHAR(45),
	book_language VARCHAR(15),
    relesed_date VARCHAR(15),
	category_name VARCHAR(15),
    donate_id VARCHAR(15),
    add_type VARCHAR(15),
	status VARCHAR(15),
	CONSTRAINT PRIMARY KEY(book_id),
	CONSTRAINT FOREIGN KEY(category_name)REFERENCES category(category_name)ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(donate_id)REFERENCES donate(donate_id)ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE book;

DROP TABLE IF EXISTS donate;
CREATE TABLE donate(
	donate_id VARCHAR(15),
	person_name VARCHAR(45),
	person_address TEXT,
    person_mobile VARCHAR(15),
    date VARCHAR(15),
    person_nic VARCHAR(25),
    email VARCHAR(35),
	CONSTRAINT PRIMARY KEY(donate_id)
);
SHOW TABLES;
DESCRIBE donate;

DROP TABLE IF EXISTS barrow;
CREATE TABLE barrow(
	barrow_id VARCHAR(15),
	member_id VARCHAR(15),
	barrow_date DATE,
	`return_date` DATE,
    member_name VARCHAR(45),
    member_nic VARCHAR(25),
    member_contact VARCHAR(15),
    member_email VARCHAR(50),
	CONSTRAINT PRIMARY KEY(barrow_id),
	CONSTRAINT FOREIGN KEY(member_id)REFERENCES member(member_id)ON DELETE CASCADE ON UPDATE CASCADE	
);
SHOW TABLES;
DESCRIBE barrow;

 DROP TABLE IF EXISTS `user`;
 CREATE TABLE `user`(
 user_id VARCHAR(10),
 user_name VARCHAR(45)NOT NULL DEFAULT 'Unknown',    
 user_address TEXT,   
 mobile INT(15),
 user_role VARCHAR(10),   
 user_password VARCHAR(50)NOT NULL, 
 nic VARCHAR(25),
 CONSTRAINT PRIMARY KEY(user_id)
 );
 SHOW TABLES;
 DESCRIBE user;
 
  DROP TABLE IF EXISTS barrow_Detail;
  CREATE TABLE barrow_Detail(
  barrow_id VARCHAR(25),
  book_id VARCHAR(25),
  member_id VARCHAR(25),
  total_date INT(20),
  book_name VARCHAR(40),
  status VARCHAR(15),
  CONSTRAINT PRIMARY KEY ( barrow_id,book_id,member_id),
  CONSTRAINT FOREIGN KEY (barrow_id) REFERENCES barrow (barrow_id)ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (book_id) REFERENCES book (book_id)ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY ( member_id) REFERENCES member (member_id)ON DELETE CASCADE ON UPDATE CASCADE
  );
  SHOW TABLES;
  DESCRIBE barrow_Detail;
  