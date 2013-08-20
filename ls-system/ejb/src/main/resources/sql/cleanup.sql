-- alter table class_info drop foreign key FKEEAD9F15AE519245;
-- alter table class_student drop foreign key FK5A8EDA74736E3D13;
-- alter table class_student drop foreign key FK5A8EDA74A22F6C25;
-- alter table grade drop foreign key FK5E0BFD7A22F6C25;
-- alter table grade drop foreign key FK5E0BFD7DE3DC40F;
-- alter table parent drop foreign key FKC4AB08AA33076C7B;
-- alter table parent_student drop foreign key FK9F22E7A6A22F6C25;
-- alter table parent_student drop foreign key FK9F22E7A6939BCF;
-- alter table student drop foreign key FK8FFE823B33076C7B;
-- alter table student drop foreign key FK8FFE823B1DC50A5F;
-- alter table student drop foreign key FK8FFE823BAE519245;
-- alter table teacher drop foreign key FKAA31CBE233076C7B;
-- alter table teacher drop foreign key FKAA31CBE21DC50A5F;
-- alter table teacher_class drop foreign key FK6D6FE6DB736E3D13;
-- alter table teacher_class drop foreign key FK6D6FE6DBB2CAB6C5;
-- alter table test drop foreign key FK364492736E3D13;

SET foreign_key_checks = 0;

drop table if exists basic_user;
drop table if exists class_info;
drop table if exists class_student;
drop table if exists detailed_info;
drop table if exists grade;
drop table if exists level;
drop table if exists parent;
drop table if exists parent_student;
drop table if exists student;
drop table if exists teacher;
drop table if exists roles;
drop table if exists teacher_class;
drop table if exists test;
drop table if exists hibernate_sequence;

SET foreign_key_checks = 1;