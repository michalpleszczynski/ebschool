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