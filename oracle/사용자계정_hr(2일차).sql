select last_name, department_id, hire_date 
from employees
order by 2 desc; -- 2���÷��� �������� ��������

select last_name, department_id, hire_date 
from employees
order by 2 desc, 3asc;
--2���÷����� �������� �ϰ�, ���� ���� �ִٸ� 3���÷��� �������� �������� �����Ѵ�.

select * from employees;

select last_name as "��  ��", salary*12 as "��  ��"
from employees
where last_name like 'K%'
order by 2 desc;

select employee_id, last_name, department_id
from employees
where lower(last_name) = 'higgins';

select mod(10,3) from dual;

select round(35765.357, 2) from dual; -- 35765.36
select round(35765.357, 0) from dual; -- 35765
select round(35765.357, -3) from dual; -- 36000

select trunc(35765.357, 2) from dual; -- 35765.35
select trunc(35765.357, 0) from dual; -- 35765
select trunc(35765.357, -3) from dual; --35000

select concat('Hello',' World')from dual; -- �̰ͺ��� || �� ���Ͽ� ���ڿ� ������ ���� �����

create table text(
str1 char(20),
str2 varchar2(20));

insert into text(str1,str2) values('angel','angel');
insert into text(str1,str2) values('��õ��','��õ��');

delete from text;

commit;

select lengthb(str1), lengthb(str2) from text;

select length(str1), length(str2) from text;

select length('korea') from dual;
select length('�ڸ���') from dual;

select lengthb('korea') from dual;
select lengthb('�ڸ���') from dual;

select instr('HelloWorld', 'W') from dual; --�ε����� 1�����ͽ���
select instr('HelloWorld','o',-5) from dual;
select instr('HelloWorld','o',-1) from dual;

select substr('I am very happy', 6, 4) from dual;
select substr('I am very happy', 6) from dual;

select * from employees;

select employee_id, concat(first_name, last_name) as name, length(concat(first_name, last_name)) as length
from employees
where substr(last_name, -1,1) = 'n';

select width_bucket(80,0,100,10) from dual;

select rtrim('test      ')||'exam' from dual;

select sysdate from dual;

select to_char(sysdate,'YYYY"��" MM"��" DD"��"') as ���ó�¥ from dual;
select to_char(sysdate, 'HH"��" MI"��" SS"��"') as ���ó�¥ from dual;
select to_char(sysdate, 'HH24"��" MI"��" SS"��"') as���ó�¥ from dual;

select add_months(sysdate, 7) from dual;

select last_day(sysdate) from dual;
select last_day('2004-02-01') from dual;
select last_day('2005-02-01')from dual;

select last_day(sysdate) - sysdate
from dual;

select substr(sysdate,4,2) from dual;