DROP SEQUENCE b_num_seq;
DROP SEQUENCE l_num_seq;
DROP SEQUENCE r_num_seq;
DROP SEQUENCE t_num_seq;
DROP SEQUENCE s_num_seq;
DROP SEQUENCE n_num_seq;

DROP TABLE l_num;
DROP TABLE blike;
DROP TABLE reply;
DROP TABLE board;
DROP TABLE schedule;
DROP TABLE todo;
DROP TABLE notice;
DROP TABLE usr;
DROP TABLE team;

--팀
CREATE TABLE team
(
    teamnum NUMBER PRIMARY KEY CHECK(teamnum IN (0,10, 20, 30,40)),
    teamname VARCHAR2(100) NOT NULL
);

--회원
CREATE TABLE usr
(
    usrid VARCHAR2(20) PRIMARY KEY,
    pw VARCHAR2(20) NOT NULL,
    usrname VARCHAR2(20) NOT NULL,
    teamnum NUMBER REFERENCES team
);

--게시판
CREATE TABLE board
(
    b_num NUMBER PRIMARY KEY,
    b_title VARCHAR2(100) NOT NULL,
    b_content VARCHAR2(1000) NOT NULL,
    usrid VARCHAR2(20) REFERENCES usr,
    b_regdate DATE DEFAULT sysdate NOT NULL,
    b_hitcount NUMBER DEFAULT 0 NOT NULL,
    teamnum NUMBER REFERENCES team
);

CREATE SEQUENCE b_num_seq;

--댓글
CREATE TABLE reply
(
    r_num NUMBER PRIMARY KEY,
    b_num NUMBER REFERENCES board,
    r_content VARCHAR2(1000) NOT NULL,
    usrid VARCHAR2(20) REFERENCES usr
);

CREATE SEQUENCE r_num_seq;

--좋아요
CREATE TABLE blike
(
    l_num NUMBER PRIMARY KEY,
    b_num NUMBER REFERENCES board,
    usrid VARCHAR2(20) REFERENCES usr
);

CREATE SEQUENCE l_num_seq;

--할 일
CREATE TABLE todo
(
    t_num NUMBER PRIMARY KEY,
    t_content VARCHAR2(1000) NOT NULL,
    usrid VARCHAR2(20) REFERENCES usr,
    teamnum NUMBER REFERENCES team,
    t_state NUMBER NOT NULL CHECK(t_state IN (0,1,2)),
    t_priority NUMBER NOT NULL CHECK(t_priority IN (0,1)),
    t_regdate DATE DEFAULT sysdate NOT NULL
);

CREATE SEQUENCE t_num_seq;

--일정 관리
CREATE TABLE schedule
(
    s_num NUMBER PRIMARY KEY,
    s_content VARCHAR2(1000) NOT NULL,
    usrid VARCHAR2(20) REFERENCES usr,
    startdate DATE DEFAULT sysdate NOT NULL,
    enddate DATE DEFAULT sysdate NOT NULL,
    teamnum NUMBER REFERENCES team
);

CREATE SEQUENCE s_num_seq;

--공지사항
CREATE TABLE notice
(
    n_num NUMBER PRIMARY KEY,
    n_title VARCHAR2(100) NOT NULL,
    n_content VARCHAR2(1000) NOT NULL,
    n_hitcount NUMBER DEFAULT 0 NOT NULL
);

CREATE SEQUENCE n_num_seq;

INSERT INTO team
VALUES (10, '기획');

INSERT INTO team
VALUES (20, '영업');

INSERT INTO team
VALUES (30, '인사');

INSERT INTO team
VALUES (40, '개발');

INSERT INTO team
VALUES(0, '협업툴관리자');

INSERT INTO usr
VALUES ('admin', 'admin', '관리자', 0); 

INSERT INTO usr
VALUES ('abc', 'abc', '홍길동', 10);

INSERT INTO usr
VALUES ('kim', 'kim', '김유리', 10); 

INSERT INTO usr
VALUES ('park', 'park', '박지원', 20); 

INSERT INTO usr
VALUES ('son', 'son', '손민정', 20); 

INSERT INTO usr
VALUES ('ahn', 'ahn', '안정환', 30); 

INSERT INTO usr
VALUES ('won', 'won', '원희민', 30);

INSERT INTO usr
VALUES ('jang', 'jang', '장주민', 40); 

INSERT INTO usr
VALUES ('han', 'han', '한수진', 40); 

INSERT INTO todo
VALUES(t_num_seq.nextval, '창고 정리', 'abc', 10, 0, 0, sysdate);

INSERT INTO todo
VALUES(t_num_seq.nextval, '프로젝트 기획', 'kim', 10, 0, 1, sysdate);

INSERT INTO todo
VALUES(t_num_seq.nextval, 'CF 모델 섭외 회의', 'son', 20, 1, 0, sysdate);

INSERT INTO todo
VALUES(t_num_seq.nextval, '개발 테스트', 'jang', 40, 1, 1, sysdate);

INSERT INTO todo
VALUES(t_num_seq.nextval, '개발 테스트', 'han', 40, 1, 1, sysdate);

INSERT INTO notice
VALUES(n_num_seq.nextval, '공지사항입니다', '공지사항 테스트', 0);

INSERT INTO notice
VALUES(n_num_seq.nextval, '공지사항입니다2', '공지사항 테스트', 0);

INSERT INTO schedule
VALUES(s_num_seq.nextval, '창립 기념 행사 기획', 'kim', '22/06/01', '22/06/05', 10);

INSERT INTO schedule
VALUES(s_num_seq.nextval, '인수 테스트 기간', 'han', '22/06/08', '22/06/15', 40);

INSERT INTO schedule
VALUES(s_num_seq.nextval, '신입사원 오리엔테이션', 'ahn', '22/06/09', '22/06/09', 30);

INSERT INTO board
(b_num,b_title,b_content,usrid,teamnum)
VALUES (b_num_seq.nextval,'손오공나가신다','거북이와두루미','abc',10); 

INSERT INTO board
(b_num,b_title,b_content,usrid,teamnum)
VALUES (b_num_seq.nextval,'길라임','슬라임과고양이','won', 30); 

INSERT INTO board
(b_num,b_title,b_content,usrid,teamnum)
VALUES (b_num_seq.nextval,'당신 누구야!','삼장법사','han',40); 

INSERT INTO board
(b_num,b_title,b_content,usrid,teamnum)
VALUES (b_num_seq.nextval,'거기서라!','제주돌하루방임','park',20); 

INSERT INTO reply
(r_num,b_num,r_content,usrid)
VALUES (r_num_seq.nextval,1,'꼴뚜기와메뚜기','son'); 

INSERT INTO reply
(r_num,b_num,r_content,usrid)
VALUES (r_num_seq.nextval,3,'사오정','won'); 

INSERT INTO reply
(r_num,b_num,r_content,usrid)
VALUES (r_num_seq.nextval,2,'고양이는강아지','jang'); 

INSERT INTO reply
(r_num,b_num,r_content,usrid)
VALUES (r_num_seq.nextval,4,'거울임','kim'); 

INSERT INTO blike
(l_num,b_num,usrid)
VALUES (l_num_seq.nextval,1,'son'); 

INSERT INTO blike
(l_num,b_num,usrid)
VALUES (l_num_seq.nextval,3,'won');

SELECT * FROM board;
SELECT * FROM blike;
SELECT * FROM reply;
SELECT * FROM schedule;
SELECT * FROM usr;
SELECT * FROM todo;
SELECT * FROM team;

commit;