create table if not exists basic_user (
    id bigint not null auto_increment,
    active bit not null,
    email varchar(50) not null,
    first_name varchar(15) not null,
    last_name varchar(20) not null,
    login varchar(25) not null unique,
    password_ varchar(40) not null,
    phone_number varchar(12) not null,
    primary key (id),
    unique (email , login)
);

create table if not exists class_info (
    id bigint not null auto_increment,
    description varchar(255),
    when_ bigint,
    where_ varchar(255),
    level_id bigint not null,
    primary key (id)
);

create table if not exists class_student (
    student_id bigint not null,
    class_id bigint not null,
    primary key (student_id , class_id)
);

create table if not exists detailed_info (
    id bigint not null auto_increment,
    city varchar(40) not null,
    country varchar(40) not null,
    street varchar(40) not null,
    zip_code varchar(6) not null,
    date_joined bigint not null,
    date_of_birth bigint not null,
    pin varchar(255) unique,
    primary key (id),
    unique (pin)
);

create table if not exists grade (
    id bigint not null auto_increment,
    comment_ varchar(255),
    percentage tinyint not null,
    weight tinyint not null,
    student_id bigint not null,
    test_id bigint,
    primary key (id)
);

create table if not exists level (
    id bigint not null auto_increment,
    name_ varchar(255),
    primary key (id)
);

create table if not exists parent (
    id bigint not null,
    primary key (id)
);

create table if not exists parent_student (
    parent_id bigint not null,
    student_id bigint not null,
    primary key (parent_id , student_id),
    unique (student_id)
);

create table if not exists student (
    id bigint not null,
    info_id bigint not null unique,
    level_id bigint not null,
    primary key (id)
);

create table if not exists teacher (
    avatar longblob,
    id bigint not null,
    info_id bigint not null unique,
    primary key (id)
);

create table if not exists teacher_class (
    teacher_id bigint not null,
    class_id bigint not null,
    primary key (teacher_id , class_id)
);

create table if not exists test (
    id bigint not null auto_increment,
    description varchar(255),
    when_ bigint not null,
    class_id bigint,
    primary key (id)
);

create table if not exists roles (
    id bigint not null auto_increment,
    role varchar(45) not null,
    user_id bigint not null,
    primary key(id),
    index fk_user_role_user1 (user_id ASC),
    constraint fk_user_role_user1
    foreign key (user_id)
    references basic_user (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

alter table class_info add index FKEEAD9F15AE519245 (level_id), add constraint FKEEAD9F15AE519245 foreign key (level_id) references level (id);

alter table class_student add index FK5A8EDA74736E3D13 (class_id), add constraint FK5A8EDA74736E3D13 foreign key (class_id) references class_info (id);

alter table class_student add index FK5A8EDA74A22F6C25 (student_id), add constraint FK5A8EDA74A22F6C25 foreign key (student_id) references student (id);

alter table grade add index FK5E0BFD7A22F6C25 (student_id), add constraint FK5E0BFD7A22F6C25 foreign key (student_id) references student (id);

alter table grade add index FK5E0BFD7DE3DC40F (test_id);

alter table parent add index FKC4AB08AA33076C7B (id), add constraint FKC4AB08AA33076C7B foreign key (id) references basic_user (id);

alter table parent_student add index FK9F22E7A6A22F6C25 (student_id), add constraint FK9F22E7A6A22F6C25 foreign key (student_id) references student (id);

alter table parent_student add index FK9F22E7A6939BCF (parent_id), add constraint FK9F22E7A6939BCF foreign key (parent_id) references parent (id);

alter table student add index FK8FFE823B33076C7B (id), add constraint FK8FFE823B33076C7B foreign key (id) references basic_user (id);

alter table student add index FK8FFE823B1DC50A5F (info_id), add constraint FK8FFE823B1DC50A5F foreign key (info_id) references detailed_info (id);

alter table student add index FK8FFE823BAE519245 (level_id), add constraint FK8FFE823BAE519245 foreign key (level_id) references level (id);

alter table teacher add index FKAA31CBE233076C7B (id), add constraint FKAA31CBE233076C7B foreign key (id) references basic_user (id);

alter table teacher add index FKAA31CBE21DC50A5F (info_id), add constraint FKAA31CBE21DC50A5F foreign key (info_id) references detailed_info (id);

alter table teacher_class add index FK6D6FE6DB736E3D13 (class_id), add constraint FK6D6FE6DB736E3D13 foreign key (class_id) references class_info (id);

alter table teacher_class add index FK6D6FE6DBB2CAB6C5 (teacher_id), add constraint FK6D6FE6DBB2CAB6C5 foreign key (teacher_id) references teacher (id);

alter table test add index FK364492736E3D13 (class_id), add constraint FK364492736E3D13 foreign key (class_id) references class_info (id);

create table hibernate_sequence (
    next_val bigint
);
