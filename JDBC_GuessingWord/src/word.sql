-- 5월 27일 42기 MyBatis 주말 과제

DROP TABLE japanword;
DROP SEQUENCE japanword_seq;

CREATE TABLE japanword
(
 seq NUMBER PRIMARY KEY,
 word VARCHAR2(200) NOT NULL,
 mean VARCHAR2(300) NOT NULL,
 memorize CHAR(1)  DEFAULT 0 -- 0: 암기못함, 1: 암기함
);
  
CREATE SEQUENCE japanword_seq;  
                        
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, 'おはよう', '아침인사');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, 'こんにちは', '점심인사');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, 'こんばんは', '저녁인사');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, 'あおい', '파랗다');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, 'あかい', '빨갛다');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, 'きれいだ', '예쁘다');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, 'あう', '만나다');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, 'くる', '오다');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, 'いく', '가다');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, 'たべる', '먹다');

SELECT * FROM japanword;