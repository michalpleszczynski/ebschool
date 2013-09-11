insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(1, 'some@email.com', 'name', 'last_name', 'login', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '111222333', true, "STUDENT");

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(2, 'someother@email.com', 'other_name', 'other_last_name', 'other_login', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '222222333', true, "TEACHER");

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(3, 'some12@email.com', 'nameParent', 'last_nameParent', 'loginParent', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '123123123', true, "PARENT");

insert into basic_user(id, email, first_name, last_name, login, password_, phone_number, active, type) values(4, 'lalal@op.pl', 'name2', 'last_name2', 'default_login2', '/xK72MkHrwZwcCEdh73wmL4XN1s=', '123123623', true, "PARENT");

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin, type) values(1, 'Virginia Rd', '13733', 'Oxford', 'USA', '345345345', '345345345', '123123123');

insert into detailed_info(id, street, zip_code, city, country, date_joined, date_of_birth, pin, type) values(2, 'Lewis St', '13733', 'Bainbridge', 'USA', '345345345', '345345345', '3456546DC');

insert into level(id, name_) values(1, 'advanced');

insert into class_info(id, description, when_, where_, level_id) values(1, 'this is a description', '45756757', 'room 103', '1');

insert into test(id, description, when_, class_id) values(1, 'this is a test', '456546456', '1');

insert into student(id, level_id, info_id) values('1', '1', '1');

insert into teacher(id, info_id, avatar) values ('2', '2', null);

insert into parent(id) values('3');

insert into parent(id) values('4');

insert into grade(id, comment_, percentage, weight, student_id, test_id) values(1, 'this is a comment', '76', '3', '1', '1');

insert into class_student(student_id, class_id) values('1', '1');

insert into teacher_class(teacher_id, class_id) values('2', '1');

insert into parent_student(parent_id, student_id) values('3', '1');