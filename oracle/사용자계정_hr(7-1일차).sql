create or replace view v_view7("부서ID","부서평균")
as select nvl(department_id,5000),
            round( avg(salary), -3)
            from employees
            group by department_id
            order by department_id asc;

select * from v_view7;

select 부서ID, 부서평균
from (select nvl(department_id, 5000) "부서ID", 
            round( avg(salary), -3) "부서평균"
            from employees
            group by department_id
            order by department_id asc);
        
-- 문제5번 풀이
--select department_name as 부서명,
--            max(salary) as 최대급여
--        from employees
--        join departments using(department_id)
--        group by department_name;

select last_name as 이름
        from employees
        where salary in(select max(salary) as 최대급여
        from employees
        join departments using(department_id)
        group by department_name)
        union
        select department_name as 부서명,
            max(salary) as 최대급여
        from employees
        join departments using(department_id)
        group by department_name;
        
---------------------------------------------------------          
select 부서명,최대급여     
from(select department_name as 부서명, max(salary) as 최대급여
     from employees
     join departments using(department_name)
     group by(department_name));
