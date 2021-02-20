select to_char(trunc(avg(salary),0),'99,999')as 사원급여평균 from employees;


select distinct department_id as 부서코드, to_char(round(avg(salary),0),'L99,999,999')as 평균급여
from employees
group by department_id
having avg(salary) >= 5000
order by department_id asc;

select * from employees;

select last_name, avg(salary)
from employees
group by department_id; --group by절에 없는 것(last_name)을 select 에 조회하면 error

--문제1
select job_id,sum(salary)
from employees
group by job_id;

select department_id, max(salary)
from employees
group by department_id;

select department_id,max(salary) as max_salart
from employees
group by department_id
having department_id in(10,20)
order by department_id;

select department_id, max(salary) as max_salary
from employees
where department_id in(10,20)
group by department_id
order by department_id;

select  employee_id,
        employees.department_id,
        department_name
from employees, departments
where employees.department_id = departments.department_id; 

select e.employee_id,
        e.department_id,
        d.department_name
from employees e, departments d
where e.department_id = d.department_id;

select employee_id, department_id, department_name
from employees
join departments using(department_id);

select department_id, city
from departments d,locations l
where d.location_id = l.location_id and department_id = 10;

select department_id, city
from departments
join locations using (location_id);

select e.last_name, d.department_id, d.department_name
from employees e, departments d
where e.department_id=d.department_id(+);

select last_name, department_id, department_name
from employees
left join departments using(department_id);

select e.last_name, d.department_id, d.department_name
from employees e, departments d
where e.department_id(+) = d.department_id;

select last_name, department_id, department_name
from employees
right join departments using(department_id);

select last_name,department_id, department_name
from employees
full join departments using(department_id);

select e.last_name, d.department_id, d.manager_id
from employees e, departments d
where e.department_id=d.department_id and e.manager_id = d.manager_id;

select last_name, department_id, manager_id
from employees
inner join departments using(department_id, manager_id);

create table locations2 as select* from locations;
select * from locations2;
alter table locations2 rename column location_id to loc_id;

select d.department_id, l.city
from departments d, locations2 l
where d.location_id = l.loc_id;

select department_id, city
from departments
join locations2 on(location_id=loc_id);

select department_id, city
from departments d
join locations2 l on(d.location_id= l.loc_id);

select last_name,job_title,department_name
from employees
join departments using(department_id)
join jobs using(job_id);

select employees.last_name as "사원이름", 
        locations2.city as "도 시", 
        departments.department_name as "부서이름"
from departments
join locations2 on(loc_id = location_id)
join employees using(department_id)
--where city = 'Seattle' or city='Oxford' 아랫줄과 동일한 결과
where city in('Seattle','Oxford')
order by 2;

select employee_id as 사원번호,
        last_name as 사원이름,
        department_name as 부서이름,
        city as 도시,
        street_address as 도시주소,
        country_name as 나라이름
from employees
left join departments using(department_id)
join locations2 on(location_id = loc_id)
join countries using(country_id)
where street_address like '%Ch%' or
        street_address like '%Sh%' or
        street_address like '%Rd%'
order by 6, 4;





select * from locations2;

select * from countries;
