-- 5�� 27�� 42�� MyBatis �ָ� ����

DROP TABLE japanword;
DROP SEQUENCE japanword_seq;

CREATE TABLE japanword
(
 seq NUMBER PRIMARY KEY,
 word VARCHAR2(200) NOT NULL,
 mean VARCHAR2(300) NOT NULL,
 memorize CHAR(1)  DEFAULT 0 -- 0: �ϱ����, 1: �ϱ���
);
  
CREATE SEQUENCE japanword_seq;  
                        
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, '���Ϫ誦', '��ħ�λ�');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, '����˪���', '�����λ�');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, '����Ъ��', '�����λ�');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, '������', '�Ķ���');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, '������', '������');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, '���쪤��', '���ڴ�');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, '����', '������');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, '����', '����');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, '����', '����');
INSERT INTO japanword (seq, word, mean) VALUES (japanword_seq.nextval, '���٪�', '�Դ�');

SELECT * FROM japanword;