

CREATE USER 'simple'@'localhost' IDENTIFIED BY 'simple';
CREATE USER 'simple'@'%' IDENTIFIED BY 'simple';

GRANT ALL PRIVILEGES ON *.* TO 'simple'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'simple'@'%';

CREATE SCHEMA IF NOT EXISTS mss;

CREATE TABLE IF NOT EXISTS POINT_LOG (
    idx                       int auto_increment  primary key,
    continuous_attendance_cnt int      null,
    created_at                datetime null,
    point                     int      null,
    user_point                int      null,
    point_code                char(2)  null,
    fk_user                   int      not null
);

CREATE TABLE IF NOT EXISTS USER (
    idx           bigint auto_increment
    primary key,
    created_at    datetime     null,
    name          varchar(30) null,
    point_current int          null
);