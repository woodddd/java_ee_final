--sql 1����
select employee_id as �����ȣ, last_name as "��  ��", salary*12 as "��  ��"from employees;
--��Ī�Է½� ���ǻ���: as �ڿ� ������ ���� �� ū����ǥ�� ���������.

select first_name ||' '|| last_name as "��  ��" from employees;

select employee_id as �����ȣ, first_name || ' ' || last_name as "��  ��", salary * 12 || '�޷�' as "��  ��" 
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

select first_name || last_name as �����, '$' || salary as "��  ��", department_id as �μ��ڵ�
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

select last_name as �̸�, department_id as ����ID, salary ||'��' as "��  ��"
from employees
where job_id in('SA_REP','AD_PRES') and salary > 10000;

select distinct job_id from employees;

select employee_id as �����ȣ, last_name as �̸�, hire_date as �Ի���
from employees
where hire_date like '05%'; -- ����Ŭ���� ������ 4�ڸ��� ���� 2�ڸ��� ������� ����.

