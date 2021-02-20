select e.employee_id as 사원번호,
        e.last_name as 사원이름,
        m.last_name as 관리자
from employees e, employees m
where m.employee_id=e.manager_id;

select e.employee_id as 사원번호,
        e.last_name as 사원이름,
        m.last_name as 관리자
from employees e
join employees m on(m.employee_id=e.manager_id);

select * from countries, locations;

select * from countries cross join locations;

create table salgrade(
salvel varchar2(2),
lowst number,
highst number);

insert into salgrade values('A',20000,29999);
insert into salgrade values('B',10000,19999);
insert into salgrade values('C',0,9999);
commit;

select * from salgrade;

select last_name, salary, salvel
from employees
join salgrade on(salary between lowst and highst) -- 조인한 이후 salary값에서 해당 범위의 값을 찾고 필드를 출력함
order by salary desc;

create table employees_role as select * from employees where 1= 0;

select * from employees_role;

insert into employees_role values(101, 'Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', '1989-09-21', 'AD_VP', 17000.00, NULL, 100, 90);
insert into employees_role values(101, 'Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', '1989-09-21', 'AD_VP', 17000.00, NULL, 100, 90);
insert into employees_role values(101, 'Nee', 'Ko', 'NKOCHHAR', '515.123.4568', '1989-09-21', 'AD_VP', 17000.00, NULL, 100, 90);
insert into employees_role values(200, 'Neena', 'Kochhar', 'NKOCHHAR', '515.123.4568', '1989-09-21', 'AD_VP', 17000.00, NULL, 100, 90);
insert into employees_role values(200, 'Nee', 'Kochhar', 'NKOCHHAR', '515.123.4568', '1989-09-21', 'AD_VP', 17000.00, NULL, 100, 90);
insert into employees_role values(300, 'GilDong', 'Conan', 'CONAN', '010-123-4567', '2009-03-01', 'IT_PROG', 23000.00, NULL, 100, 90);
commit;

select employee_id, last_name from employees
union
select employee_id, last_name from employees_role;

select employee_id, last_name from employees
union all
select employee_id, last_name from employees_role;

select salary  from employees where department_id=10
union all
select salary  from employees where department_id=30 order by 1;

select employee_id, last_name from employees
minus
select employee_id, last_name from employees_role;

select employee_id, last_name from employees
intersect
select employee_id, last_name from employees_role;

select last_name as 사원이름, job_id as 업무ID, department_id as 부서ID from employees where department_id = 10
union
select last_name as 사원이름, job_id as 업무ID, department_id as 부서ID from employees_role where job_id = 'IT_PROG';

select last_name, job_title
from employees
join jobs using(job_id)
where job_title in('Stock Manager','Programmer');

select last_name,job_title
from employees
join jobs using(job_id)
where job_title='Stock Manager'
union
select last_name, job_title
from employees
join jobs using(job_id)
where job_title='Programmer'
order by 2;

select last_name, employee_id, hire_date
from employees
where department_id=20
union
select department_name, department_id, NULL
from departments
where department_id=20;