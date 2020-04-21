create table user_comment (
          board_no number,
          id varchar2(30),
          r_name varchar2(30),
          location varchar2(150),
          boarding varchar2(3000),
          grade number default 3,
          b_date date default sysdate,
          board_comment_level number default 1,
          board_ref number,  --게시글 번호
          board_comment_ref number, --참조하고있는 댓글번호, 댓글인경우 null
          
          constraint pk_user_comment primary key (board_no),
          constraint fk_user_comment_rname foreign key (r_name) references restaurant(r_name) on delete cascade,
          constraint fk_user_comment_id foreign key (id) references user_log_info(id),
          constraint ck_user_comment_grade check (grade between 0 and 5),
          constraint fk_user_ref foreign key(board_ref) references user_comment(board_no) on delete cascade,
          constraint fk_user_comment_ref foreign key(board_comment_ref) references user_comment(board_no) on delete cascade
); 

create sequence seq_user_comment;


