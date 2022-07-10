-- 외래키 삭제
alter table attached_photo
    drop
        foreign key FKjb1h16xhjextj0vgf99cbu22r;
alter table point_history
    drop
        foreign key FKlmm2nu7f5elblp2o2w2rrcwhl;
alter table review
    drop
        foreign key FKn429agmmvh298piqrnnd4gbfg;
alter table review
    drop
        foreign key FK6cpw2nlklblpvc7hyt7ko6v3e;


-- 리뷰첨부이미지
drop table if exists attached_photo;
create table attached_photo (
                                attached_photo_id BINARY(16) not null,
                                image varchar(255),
                                review_id BINARY(16),
                                primary key (attached_photo_id)
);

-- 장소
drop table if exists place;
create table place (
                       place_id BINARY(16) not null,
                       name varchar(255),
                       primary key (place_id)
);

-- 포인트히스토리
drop table if exists point_history;
create table point_history (
                               point_history_id BINARY(16) not null,
                               amount bigint,
                               created_date_time datetime(6),
                               event_target_id varchar(255),
                               event_type varchar(255),
                               is_bonus bit,
                               original_point_id BINARY(16),
                               point_type varchar(255),
                               state varchar(255),
                               user_id BINARY(16),
                               primary key (point_history_id)
);

--
drop table if exists review;
create table review (
                        review_id BINARY(16) not null,
                        content varchar(255),
                        created_date_time datetime(6),
                        is_deleted varchar(255),
                        update_date_time datetime(6),
                        place_id BINARY(16),
                        user_id BINARY(16),
                        primary key (review_id)
);

-- 사용자
drop table if exists users;
create table users (
                       user_id BINARY(16) not null,
                       name varchar(255),
                       primary key (user_id)
);


-- 외래키 연결
alter table attached_photo
    add constraint FKjb1h16xhjextj0vgf99cbu22r
        foreign key (review_id)
            references review (review_id);
alter table point_history
    add constraint FKlmm2nu7f5elblp2o2w2rrcwhl
        foreign key (user_id)
            references users (user_id);
alter table review
    add constraint FKn429agmmvh298piqrnnd4gbfg
        foreign key (place_id)
            references place (place_id);
alter table review
    add constraint FK6cpw2nlklblpvc7hyt7ko6v3e
        foreign key (user_id)
            references users (user_id);