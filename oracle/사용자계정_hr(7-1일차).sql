create or replace view v_view7("�μ�ID","�μ����")
as select nvl(department_id,5000),
            round( avg(salary), -3)
            from employees
            group by department_id
            order by department_id asc;

select * from v_view7;

select �μ�ID, �μ����
from (select nvl(department_id, 5000) "�μ�ID", 
            round( avg(salary), -3) "�μ����"
            from employees
            group by department_id
            order by department_id asc);
        
-- ����5�� Ǯ��
--select department_name as �μ���,
--            max(salary) as �ִ�޿�
--        from employees
--        join departments using(department_id)
--        group by department_name;

select last_name as �̸�
        from employees
        where salary in(select max(salary) as �ִ�޿�
        from employees
        join departments using(department_id)
        group by department_name)
        union
        select department_name as �μ���,
            max(salary) as �ִ�޿�
        from employees
        join departments using(department_id)
        group by department_name;
        
---------------------------------------------------------          
select �μ���,�ִ�޿�     
from(select department_name as �μ���, max(salary) as �ִ�޿�
     from employees
     join departments using(department_name)
     group by(department_name));
