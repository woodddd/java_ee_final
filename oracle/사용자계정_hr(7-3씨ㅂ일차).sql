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
        
-- 우석 문제5-1번 풀이
select department_name as 부서명,
        max(salary) as 최대급여
        from employees
        join departments using(department_id)
        group by department_name;
---------------------------------------------------------          
-- 강사님 문제 5-1 번
select 부서명,최대급여     
from(select department_name as 부서명,
        max(salary) as 최대급여
        from employees
        join departments using(department_id)
        group by department_name);
        
--문제 5-2
select 이름,부서명,최대급여     
from(select last_name as 이름,
        department_name as 부서명,
        salary as 최대급여
        from employees
        join departments using(department_id)
        where (department_id, salary) in(select department_id, max(salary) from employees group by department_id)
        );

--인라인뷰의 개념: from 절안에들어가는 select 문을 이야기한다. 
select rownum, last_name, salary
from (select last_name, nvl(salary,0) as salary from employees order by 2 desc)
where rownum<=3; -- rownum 가 1 인 것은 가지고 올 수 있지만, 특정행은 가지고올수 없다.

select  * from 
(select rownum , ceil(rownum/3) as page, tt.* from -- ceil 은 올림
(select last_name, nvl(salary,0)as salary from employees order by salary desc)tt
) where page=2;

select * from
(select rownum rn, tt.* from 
(select last_name, nvl(salary,0) as salary from employees order by 2 desc)tt
) where rn>=4 and rn<=6; -- 기존rownum 의 값의 범위를 잡아주면 결과가 나오지 않았지만,
-- 현재는 from 절에서 인라인뷰 를 이용하여 테이블을 가져왔고, 가져온 테이블에대한 rownumber
--의 범위를 조건으로 달아준 것이기 때문에 문제없이 사용할 수 있다.

--문제 6
select last_name as 사원이름, 
       department_name as 부서명, 
       to_char(salary, 'L99,999') as 연봉 from
(select rownum rn, tt.* from
(select last_name, 
        department_name, 
        salary*12+(salary*12* nvl(commission_pct,0)) as salary
        from employees
join departments using(department_id)
order by 3)tt
)where rn<=5;
 


