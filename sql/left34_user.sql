--drop table user_log_info;

create table user_log_info (
          id varchar2(30),
          pwd varchar2(300) not null,
          name varchar2(30) not null,
          email varchar2(100) not null,
          address varchar2(150) not null,
          phone varchar2(30) not null,
          age number(10),
          favorite varchar2(150),
          gender char(1) default 'M' not null,
          
          constraint pk_user_id primary key (id),
          constraint ck_user_gender check (gender in ('M','F'))
);

select favorite from user_log_info where id = 'ckdxor1014';

select * from restaurant where type like '%한식,중식,양식,디저트%';

insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('heejin','heejin1234','전희진','heejin@naver.com','서울시 강동구 강일동','010-9545-6377','24','한식,양식,일식,디저트','F');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('wjdgnsdl','wjdgnsdl1234','이정훈','wjdgnsdl@naver.com','경기도 시흥시','010-5877-2282','26','한식,일식,중식','M');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('seop200','seop100','정진섭','jinseop200@naver.com','경기도 용인시','010-6305-3467','27','한식,양식,중식','M');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('dohyeonzzang','zzangdohyeon','이도현','dohyeonzzang@naver.com','서울시 강남구','010-9441-7073','26','한식,중식,디저트','M');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('mohargga','youngkyun','정영균','youngkyun@gmail.com','경기도 군포시','010-3023-6244','32','한식,중식','M');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('ckdxor1014','babo','이창택','ckdxor1014@naver.com','경기도 안산시','010-8971-3257','26','한식,양식,일식,중식,디저트','M');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('baedragon','dragon','배용팔','dragon@naver.com','서울시 용산구 독서당로 111','010-5555-8888','43','한식,양식,중식','M');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('eight','8chun','강팔춘','eight@gmail.com','서울시 송파구 올림픽로 300 롯데월드타워 70층','010-1004-8888','51','한식,양식,디저트','F');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('femalechun','chunzzang','여춘계','femalechun@naver.com','서울시 마포구 양화로 45','010-1234-4567','17','양식,디저트','F');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('seolhyun','god','김설현','seolhyun@naver.com','서울시 서초구 신반포로 270','010-1212-3434','25','한식,디저트','F');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('tumen','2men','두만식','tumen@gmail.com','서울시 성동구 왕십리로 16','010-4567-1234','20','중식,일식','M');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('lucky','lucky123','최덕복','luck@gmail.com','서울시 서초구 신반포로15길 19','010-6666-1111','10','한식,중식','M');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('hooray','songhooray','송만세','hooray@naver.com','인천시 연수구 송도국제대로 261','010-9638-5274','8','한식,일식,중식,양식,디저트','M');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('angel','parkangel','박나은','angel@naver.com','울산시 중구 강북로 123','010-7777-7777','4','한식,양식,디저트','F');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('jihyun','queen','전지현','jihyun@gmail.com','서울시 강남구 영동대로141길 14','010-1313-4646','38','양식','F');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('hand','hand123','손예진','hand@naver.com','서울시 강남구 학동로82길 29','010-7410-8520','38','한식','F');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('kiyoung','gomusin','이기영','kiyoung@naver.com','서울시 서초구 신반포로15길 19','010-3333-2212','12','한식,디저트','M');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('seo','jun','서강준','seo@naver.com','서울시 강남구 언주로30길 56','010-1245-3214','27','한식,일식','M');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('hyekyo','song','송혜교','hyekyo@naver.com','서울시 용산구 유엔빌리지3길 118','010-7777-6666','39',',양식,디저트','F');
insert into left34.user_log_info(id, pwd, name, email, address, phone, age, favorite, gender)
values ('hazzang','hazzang96','신하진','hazzang@naver.com','부산광역시 해운대구 마린시티3로 37','010-5765-1234','24','한식,일식,중식,양식,디저트','F');

--commit;