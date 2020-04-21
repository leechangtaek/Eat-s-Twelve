create table user_like (
         post_no number,
         id varchar2(30),
         r_name varchar2(30),
         posting_type varchar2(30),
         
         constraint pk_user_like_no primary key (post_no),
         constraint fk_user_like_id foreign key (id) references user_log_info(id),
         constraint fk_user_like_rname foreign key (r_name) references restaurant(r_name),
         constraint ck_user_like_posting_type check (posting_type in ('LIKE', 'WISH') )
);

create sequence seq_user_like;