create or replace view v_view1
as select employee_id, last_name, salary, department_id from employees
where department_id=90;

select * from v_view1;

delete from v_view1;

create or replace view v_view2 as
select employee_id as 사원ID, last_name as 사원명, salary as 급여, department_id as 부서ID
from employees
where salary >=5000 and salary <= 10000;

-- create relpace view v_view2(사원ID,사원이름,급여,부서ID) as
--select employee_id, last_name, salary, department_id
--from employees
--where salary >=5000 and salary <= 10000;


drop view v_view2;

select * from v_view2;
update v_view2 set salary=12000 where employee_id=103; -- 에러가발생
update v_view2 set 급여=12000 where 사원ID=103; --103번이 12000 으로 변경되면서  뷰생성 의 조건인 5000이상 10000이하의 조건으로 생성된 레코드에 포함되지 않게 되므로 행이 사라지게됨

select * from employees where employee_id=103;



create or replace view v_view3 as
select employee_id as 사원번호, last_name as 사원명,department_name as 부서명
from employees
join departments using(department_id)
where department_id in(10,90)
order by 1;

select * from v_view3;

create or replace view v_view4 as
select employee_id as 사원번호, last_name as 사원명, to_char(salary,'9,999,999') || '원' as  급여, to_char(hire_date,'YYYY"년" MM"월" DD"일"') as 입사일, department_name as 부서명, city as 부서위치
from employees
join departments using(department_id)
join locations using(location_id)
where department_id in(10,90)
order by 1;


select * from v_view4;
