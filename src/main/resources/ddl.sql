# 管理员表
CREATE TABLE admin
(
    id       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

# 学生表
CREATE TABLE student
(
    id       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    teacher_name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

# 教师表
CREATE TABLE teacher
(
    id       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    student_name     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

# 课题表
CREATE  TABLE subject(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL ,
    request VARCHAR(255) NOT NULL ,
    teacher INT NOT NULL
);

