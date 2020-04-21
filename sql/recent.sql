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