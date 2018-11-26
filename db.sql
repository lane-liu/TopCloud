---------------新建项目数据库---------------------
create database topcloud;

-----------------------测试数据表------------
create table user_lf(
	id int(6),
	name varchar(22),
	password varchar(22),
	age int(10),
	tel varchar(22)
);

insert into user_lf values(1,'Tom','123456',20,'0562-454656');
insert into user_lf values(2,'Jake','123456',13,'0562-565444');
insert into user_lf values(3,'Mary','123456',57,'0562-0124312');
insert into user_lf values(4,'Jreem','123456',45,'0562-5546546');

select * from user_lf;
----------------***************测试***************-----------------------------------

--------------------用户表---------------------------------
DROP TABLE topcloud_user;
CREATE TABLE topcloud_user (
  id int(12)  PRIMARY KEY auto_increment,
  username varchar(50) NOT NULL,
  email varchar(50),
  password varchar(50) NOT NULL,
  age int(18) default null,
  sex varchar(18) default null,
  address varchar(50) default null,
  tel varchar(18) default null,
  is_ckeckedEmail varchar(22) default 'false',
  regiesttime datetime,
  lastlogintime datetime,
  passwordquestion varchar(50) default null,
  passwordquestionanwiwes varchar(50) default null,
  maxdevicespace varchar(50)  default  '1GB',
  surplusdevicespace varchar(50)default  '1GB',
  UNIQUE KEY email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into topcloud_user(username,email,password,regiesttime,lastlogintime) values ('刘小龙','123456@qq.com','123456','2018-01-01 08:00','2018-01-01 08:00')
delete from topcloud_user id=4;
select * from topcloud_user ;

--------------------文件表----------------------
DROP TABLE IF EXISTS topcloud_file;

create table topcloud_file(
	id int(12) PRIMARY KEY auto_increment,
	filename varchar(5000) NOT NULL,
	filepath varchar(5000) NOT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	isDir boolean default false, 
	filesize varchar(5000) NOT NULL,
	fileuploadtime  datetime,
	filetype varchar(5000) NOT NULL,
	filelevel int(20) NOt NULL,
	user_id int(12) NOT NULL,
	MD5ID varchar(50),
	foreign key(user_id) references topcloud_user(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('1.txt','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload',false,'1KB','2018-01-01 08:00','txt',1,1);

insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('show','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload',true,'0KB','2018-01-01 08:00','dri',1,1);
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('2.txt','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show',false,'1KB','2018-01-01 08:00','txt',2,1);

 
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('5.mp3','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show',false,'1KB','2018-01-01 08:00','doc',2,1);
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('wav','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show',false,'1KB','2018-01-01 08:00','pptx',2,1);
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('6.ape','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show',false,'1KB','2018-01-01 08:00','ape',2,1);
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('sdf','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show',false,'1KB','2018-01-01 08:00','sdf',2,1);
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('7.mid','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show',false,'1KB','2018-01-01 08:00','bmp',2,1);
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('aaa','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show',false,'1KB','2018-01-01 08:00','jpg',2,1);
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('8.aif','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show',false,'1KB','2018-01-01 08:00','wmv',2,1);
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('txt','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show',false,'1KB','2018-01-01 08:00','flv',2,1);
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('9.flac','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show',false,'1KB','2018-01-01 08:00','rmvb',2,1);
insert into topcloud_file(filename,filepath,isDir,filesize,fileuploadtime,filetype,filelevel,user_id) values('10.ape','C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show',false,'1KB','2018-01-01 08:00','ape',2,1);


update topcloud_file set filename='10.apk', filepath='C:\\\apache-tomcat-6.0.18\\\webapps\\\upload' ,fileuploadtime='2018-10-01 08:00' where user_id=1 and filepath='C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\show';
update topcloud_file set filepath='C:\\\apache-tomcat-6.0.18\\\webapps\\\upload\\\史可' where id=1;

delete from topcloud_file where id=18;
select * from topcloud_file where user_id=1;

-------------------------回收站-------------------------------------
DROP TABLE IF EXISTS topcloud_file_garbage;
create table topcloud_file_garbage(
	id int(12) PRIMARY KEY auto_increment,
	filename varchar(5000) NOT NULL,
	filepath varchar(5000) NOT NULL,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
	isDir boolean default false, 
	filesize varchar(5000) NOT NULL,
	fileuploadtime  datetime,
	filetype varchar(5000) NOT NULL,
	filelevel int(20) NOt NULL,
	user_id int(12) NOT NULL,
	MD5ID varchar(50),
	foreign key(user_id) references topcloud_user(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
select * from topcloud_file_garbage;
