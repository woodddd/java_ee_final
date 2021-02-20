create or replace view v_view5
as select employee_id, last_name, job_id
from employees
where job_id='IT_PROG'
with read only;

select * from v_view5;
delete from v_view5;

create or replace view v_view6
as select employee_id, last_name, email, hire_date, job_id
from employees
where job_id='IT_PROG'
with check option;

select * from v_view6;

insert into v_view6(employee_id, last_name, email, hire_date,job_id)
values(500,'kim','candy','2004-01-01','Sales');

update v_view6 set job_id='Sales' where employee_id=103;

insert into v_view6(employee_id, last_name, email, hire_date, job_id)
values(500,'kim','candy','2004-01-01','IT_PROG');

select * from v_view6;

delete from v_view6;

-- 문제4
create table bookshop(
isbn varchar2(10) constraint PISBN primary key,
title varchar2(50) constraint CTIT not null,
author varchar2(50),
price number,
company varchar2(30));

select * from bookshop;

delete from bookshop;

insert into bookshop values ('is001','자바3일완성','김자바',25000,'야메루출판사');
insert into bookshop values ('pa002','JSP달인되기','이달인',28000,'공갈닷컴');
insert into bookshop values ('or003','오라클무작정따라하기','박따라',23500,'야메루출판사');
commit;

create table bookorder(
idx number primary key,
isbn varchar2(10),
constraint FKISBN foreign key(isbn) references bookshop,
-- isbn varchar2(10) constraint FKISBN references bookshop(isbn), 을 위의 두줄과 동일하게 사용할 수 있음.
qty number);

insert into bookorder values(idx_seq.nextval,'is001',2);
insert into bookorder values(idx_seq.nextval,'or003',3);
insert into bookorder values(idx_seq.nextval,'pa002',5);
insert into bookorder values(idx_seq.nextval,'is001',3);
insert into bookorder values(idx_seq.nextval,'or003',10);

drop table bookorder;

select * from bookorder;
desc bookorder;

create sequence idx_seq increment by 1 start with 1 nocycle nocache;

create or replace view bs_view
as select title as 책제목, author as 저자, sum(qty*price) as 총판매금액
from bookshop
join bookorder using(isbn)
group by (title,author) -- title만을 그룹을 걸면 author 필드는 결과로 나올 수 없으므로  author 도 같이 그룹을 걸어주어야 한다.
with read only;

select * from bs_view;