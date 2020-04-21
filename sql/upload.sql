create table upload(
          up_no number,
          r_name varchar2(30),
          location varchar2(150),
          original_filename varchar2(3000),
          renamed_filename varchar2(3000),
          
          constraint pk_upload_no primary key (up_no)
);

create sequence seq_upload;