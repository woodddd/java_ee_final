--sql 1일차
select employee_id as 사원번호, last_name as "이  름", salary*12 as "연  봉"from employees;
--별칭입력시 유의사항: as 뒤에 공백이 있을 시 큰따옴표로 묶어줘야함.

select first_name ||' '|| last_name as "이  름" from employees;

select employee_id as 사원번호, first_name || ' ' || last_name as "이  름", salary * 12 || '달러' as "연  봉" 
from employees 
where employee_id in('100','101');

select last_name || ' is a ' || job_id as "Employee Detail" 
from employees 
where last_name like 'A%' and job_id like 'S%' 
order by "Employee Detail" asc;

select distinct department_id from employees;

select last_name, hire_date, department_id
from employees
where department_id = 10 or department_id = 90;

select last_name, hire_date, salary
from employees
where salary >= 2500 or salary < 3500;

select first_name || last_name as 사원명, '$' || salary as "월  급", department_id as 부서코드
from employees
where (salary <= 2500 or salary >= 3000) and department_id = 90;

select * from employees where last_name = 'King';

select * from employees where lower(last_name) = 'king';

select last_name, job_id, department_id
from employees
where job_id like '%MAN%';

select last_name, job_id, department_id
from employees
where job_id like 'IT%';

select last_name, salary, commission_pct
from employees
where commission_pct is not null;

select last_name, salary, commission_pct
from employees
where commission_pct is null;

select employee_id, last_name,job_id
from employees
where job_id = 'FI_MGR' or job_id = 'FI_ACCOUNT';

select employee_id, last_name,job_id
from employees
where job_id in('FI_MGR', 'FI_ACCOUNT');

select employee_id, last_name,salary
from employees
where salary>=10000 and salary <=20000;

select employee_id, last_name,salary
from employees
where salary between 10000 and 20000;

select last_name as 이름, department_id as 업무ID, salary ||'원' as "급  여"
from employees
where job_id in('SA_REP','AD_PRES') and salary > 10000;

select distinct job_id from employees;

select employee_id as 사원번호, last_name as 이름, hire_date as 입사일
from employees
where hire_date like '05%'; -- 오라클에선 연도의 4자리중 앞의 2자리를 기억하지 않음.

