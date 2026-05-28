CREATE DATABASE PlacementEliminationSystem;

USE PlacementEliminationSystem;

⸻
CREATE TABLE students(
id INT PRIMARY KEY,
name VARCHAR(50),
email VARCHAR(100),
coding INT,
aptitude INT,
communication INT,
cgpa DOUBLE,
projects INT,
certifications INT,
hackathons INT
);

INSERT INTO students VALUES
(101,‘Arjun’,‘arjun@gmail.com’,95,85,80,9.1,4,5,2),
(102,‘Rahul’,‘rahul@gmail.com’,80,75,70,8.0,3,2,1),
(103,‘Sneha’,‘sneha@gmail.com’,98,90,95,9.7,5,6,4),
(104,‘Varun’,‘varun@gmail.com’,60,70,65,7.0,2,1,0),
(105,‘Kiran’,‘kiran@gmail.com’,88,84,80,8.5,3,4,2),
(106,‘Ayesha’,‘ayesha@gmail.com’,72,76,78,7.6,2,2,1),
(107,‘Rohit’,‘rohit@gmail.com’,99,95,90,9.9,5,6,5),
(108,‘Deepika’,‘deepika@gmail.com’,55,60,62,6.8,1,1,0),
(109,‘Ajay’,‘ajay@gmail.com’,86,82,88,8.2,3,3,2),
(110,‘Pooja’,‘pooja@gmail.com’,78,80,76,7.8,2,3,1);


CREATE TABLE companies(
company_id INT PRIMARY KEY,
company_name VARCHAR(50),
min_ai_score INT,
package_lpa DOUBLE
);

INSERT INTO companies VALUES
(1,‘Google’,150,35),
(2,‘Amazon’,140,30),
(3,‘Microsoft’,145,32),
(4,‘Adobe’,135,24),
(5,‘Accenture’,110,8),
(6,‘IBM’,100,7),
(7,‘Capgemini’,95,6),
(8,‘Infosys’,90,5),
(9,‘TCS’,85,4),
(10,‘Wipro’,80,4);

CREATE TABLE placement_result(
student_id INT,
student_name VARCHAR(50),
email VARCHAR(100),
ai_score INT,
category VARCHAR(30),
company_type VARCHAR(100),
final_status VARCHAR(50)
);



