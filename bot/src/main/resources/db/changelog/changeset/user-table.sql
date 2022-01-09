create table user_info (
    id bigint primary key ,
    user_login varchar(50),
    user_full_name varchar(150),
    last_activity timestamp
);