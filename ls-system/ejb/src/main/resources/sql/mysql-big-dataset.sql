insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(1, 'some@email.com', 'name', 'last_name', 'login', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '111222333', true, "STUDENT");

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(2, 'someother@email.com', 'other_name', 'other_last_name', 'other_login', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '222222333', true, "TEACHER");

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(3, 'some12@email.com', 'nameParent', 'last_nameParent', 'loginParent', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '123123123', true, "PARENT");

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(4, 'lalal@op.pl', 'name2', 'last_name2', 'default_login2', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '123123623', true, "STUDENT");

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(5, 'awlasiu@wp.pl', 'Ashlee', 'Gayle', 'lethal_ashlee', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '607888555', true, "STUDENT");

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(6, 'jeff@bigdaddy.com', 'Jeff', 'Masztalerz', 'haveplate', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '534678233', true, "STUDENT");

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(7, 'michau@yahoo.com', 'Michau', 'Will', 'gniewosz', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '4723334', true, "STUDENT");

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(8, 'random_guy@gmail.pl', 'Random', 'Guy', '4354333', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '811812821', true, "TEACHER");

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(1, 'Virginia Rd', '13733', 'Oxford', 'USA', '2011-01-15', '1992-11-03', '123123123');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(2, 'Lewis St', '13733', 'Bainbridge', 'USA', '2010-05-22', '1990-10-26', '3456546DC');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(3, 'ObiOne Av', '23222', 'Tatooine', 'The Republic', '2012-12-12', '1963-03-01', 'r2d2c3po');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(4, 'The street', '00-100', 'Quauhog', 'USA', '2009-09-10', '1995-05-05', '1231231hf');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(5, 'Maple 5 Av', '221357', 'Triangle', 'India', '2011-02-14', '1991-10-11', 'kjkndfg56');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(6, 'Goofrey Rd', '77007', 'Sidney', 'Australia', '2012-10-10', '1988-03-08', '333444555y');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(7, 'Emancypantek', '21-111', 'Ljubliana', 'Slovenia', '2013-01-01', '1978-06-18', 'ugabuga2');

insert into level(id, name_) values(1, "advanced");

insert into level(id, name_) values(2, "pre-intermediate");

insert into level(id, name_) values(3, "elementary");

insert into semester(id, name, begin_date, end_date) values(1, "first semester of 2011/2012", "2011-10-01", "2012-01-29");

insert into semester(id, name, begin_date, end_date) values(2, "second semester of 2011/2012", "2012-02-15", "2012-06-30");

insert into class_info(id, description, time, day, where_, level_id, semester_id) values(1, "this is a description", "15:30:00", "FRIDAY", "room 103", "1", "1");

insert into class_info(id, description, time, day, where_, level_id, semester_id) values(2, "Ashlee smart class", "08:00:00", "MONDAY", "room 666", "2", "2");

insert into class_info(id, description, time, day, where_, level_id, semester_id) values(3, "Jeff is drawing foxes", "10:15:00", "SATURDAY", "under the stairs with harry", "3", "1");

insert into student_task(id, description, when_, class_id, type) values(1, "this is a test", "2012-02-27 11:30:00", "1", "ASSIGNMENT");

insert into student_task(id, description, when_, class_id, type) values(2, "triangle fox", "2011-11-07 08:00:00", "3", "TEST");

insert into student_task(id, description, when_, class_id, type) values(3, "nuclear test", "2013-01-03 07:00:00", "2", "TEST");

insert into student_task(id, description, when_, class_id, type) values(4, "GALILEO", "2013-03-01 16:15:00", "1", "ASSIGNMENT");

insert into student(id, level_id, info_id) values("1", "1", "1");

insert into student(id, level_id, info_id) values("4", "3", "4");

insert into student(id, level_id, info_id) values("5", "2", "5");

insert into student(id, level_id, info_id) values("6", "1", "7");

insert into student(id, level_id, info_id) values("7", "3", "3");

insert into teacher(id, info_id, avatar) values ("2", "2", null);

insert into teacher(id, info_id, avatar) values ("8", "6", null);

insert into parent(id) values("3");

insert into grade(id, comment_, percentage, weight, student_id, student_task_id) values(1, "this is a comment", "76", "3", "1", "1");

insert into grade(id, comment_, percentage, weight, student_id, student_task_id) values(2, "you suck at drawing eyes of foxes", "74", "10", "5", "2");

insert into grade(id, comment_, percentage, weight, student_id, student_task_id) values(3, "you good", "89", "10", "4", "2");

insert into grade(id, comment_, percentage, weight, student_id, student_task_id) values(4, "nice one harry", "94", "1", "4", "3");

insert into grade(id, comment_, percentage, weight, student_id, student_task_id) values(5, "lasldlalal", "33", "3", "7", "4");

insert into grade(id, comment_, percentage, weight, student_id, student_task_id) values(6, "recourse kasia, recourse", "50", "7", "5", "3");

insert into class_student(student_id, class_id) values("1", "1");

insert into class_student(student_id, class_id) values("4", "2");

insert into class_student(student_id, class_id) values("5", "3");

insert into class_student(student_id, class_id) values("6", "2");

insert into class_student(student_id, class_id) values("7", "1");

insert into teacher_class(teacher_id, class_id) values("2", "1");

insert into teacher_class(teacher_id, class_id) values("8", "2");

insert into teacher_class(teacher_id, class_id) values("8", "3");

insert into teacher_class(teacher_id, class_id) values("8", "1");

insert into parent_student(parent_id, student_id) values("3", "1");

insert into parent_student(parent_id, student_id) values("3", "6");

insert into parent_student(parent_id, student_id) values("3", "7");

insert into roles(id, role, user_id) values("1", "student", "1");

insert into roles(id, role, user_id) values("2", "teacher", "2");

insert into roles(id, role, user_id) values("3", "parent", "3");

insert into roles(id, role, user_id) values("4", "student", "4");

insert into roles(id, role, user_id) values("5", "student", "5");

insert into roles(id, role, user_id) values("6", "student", "6");

insert into roles(id, role, user_id) values("7", "student", "7");

insert into roles(id, role, user_id) values("8", "teacher", "8");