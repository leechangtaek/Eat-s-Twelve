select * from restaurant where r_name like '%구로%' or location like '%구로%';
select * from restaurant_menu where menu like '%짬뽕%';

select count(*) from restaurant r join photo p on r.r_name=p.r_name where r.r_name like '%구로%'; 

select r.r_name, r.location from restaurant r join photo p on r.r_name = p.r_name where r.location like '%강남%' group by r.r_name, r.location;
select r.r_name, r.location from restaurant r join photo p on r.r_name = p.r_name where r.location like '%강남%';
select * from photo where location like '%강남%';

select * from restaurant order by 1;

select * from (
select rownum rnum, v.* from (
select p.* from restaurant r join photo p on r.r_name = p.r_name join restaurant_menu m on r.r_name = m.r_name 
where (r.r_name like '%강남%' or r.location like '%강남%' or m.menu like '%강남%' or r.category like '%강남%' or r.type like '%강남%') and img_name like '%1.%') v
) where rnum between 10 and 18;

--select * from (select rownum rnum, v.* from (select distinct p.* from restaurant r join photo p on r.r_name = p.r_name join restaurant_menu m on r.r_name = m.r_name where (r.r_name like ? or r.location like ? or m.menu like ? or r.category like ? or r.type like ?) and img_name like '%1.%') v)
select * from restaurant r join restaurant_menu m on r.r_name = m.r_name where m.single_yn='Y' and r.r_name='갓포아키(강남)'; 


select * from photo where img_name like '%1.%';

select * from photo;

select count(*) from restaurant r join photo p on r.r_name = p.r_name join restaurant_menu m on r.r_name = m.r_name 
where r.r_name like '%구로%' or r.location like '%구로%' or m.menu like '%r구로%' or r.category like '%구로%' or r.type like '%구로%'
group by p.img_name order by 1;


--where r.r_name like '%구로%' or r.location like '%구로%' or m.menu like '%r구로%' or r.category like '%구로%' or r.type like '%구로%';
--group by r.r_name;



select r.r_name, grade from restaurant r join photo p on r.r_name = p.r_name join restaurant_menu m on r.r_name = m.r_name
where (r.r_name like '%강남%' or r.location like '%강남%' or m.menu like '%강남%' or r.category like '%강남%' or r.type like '%강남%') group by r.r_name, grade order by grade desc;


select r.r_name, sum(nvl(l.posting_type,0)) from restaurant r join photo p on r.r_name = p.r_name join restaurant_menu m on r.r_name = m.r_name left join user_like l on r.r_name = p.r_name
where (r.r_name like '%강남%' or r.location like '%강남%' or m.menu like '%강남%' or r.category like '%강남%' or r.type like '%강남%') group by r.r_name order by 2 desc;


select r_name, sum(decode(single_yn,'Y',1,0)) cnt from restaurant_menu where r_name like '%비와%' group by r_name;

select * from photo where r_name = '구로곱창' and img_name like '%1.%';

select * from restaurant_menu where single_yn = 'N';

select * from restaurant;
select r_name, grade from restaurant where location like '%강남%' order by grade desc;
select r_name from (select r.*, rownum rnum from (select r.r_name from restaurant r where (r.r_name like '%강남%' or r.location like '%강남%' or r.category like '%강남%' or r.type like '%강남%' ) order by grade desc) r) where rnum between 10 and 18;

select r_name from (select r.*, rownum rnum from (select r.r_name, sum(nvl(l.posting_type,0)) from restaurant r left join user_like l on r.r_name = l.r_name where (r.r_name like '%강남%' or r.location like '%강남%' or r.category like '%강남%' or r.type like '%강남%') group by r.r_name order by 2 desc) r) where rnum between 4 and 8;

select r_name, location from restaurant;

select img_name from photo where r_name = '홍콩반점0410';

select img_no, r_name, location, img_name from (select v.*, rownum rnum from(select p.* from restaurant r join photo p on r.r_name = p.r_name where type = '한식' and p.img_name like '%1.%' order by grade) V) where rnum <=20;

select * from user_tables;
