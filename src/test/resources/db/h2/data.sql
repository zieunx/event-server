-- 유저
INSERT INTO USERS (USER_ID, NAME) VALUES ('f8f5f991-b3e1-4f37-95c7-5353426e11a8', '유저 1');

-- 장소
INSERT INTO PLACE
    (PLACE_ID, NAME)
VALUES
    ('5b5435a1-d786-4f3a-9144-44fa9102d93f', '장소 1'),
    ('a88c3003-908e-4ce9-9497-ede96fb2ceee', '장소 2');


-- 리뷰첨부이미지
INSERT INTO ATTACHED_PHOTO
(ATTACHED_PHOTO_ID, IMAGE, REVIEW_ID)
VALUES
    ('9cb013cd-12f1-4b98-be8a-f7be08d59e6b', null, null),
    ('d1ee0ac8-e3cb-4780-9793-b5d7cb17e98a', null, null),
    ('d960d63a-3de9-49b5-bb3b-25b31adbc8a2', null, null);