insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active) values(1, 'some@email.com', 'name', 'last_name', 'login', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '111222333', true);

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active) values(2, 'someother@email.com', 'other_name', 'other_last_name', 'other_login', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '222222333', true);

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active) values(3, 'some12@email.com', 'nameParent', 'last_nameParent', 'loginParent', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '123123123', true);

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active) values(4, 'lalal@op.pl', 'name2', 'last_name2', 'default_login2', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '123123623', true);

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active) values(5, 'awlasiu@wp.pl', 'Ashlee', 'Gayle', 'lethal_ashlee', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '607888555', true);

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active) values(6, 'jeff@bigdaddy.com', 'Jeff', 'Masztalerz', 'haveplate', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '534678233', true);

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active) values(7, 'michau@yahoo.com', 'Michau', 'Will', 'gniewosz', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '4723334', true);

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active) values(8, 'random_guy@gmail.pl', 'Random', 'Guy', '4354333', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '811812821', true);

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(1, 'Virginia Rd', '13733', 'Oxford', 'USA', '345345345', '345345345', '123123123');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(2, 'Lewis St', '13733', 'Bainbridge', 'USA', '345345345', '345345345', '3456546DC');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(3, 'ObiOne Av', '23222', 'Tatooine', 'The Republic', '345345345', '345345345', 'r2d2c3po');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(4, 'The street', '00-100', 'Quauhog', 'USA', '345345345', '345345345', '1231231hf');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(5, 'Maple 5 Av', '221357', 'Triangle', 'India', '345345345', '345345345', 'kjkndfg56');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(6, 'Goofrey Rd', '77007', 'Sidney', 'Australia', '345345345', '345345345', '333444555y');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin) values(7, 'Emancypantek', '21-111', 'Ljubliana', 'Slovenia', '345345345', '345345345', 'ugabuga2');

insert into level(id, name_) values(1, "advanced");

insert into level(id, name_) values(2, "pre-intermediate");

insert into level(id, name_) values(3, "elementary");

insert into class_info(id, description, when_, where_, level_id) values(1, "this is a description", "45756757", "room 103", "1");

insert into class_info(id, description, when_, where_, level_id) values(2, "Ashlee smart class", "45756757", "room 666", "2");

insert into class_info(id, description, when_, where_, level_id) values(3, "Jeff is drawing foxes", "45756757", "under the stairs with harry", "3");

insert into test(id, description, when_, class_id) values(1, "this is a test", "456546456", "1");

insert into test(id, description, when_, class_id) values(2, "triangle fox", "456546456", "3");

insert into test(id, description, when_, class_id) values(3, "nuclear test", "456546456", "2");

insert into test(id, description, when_, class_id) values(4, "GALILEO", "456546456", "1");

insert into student(id, level_id, info_id) values("1", "1", "1");

insert into student(id, level_id, info_id) values("4", "3", "4");

insert into student(id, level_id, info_id) values("5", "2", "5");

insert into student(id, level_id, info_id) values("6", "1", "7");

insert into student(id, level_id, info_id) values("7", "3", "3");

insert into teacher(id, info_id, avatar) values ("2", "2", null);

insert into teacher(id, info_id, avatar) values ("8", "6", null);

insert into parent(id) values("3");

insert into grade(id, comment_, percentage, weight, student_id, test_id) values(1, "this is a comment", "76", "3", "1", "1");

insert into grade(id, comment_, percentage, weight, student_id, test_id) values(2, "you suck at drawing eyes of foxes", "74", "10", "5", "2");

insert into grade(id, comment_, percentage, weight, student_id, test_id) values(3, "you good", "89", "10", "4", "2");

insert into grade(id, comment_, percentage, weight, student_id, test_id) values(4, "nice one harry", "94", "1", "4", "3");

insert into grade(id, comment_, percentage, weight, student_id, test_id) values(5, "lasldlalal", "33", "3", "7", "4");

insert into grade(id, comment_, percentage, weight, student_id, test_id) values(6, "recourse kasia, recourse", "50", "7", "5", "3");

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
