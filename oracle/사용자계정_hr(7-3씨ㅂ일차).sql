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
        
-- �켮 ����5-1�� Ǯ��
select department_name as �μ���,
        max(salary) as �ִ�޿�
        from employees
        join departments using(department_id)
        group by department_name;
---------------------------------------------------------          
-- ����� ���� 5-1 ��
select �μ���,�ִ�޿�     
from(select department_name as �μ���,
        max(salary) as �ִ�޿�
        from employees
        join departments using(department_id)
        group by department_name);
        
--���� 5-2
select �̸�,�μ���,�ִ�޿�     
from(select last_name as �̸�,
        department_name as �μ���,
        salary as �ִ�޿�
        from employees
        join departments using(department_id)
        where (department_id, salary) in(select department_id, max(salary) from employees group by department_id)
        );

--�ζ��κ��� ����: from ���ȿ����� select ���� �̾߱��Ѵ�. 
select rownum, last_name, salary
from (select last_name, nvl(salary,0) as salary from employees order by 2 desc)
where rownum<=3; -- rownum �� 1 �� ���� ������ �� �� ������, Ư������ ������ü� ����.

select  * from 
(select rownum , ceil(rownum/3) as page, tt.* from -- ceil �� �ø�
(select last_name, nvl(salary,0)as salary from employees order by salary desc)tt
) where page=2;

select * from
(select rownum rn, tt.* from 
(select last_name, nvl(salary,0) as salary from employees order by 2 desc)tt
) where rn>=4 and rn<=6; -- ����rownum �� ���� ������ ����ָ� ����� ������ �ʾ�����,
-- ����� from ������ �ζ��κ� �� �̿��Ͽ� ���̺��� �����԰�, ������ ���̺����� rownumber
--�� ������ �������� �޾��� ���̱� ������ �������� ����� �� �ִ�.

--���� 6
select last_name as ����̸�, 
       department_name as �μ���, 
       to_char(salary, 'L99,999') as ���� from
(select rownum rn, tt.* from
(select last_name, 
        department_name, 
        salary*12+(salary*12* nvl(commission_pct,0)) as salary
        from employees
join departments using(department_id)
order by 3)tt
)where rn<=5;
 


