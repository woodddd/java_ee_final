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

-- ����4
create table bookshop(
isbn varchar2(10) constraint PISBN primary key,
title varchar2(50) constraint CTIT not null,
author varchar2(50),
price number,
company varchar2(30));

select * from bookshop;

delete from bookshop;

insert into bookshop values ('is001','�ڹ�3�Ͽϼ�','���ڹ�',25000,'�߸޷����ǻ�');
insert into bookshop values ('pa002','JSP���εǱ�','�̴���',28000,'��������');
insert into bookshop values ('or003','����Ŭ�����������ϱ�','�ڵ���',23500,'�߸޷����ǻ�');
commit;

create table bookorder(
idx number primary key,
isbn varchar2(10),
constraint FKISBN foreign key(isbn) references bookshop,
-- isbn varchar2(10) constraint FKISBN references bookshop(isbn), �� ���� ���ٰ� �����ϰ� ����� �� ����.
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
as select title as å����, author as ����, sum(qty*price) as ���Ǹűݾ�
from bookshop
join bookorder using(isbn)
group by (title,author) -- title���� �׷��� �ɸ� author �ʵ�� ����� ���� �� �����Ƿ�  author �� ���� �׷��� �ɾ��־�� �Ѵ�.
with read only;

select * from bs_view;