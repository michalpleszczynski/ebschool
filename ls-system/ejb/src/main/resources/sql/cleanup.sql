
    alter table class_info 
        drop 
        foreign key FK_6iati1u00txam28fl3551cop7;

    alter table class_student 
        drop 
        foreign key FK_bkb08ykubi3cqt7xpul6t5er8;

    alter table class_student 
        drop 
        foreign key FK_1amyqdu4psf83jb8ahlsrklyg;

    alter table grade 
        drop 
        foreign key FK_ejvylbxm9l2bveja88c5u24bu;

    alter table grade 
        drop 
        foreign key FK_lgn2ackvagnnjumqxapkn0wld;

    alter table parent 
        drop 
        foreign key FK_jhtu6p11uyjfadgtd00hbidh2;

    alter table parent_student 
        drop 
        foreign key FK_ihvxc37nk750ps1b1qeg2gqds;

    alter table parent_student 
        drop 
        foreign key FK_57x72kwvd1weo7vp8k79q1dup;

    alter table student 
        drop 
        foreign key FK_r7rhq7flnhuad3dqbcjj5cb6g;

    alter table student 
        drop 
        foreign key FK_q5gisosbgx7slf5r036g7rdlc;

    alter table student 
        drop 
        foreign key FK_m4oyvjystgi94h8yo4v8oijrr;

    alter table teacher 
        drop 
        foreign key FK_towtpdaerkwtapdjff3t49iws;

    alter table teacher 
        drop 
        foreign key FK_ih20pr4twvb71fvoeypi8crnl;

    alter table teacher_class 
        drop 
        foreign key FK_1khxall52ind57fgxydt42rcq;

    alter table teacher_class 
        drop 
        foreign key FK_1bu4s2rxs9uyamynbkouh1qaa;

    alter table test 
        drop 
        foreign key FK_h319pge3w3e1u4u5qgjydrwnk;

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

    drop table if exists teacher_class;

    drop table if exists test;
