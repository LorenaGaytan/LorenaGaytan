-- ADD STUDENTS TO DATA BASE
INSERT INTO students(name, email, age, gender) VALUES('jesica', 'jesica@gmail.com', 25, 'f');
INSERT INTO students(name, email, age, gender) VALUES('joaquin', 'joaquin@gmail.com', 25, 'm');
INSERT INTO students(name, email, age, gender) VALUES('nath', 'nath@gmail.com', 24, 'f');

-- CONSULTAS Y FILTROS
SELECT * FROM students;
SELECT * FROM students WHERE email = 'nath@gmail.com';
SELECT * FROM students WHERE age = 25 and name = 'nath';
SELECT name, email, gender FROM students;

-- UPDATE ROW
UPDATE students SET age = 25, name = 'nath' WHERE id = 1;

-- DELETE ROW
DELETE FROM students WHERE id = 2;

-- GET MYSQL VERSION
select version();

-- ADD COURSES TO DATA BASE
INSERT INTO courses(name) VALUES("Java desde Cero");
INSERT INTO courses(name) VALUES("Java Frameworks");
INSERT INTO courses(name) VALUES("Spring Framework");
INSERT INTO courses(name) VALUES("React desde Cero");
INSERT INTO courses(name) VALUES("Spring Boot - REST API");

-- REGISTRY STUDENT TO A COURSE
INSERT INTO inscriptions(student, course) VALUES(1, 2);

-- GET INFORMATION FROM TO TABLES (JOIN)
SELECT i.id, i.student, s.name, c.name FROM inscriptions i
JOIN courses c ON i.course = c.id 
JOIN students s ON i.student = s.id
WHERE i.student = 1;