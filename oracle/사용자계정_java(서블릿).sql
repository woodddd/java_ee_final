create table member(
name varchar2(30) not null,
id varchar2(30) primary key, --�⺻Ű, unique, not null, ���Ἲ ���� ����
pwd varchar2(30) not null,
gender varchar2(3),
email1 varchar2(20),
email2 varchar2(20),
tel1 varchar2(10),
tel2 varchar2(10),
tel3 varchar2(10),
zipcode varchar2(10),
addr1 varchar2(100),
addr2 varchar2(100),
logtime date);

select * from member;

delete member where id='hong';
commit;

