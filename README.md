# event-server

## version
- java 11
- Spring Boot 2.7.1
- Mysql 8.0.25

### test
- h2

## API
### 이벤트발생 API
- `POST` /events
- request-data:
```
{

"type": "REVIEW",

"action": "ADD",

"content": "리뷰등록",

"attachedPhotoIds": ["93b4e592-7c42-43f7-864e-3753837e840c"],

"reviewId": "69151cd5-d892-4795-b42d-dcc4edfd0277",

"userId": "f8f5f991-b3e1-4f37-95c7-5353426e11a8",

"placeId": "5b5435a1-d786-4f3a-9144-44fa9102d93f"

}
```

### 포인트 조회 API
- `GET` /points
#### Request Param
- (필수) userId: 회원UUID
- page: 페이지 (detault:1)
- size: 사이즈 (default:5)


## DDL
```sql
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
```

### data insert
```sql

-- 유저  
INSERT INTO USERS (USER_ID, NAME) VALUES (unhex(replace('f8f5f991-b3e1-4f37-95c7-5353426e11a8', '-', '')), '유저 1');  
  
-- 장소  
INSERT INTO PLACE  
    (PLACE_ID, NAME)  
VALUES  
    (unhex(replace('5b5435a1-d786-4f3a-9144-44fa9102d93f', '-', '')), '장소 1'),  
    (unhex(replace('a88c3003-908e-4ce9-9497-ede96fb2ceee', '-', '')), '장소 2');  
  
  
-- 리뷰첨부이미지  
INSERT INTO ATTACHED_PHOTO  
(ATTACHED_PHOTO_ID, IMAGE, REVIEW_ID)  
VALUES  
    (unhex(replace('9cb013cd-12f1-4b98-be8a-f7be08d59e6b', '-', '')), null, null),  
    (unhex(replace('d1ee0ac8-e3cb-4780-9793-b5d7cb17e98a', '-', '')), null, null),  
    (unhex(replace('d960d63a-3de9-49b5-bb3b-25b31adbc8a2', '-', '')), null, null);

```
