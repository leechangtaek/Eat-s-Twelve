--drop table inquire;

create table inquire (
          inquire_no number,
          id varchar2(30),
          r_name varchar2(30) not null,
          type varchar2(30) not null,
          location varchar2(150) not null,
          description varchar2(3000),
          category varchar2(50),
          r_phone varchar2(30),
          parking_yn char(1) default 'N',
          open_time number,
          close_time number,
          holiday varchar2(30),
          inq_date date default sysdate,
          accept_yn char(1) default 'N',
          
          constraint pk_inquire_no primary key (inquire_no),
          constraint ck_inquire_parking check (parking_yn in ('Y','N')),
          constraint ck_inquire_open check (open_time between 0 and 2400),
          constraint ck_inquire_close check (close_time between 0 and 2400)
);

create sequence seq_inquire_no;

create or replace trigger trg_inquire_yn
          after
          update on inquire
          for each row
begin
          --accept_yn이 N에서 Y로 바뀔때
          if :new.accept_yn = 'Y' then
                    insert into restaurant values (:new.r_name, :new.type, :new.location, default, default, :new.description, :new.category, :new.r_phone, :new.parking_yn, :new.open_time, :new.close_time, :new.holiday); 
          
          end if;
end;
/

