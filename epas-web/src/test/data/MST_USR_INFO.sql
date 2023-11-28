﻿SET DEFINE OFF;
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    USR_TEL, MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, 
    USR_IP, LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, 
    UPDATED_USR_ID, UPDATED_DT, CREATED_USR_ID)
 Values
   ('admin', 'admin@htk.com', '관리자', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', TO_TIMESTAMP('2023-08-21 오전 9:20:51.807000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    '010-1234-5678', 'EP0300,EP0700,EP0500,EP0600,EP0400,EP0800,EP0900,EP1000,EP1100', 4, 6, 'en', 
    '172.16.1.123', TO_TIMESTAMP('2023-07-25 오전 10:41:40.069000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-09-14 오후 2:31:39.085000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-06-27 오후 5:41:26.830000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    'admin', TO_TIMESTAMP('2023-09-14 오후 2:31:39.085000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'admin');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    USR_TEL, MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, 
    USR_IP, LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, 
    UPDATED_USR_ID, UPDATED_DT, CREATED_USR_ID)
 Values
   ('chamw', 'chamw@kcc.com', '차민우', '6b6858957b1e406d922bce9ad345b4ea9f3ef9519464bdd3bf660523f5ecf8db', TO_TIMESTAMP('2023-11-06 오후 1:40:26.090171','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    '010-9090-8081', 'EP1100,EP0300,EP0400,EP0500,EP0700,EP0600,EP0900,EP0800,EP1000', 4, 7, 'ko', 
    '172.16.1.50', TO_TIMESTAMP('2023-09-08 오후 6:27:35.179000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-11-06 오후 4:50:47.857500','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-06-23 오전 11:20:05.993000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    'chamw', TO_TIMESTAMP('2023-11-06 오후 4:50:47.857500','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'admin');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    USR_TEL, MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, 
    USR_IP, LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, 
    UPDATED_USR_ID, UPDATED_DT, CREATED_USR_ID)
 Values
   ('choih', 'choih@kcc.com', '조익환', 'cfb58eb03e0848da0e873f400278480b0b702764823d0f94914334d7be9c1f99', TO_TIMESTAMP('2023-11-21 오전 10:26:53.565000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    '010-9999-9999', 'EP0900,EP1000,EP1100,EP0100,EP0200,EP0300,EP0400,EP0500,EP0600,EP0700,EP0800', 4, 7, 'en', 
    '127.0.0.1', TO_TIMESTAMP('2023-08-07 오전 10:40:44.215000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-11-27 오후 2:52:09.000000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-06-23 오전 11:17:05.314000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    'choih', TO_TIMESTAMP('2023-11-27 오후 2:52:09.000000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'admin');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, USR_IP, 
    LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, UPDATED_USR_ID, 
    UPDATED_DT, CREATED_USR_ID)
 Values
   ('chooys', 'chooys@kcc.com', '추유식', 'b4f36d619b69d8db65781d7da781a246f5e3ad69f92cbf5ca22640a732fa649a', TO_TIMESTAMP('2023-11-06 오전 11:58:56.936002','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    ':', 4, 7, 'ko', '0:0:0:0:0:0:0:1', 
    TO_TIMESTAMP('2023-11-01 오전 12:00:00.000000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-11-08 오후 2:16:29.990859','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-09-01 오후 2:09:21.255000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'chooys', 
    TO_TIMESTAMP('2023-11-08 오후 2:16:29.990859','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'chamw');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, USR_IP, 
    LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, UPDATED_USR_ID, 
    UPDATED_DT, CREATED_USR_ID)
 Values
   ('gohkh', 'gohkh@kcc.com', '고기호', '67727a41b5b1d4dfca981e4045b1bb2f1e7fef0e3e8825c028949d186cad4c00', TO_TIMESTAMP('2023-11-06 오전 10:16:38.486289','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    'EP0100,EP0200,EP0300,EP0400,EP0500,EP0600,EP0700,EP0800,EP0900,EP1000,EP1100', 4, 3, 'ko', '0:0:0:0:0:0:0:1', 
    TO_TIMESTAMP('2023-11-01 오전 12:00:00.000000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-11-08 오후 2:15:07.935458','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-06-23 오전 11:18:44.699000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'gohkh', 
    TO_TIMESTAMP('2023-11-08 오후 2:15:07.935458','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'admin');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, USR_IP, 
    LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, UPDATED_USR_ID, 
    UPDATED_DT, CREATED_USR_ID)
 Values
   ('kang', 'kangmg@kcc.com', '강명구', '28f0116ef42bf718324946f13d787a1d41274a08335d52ee833d5b577f02a32a', TO_TIMESTAMP('2023-08-09 오후 7:06:21.122000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    ':', 12, 1, 'ko', '172.16.1.123', 
    TO_TIMESTAMP('2023-11-01 오전 12:00:00.000000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-08-09 오후 7:06:28.831000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-08-09 오후 7:03:53.042000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'kang', 
    TO_TIMESTAMP('2023-08-09 오후 7:06:28.831000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'admin');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, USR_IP, 
    LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, UPDATED_USR_ID, 
    UPDATED_DT, CREATED_USR_ID)
 Values
   ('kimbz', 'kimbz@kcc.com', '김범주', '28105ec3413b376d2e9ee315ac57653f8448475f76e19ec7343a3e3c19798493', TO_TIMESTAMP('2023-08-08 오후 3:55:47.075000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    ':', 4, 6, 'ko', '172.16.1.237', 
    TO_TIMESTAMP('2023-11-01 오전 12:00:00.000000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-09-14 오후 1:18:49.792000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-08-08 오후 3:55:29.534000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'kimbz', 
    TO_TIMESTAMP('2023-09-14 오후 1:18:49.792000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'parksk');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, USR_IP, 
    LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, UPDATED_USR_ID, 
    UPDATED_DT, CREATED_USR_ID)
 Values
   ('kimjs', 'kimjs@kcc.com', '김진숙', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', TO_TIMESTAMP('2023-11-06 오전 10:26:58.199363','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    ':', 4, 7, 'en', '0:0:0:0:0:0:0:1', 
    TO_TIMESTAMP('2023-11-01 오전 12:00:00.000000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-11-08 오후 1:49:35.048555','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-08-22 오전 10:21:59.542000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'kimjs', 
    TO_TIMESTAMP('2023-11-08 오후 1:49:35.048555','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'choih');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, USR_IP, 
    LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, UPDATED_USR_ID, 
    UPDATED_DT, CREATED_USR_ID)
 Values
   ('parksk', 'parksk@kcc.com', '박상국', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', TO_TIMESTAMP('2023-09-05 오후 1:53:14.585000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    'EP1100,EP0400,EP0600,EP0500,EP0700,EP0300,EP0800,EP0900,EP1000', 4, 1, 'en', '172.16.1.59', 
    TO_TIMESTAMP('2023-07-21 오후 4:54:59.973000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-09-14 오후 1:45:27.700000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-07-20 오전 10:48:02.503000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'parksk', 
    TO_TIMESTAMP('2023-09-14 오후 1:45:27.700000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'admin');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, USR_IP, 
    LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, UPDATED_USR_ID, 
    UPDATED_DT, CREATED_USR_ID)
 Values
   ('yangkw', 'yangkw@kcc.com', '양경욱', '514cedc5a74404407cb25627410a3e8287d284f3da11ac4fea1725a649b9f987', TO_TIMESTAMP('2023-11-06 오전 10:08:29.808929','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    'EP0100,EP0200,EP0300,EP0400,EP0500,EP0600,EP0700,EP0800,EP0900,EP1000,EP1100', 4, 3, 'ko', '0:0:0:0:0:0:0:1', 
    TO_TIMESTAMP('2023-09-07 오후 12:16:05.525000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-11-08 오후 2:31:35.785003','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-06-23 오전 11:18:44.699000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'yangkw', 
    TO_TIMESTAMP('2023-11-08 오후 2:31:35.785003','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'admin');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    USR_TEL, MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, 
    USR_IP, LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, 
    UPDATED_USR_ID, UPDATED_DT, CREATED_USR_ID)
 Values
   ('yookj', 'yookj@kcc.com', '유금종', 'a8a51ff4ca62a8f7891de80f93cf2b316cae3995f829fff55aafa28a53018e68', TO_TIMESTAMP('2023-11-06 오전 10:24:40.341944','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    '010-5555-6667', 'EP1000,EP1100,EP0300,EP0500,EP0400,EP0600,EP0700,EP0800,EP0900,EP1300,EP1200', 4, 3, 'ko', 
    '127.0.0.1', TO_TIMESTAMP('2023-09-07 오후 3:33:16.916000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-11-08 오전 10:43:20.320359','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-06-23 오전 11:18:03.866000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    'yookj', TO_TIMESTAMP('2023-11-08 오전 10:43:20.320359','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'admin');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, USR_IP, 
    LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, UPDATED_USR_ID, 
    UPDATED_DT, CREATED_USR_ID)
 Values
   ('yookj11', 'yookj@kcc.com', '유금종11', 'a8a51ff4ca62a8f7891de80f93cf2b316cae3995f829fff55aafa28a53018e68', TO_TIMESTAMP('2023-08-01 오후 6:06:35.547000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    ':', 1, 1, 'en', '127.0.0.2', 
    TO_TIMESTAMP('2023-11-01 오전 12:00:00.000000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-08-24 오전 11:00:28.302000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-07-03 오전 11:44:36.313000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'yookj11', 
    TO_TIMESTAMP('2023-08-24 오전 11:00:28.302000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Admin');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    USR_TEL, MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_IP, 
    LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, UPDATED_USR_ID, 
    UPDATED_DT, CREATED_USR_ID)
 Values
   ('yookj12', 'yookj@kcc.com', '유금종', 'a8a51ff4ca62a8f7891de80f93cf2b316cae3995f829fff55aafa28a53018e68', TO_TIMESTAMP('2023-08-24 오전 11:11:45.667000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    '010-5555-6666', ':', 6, 7, '127.0.0.1', 
    TO_TIMESTAMP('2023-11-01 오전 12:00:00.000000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-08-24 오전 11:11:50.801000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-08-24 오전 11:11:17.774000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'choih', 
    TO_TIMESTAMP('2023-09-11 오전 10:28:58.345000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'yookj');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    USR_TEL, MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, 
    USR_IP, LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, 
    UPDATED_USR_ID, UPDATED_DT, CREATED_USR_ID)
 Values
   ('yookj123', 'abcd@naver.com', 'testYKJ', '076eefa2bd027986f3bd8eaea5d4f1c84ea43e39bb511d1ffc2fbb8745423c48', TO_TIMESTAMP('2023-08-24 오전 11:07:31.578000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    '010-1111-2222', ':', 6, 7, 'ko', 
    '127.0.0.1', TO_TIMESTAMP('2023-11-01 오전 12:00:00.000000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-08-24 오전 11:07:16.442000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-08-21 오후 12:54:20.779000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    'yookj123', TO_TIMESTAMP('2023-08-24 오전 11:07:31.578000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'yookj');
Insert into DEVUSER.MST_USR_INFO
   (USR_ID, EMAIL, USR_NM, USR_PWD, USR_PWD_CREATED_DT, 
    USR_TEL, MENU_ORD, ROLE_SEQ, GROUP_SEQ, USR_LANG, 
    USR_IP, LAST_NOTIFY_CLEAR_DT, LAST_LOGIN_DT, USE_YN, CREATED_DT, 
    UPDATED_USR_ID, UPDATED_DT, CREATED_USR_ID)
 Values
   ('yookj21', 'yookj@kcc.com', '유금종', 'a8a51ff4ca62a8f7891de80f93cf2b316cae3995f829fff55aafa28a53018e68', TO_TIMESTAMP('2023-08-24 오후 12:09:57.351000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    '010-5555-6666', ':', 3, 7, 'ko', 
    '127.0.0.1', TO_TIMESTAMP('2023-11-01 오전 12:00:00.000000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2023-08-24 오후 12:10:02.583000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'Y', TO_TIMESTAMP('2023-08-24 오후 12:09:37.271000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 
    'choih', TO_TIMESTAMP('2023-09-11 오전 10:29:08.230000','YYYY-MM-DD AM fmHH12fm:MI:SS.FF'), 'yookj');
COMMIT;
