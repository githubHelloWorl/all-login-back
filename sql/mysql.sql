create database aaa;

drop table if exists student;
create table student(
                        sid   int primary key auto_increment          comment '学生id',
                        sno   varchar(255)   NOT NULL unique          comment '学生学号',
                        spass varchar(255)   NOT NULL                 comment '密码',
                        sname varchar(255)   NOT NULL                 comment '学生姓名',
                        ssex  enum('男','女') default '男'             comment '性别',
                        sage  int                                     comment '年龄',
                        sdept varchar(255)                            comment '所属院系',
                        check ( sage > 0 && sage < 200)
);

insert into student(sno,spass,sname,ssex,sage,sdept) values('2024001','1234','张三','男',25,'商学院');
insert into student(sno,spass,sname,ssex,sage,sdept) values('2024002','1234','李四','男',55,'理学院');
insert into student(sno,spass,sname,ssex,sage,sdept) values('2024003','1234','王五','女',45,'商学院');