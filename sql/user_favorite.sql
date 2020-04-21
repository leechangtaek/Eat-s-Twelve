create table user_favorite(
        favorite_no number,
        id varchar2(30) not null,
        r_name varchar2(30) not null,
        constraint pk_user_favorite_no primary key (favorite_no),
        constraint fk_user_favorite_id foreign key (id) references user_log_info(id),
        constraint fk_user_favorite_menu foreign key (r_name) references restaurant(r_name) on delete cascade
);
create sequence seq_user_favorite_no;

create table user_recent(
            recent_no number,
            id varchar2(30) not null,
            r_name varchar2(30) not null,
            constraint pk_user_recent_no primary key (recent_no),
            constraint fk_user_recent_id foreign key (id) references user_log_info(id),
            constraint fk_user_recent_menu foreign key (r_name) references restaurant(r_name) on delete cascade
);
create sequence seq_user_recent_no;

insert into user_favorite(favorite_no, id, r_name) values(seq_user_favorite_no.nextval, 'ckdxor1014', '낭만부대찌개');
insert into user_favorite(favorite_no, id, r_name) values(seq_user_favorite_no.nextval, 'ckdxor1014', '춘자싸롱');
insert into user_favorite(favorite_no, id, r_name) values(seq_user_favorite_no.nextval, 'ckdxor1014', '구로곱창');

insert into user_recent(recent_no, id, r_name) values(seq_user_recent_no.nextval, 'ckdxor1014', '경식이네');
insert into user_recent(recent_no, id, r_name) values(seq_user_recent_no.nextval, 'ckdxor1014', '박용석스시');
insert into user_recent(recent_no, id, r_name) values(seq_user_recent_no.nextval, 'ckdxor1014', '최우영스시');
commit;


delete from user_favorite;
delete from user_recent;
