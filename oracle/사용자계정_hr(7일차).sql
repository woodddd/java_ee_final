create or replace view v_view1
as select employee_id, last_name, salary, department_id from employees
where department_id=90;

select * from v_view1;

delete from v_view1;

create or replace view v_view2 as
select employee_id as ���ID, last_name as �����, salary as �޿�, department_id as �μ�ID
from employees
where salary >=5000 and salary <= 10000;

-- create relpace view v_view2(���ID,����̸�,�޿�,�μ�ID) as
--select employee_id, last_name, salary, department_id
--from employees
--where salary >=5000 and salary <= 10000;


drop view v_view2;

select * from v_view2;
update v_view2 set salary=12000 where employee_id=103; -- �������߻�
update v_view2 set �޿�=12000 where ���ID=103; --103���� 12000 ���� ����Ǹ鼭  ����� �� ������ 5000�̻� 10000������ �������� ������ ���ڵ忡 ���Ե��� �ʰ� �ǹǷ� ���� ������Ե�

select * from employees where employee_id=103;



create or replace view v_view3 as
select employee_id as �����ȣ, last_name as �����,department_name as �μ���
from employees
join departments using(department_id)
where department_id in(10,90)
order by 1;

select * from v_view3;

create or replace view v_view4 as
select employee_id as �����ȣ, last_name as �����, to_char(salary,'9,999,999') || '��' as  �޿�, to_char(hire_date,'YYYY"��" MM"��" DD"��"') as �Ի���, department_name as �μ���, city as �μ���ġ
from employees
join departments using(department_id)
join locations using(location_id)
where department_id in(10,90)
order by 1;


select * from v_view4;
