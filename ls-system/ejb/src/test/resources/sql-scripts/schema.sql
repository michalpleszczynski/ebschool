
    create table basic_user (
        id bigint not null auto_increment,
        active boolean not null,
        email varchar(50) not null,
        first_name varchar(15) not null,
        last_name varchar(20) not null,
        login varchar(25) not null,
        password_ varchar(32) not null,
        phone_number varchar(12) not null,
        type varchar(255) not null,
        primary key (id)
    );

    create table class_info (
        id bigint not null auto_increment,
        description varchar(255),
        day varchar(255) not null,
        time time not null,
        where_ varchar(255),
        level_id bigint not null,
        semester_id bigint not null,
        primary key (id)
    );

    create table class_student (
        student_id bigint not null,
        class_id bigint not null,
        primary key (class_id, student_id)
    );

    create table detailed_info (
        id bigint not null auto_increment,
        city varchar(40) not null,
        country varchar(40) not null,
        street varchar(40) not null,
        zip_code varchar(6) not null,
        date_joined date not null,
        date_of_birth date not null,
        pin varchar(255),
        primary key (id)
    );

    create table grade (
        id bigint not null auto_increment,
        comment_ varchar(255),
        percentage tinyint not null,
        weight tinyint not null,
        student_id bigint not null,
        student_task_id bigint,
        primary key (id)
    );

    create table level (
        id bigint not null auto_increment,
        name_ varchar(255),
        primary key (id)
    );

    create table parent (
        id bigint not null,
        primary key (id)
    );

    create table parent_student (
        parent_id bigint,
        student_id bigint not null,
        primary key (parent_id, student_id)
    );

    create table semester (
        id bigint not null auto_increment,
        begin_date date not null,
        end_date date not null,
        name varchar(255) not null,
        primary key (id)
    );

    create table student (
        id bigint not null,
        info_id bigint not null,
        level_id bigint,
        primary key (id)
    );

    create table student_task (
        id bigint not null auto_increment,
        description varchar(255),
        type varchar(255) not null,
        when_ datetime not null,
        class_id bigint,
        primary key (id)
    );

    create table teacher (
        avatar longblob,
        id bigint not null,
        info_id bigint not null,
        primary key (id)
    );

    create table teacher_class (
        teacher_id bigint not null,
        class_id bigint not null,
        primary key (class_id, teacher_id)
    );

    alter table basic_user 
        add constraint UK_3v1wbn30ju5bxbrnpyidmu79u unique (email, login);

    alter table basic_user 
        add constraint UK_rpsftjy5f966jyni917rw19yg unique (login);

    alter table class_info 
        add index FK_6iati1u00txam28fl3551cop7 (level_id), 
        add constraint FK_6iati1u00txam28fl3551cop7 
        foreign key (level_id) 
        references level (id);

    alter table class_info 
        add index FK_qjyp0wta6xtosu0gq8juu20xd (semester_id), 
        add constraint FK_qjyp0wta6xtosu0gq8juu20xd 
        foreign key (semester_id) 
        references semester (id);

    alter table class_student 
        add index FK_bkb08ykubi3cqt7xpul6t5er8 (class_id), 
        add constraint FK_bkb08ykubi3cqt7xpul6t5er8 
        foreign key (class_id) 
        references class_info (id);

    alter table class_student 
        add index FK_1amyqdu4psf83jb8ahlsrklyg (student_id), 
        add constraint FK_1amyqdu4psf83jb8ahlsrklyg 
        foreign key (student_id) 
        references student (id);

    alter table detailed_info 
        add constraint UK_923obfxoosj544m2wwhiib7ea unique (pin);

    alter table grade 
        add index FK_ejvylbxm9l2bveja88c5u24bu (student_id), 
        add constraint FK_ejvylbxm9l2bveja88c5u24bu 
        foreign key (student_id) 
        references student (id);

    alter table grade 
        add index FK_g8qv5m0eqhrcixp25067aq55u (student_task_id), 
        add constraint FK_g8qv5m0eqhrcixp25067aq55u 
        foreign key (student_task_id) 
        references student_task (id);

    alter table level 
        add constraint UK_jr9r4hutcrxfdcyq0ck0lns2d unique (name_);

    alter table parent 
        add index FK_jhtu6p11uyjfadgtd00hbidh2 (id), 
        add constraint FK_jhtu6p11uyjfadgtd00hbidh2 
        foreign key (id) 
        references basic_user (id);

    alter table parent_student 
        add constraint UK_57x72kwvd1weo7vp8k79q1dup unique (student_id);

    alter table parent_student 
        add index FK_ihvxc37nk750ps1b1qeg2gqds (parent_id), 
        add constraint FK_ihvxc37nk750ps1b1qeg2gqds 
        foreign key (parent_id) 
        references parent (id);

    alter table parent_student 
        add index FK_57x72kwvd1weo7vp8k79q1dup (student_id), 
        add constraint FK_57x72kwvd1weo7vp8k79q1dup 
        foreign key (student_id) 
        references student (id);

    alter table semester 
        add constraint UK_i2h7dsgrb3fkhvq8oxbquhkiq unique (name);

    alter table student 
        add constraint UK_r7rhq7flnhuad3dqbcjj5cb6g unique (info_id);

    alter table student 
        add index FK_r7rhq7flnhuad3dqbcjj5cb6g (info_id), 
        add constraint FK_r7rhq7flnhuad3dqbcjj5cb6g 
        foreign key (info_id) 
        references detailed_info (id);

    alter table student 
        add index FK_q5gisosbgx7slf5r036g7rdlc (level_id), 
        add constraint FK_q5gisosbgx7slf5r036g7rdlc 
        foreign key (level_id) 
        references level (id);

    alter table student 
        add index FK_m4oyvjystgi94h8yo4v8oijrr (id), 
        add constraint FK_m4oyvjystgi94h8yo4v8oijrr 
        foreign key (id) 
        references basic_user (id);

    alter table student_task 
        add index FK_o43m1xgtqh48lvwbbb6aw23uq (class_id), 
        add constraint FK_o43m1xgtqh48lvwbbb6aw23uq 
        foreign key (class_id) 
        references class_info (id);

    alter table teacher 
        add constraint UK_towtpdaerkwtapdjff3t49iws unique (info_id);

    alter table teacher 
        add index FK_towtpdaerkwtapdjff3t49iws (info_id), 
        add constraint FK_towtpdaerkwtapdjff3t49iws 
        foreign key (info_id) 
        references detailed_info (id);

    alter table teacher 
        add index FK_ih20pr4twvb71fvoeypi8crnl (id), 
        add constraint FK_ih20pr4twvb71fvoeypi8crnl 
        foreign key (id) 
        references basic_user (id);

    alter table teacher_class 
        add index FK_1khxall52ind57fgxydt42rcq (class_id), 
        add constraint FK_1khxall52ind57fgxydt42rcq 
        foreign key (class_id) 
        references class_info (id);

    alter table teacher_class 
        add index FK_1bu4s2rxs9uyamynbkouh1qaa (teacher_id), 
        add constraint FK_1bu4s2rxs9uyamynbkouh1qaa 
        foreign key (teacher_id) 
        references teacher (id);
