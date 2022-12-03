

CREATE USER 'simple'@'localhost' IDENTIFIED BY 'simple';
CREATE USER 'simple'@'%' IDENTIFIED BY 'simple';

GRANT ALL PRIVILEGES ON *.* TO 'simple'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'simple'@'%';

CREATE SCHEMA IF NOT EXISTS mss;

CREATE TABLE IF NOT EXISTS POINT_LOG (
    idx                       int auto_increment  primary key,
    continuous_attendance_cnt int      null,
    point                     int      null,
    user_point                int      null,
    point_code                char(2)  null,
    created_at                datetime null default CURRENT_TIMESTAMP,
    fk_user                   int      not null
);

CREATE TABLE IF NOT EXISTS USER (
    idx           bigint auto_increment primary key,
    name          varchar(30) null,
    point_current int          null default 0,
    created_at    datetime     null default CURRENT_TIMESTAMP
);

INSERT INTO mss.USER (name, point_current, created_at) VALUES
 ('user1', DEFAULT, DEFAULT), ('user2', DEFAULT, DEFAULT), ('user3', DEFAULT, DEFAULT),
 ('user4', DEFAULT, DEFAULT), ('user5', DEFAULT, DEFAULT), ('user6', DEFAULT, DEFAULT),
 ('user7', DEFAULT, DEFAULT), ('user8', DEFAULT, DEFAULT), ('user9', DEFAULT, DEFAULT),
 ('user10', DEFAULT, DEFAULT), ('user11', DEFAULT, DEFAULT);
