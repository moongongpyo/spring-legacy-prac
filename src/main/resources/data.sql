INSERT INTO article(id,title,content,username) VALUES (1,'가가가가','1111','최민성');
INSERT INTO article(id,title,content,username) VALUES (2,'나나나나','2222','최민성');
INSERT INTO article(id,title,content,username) VALUES (3,'다다다다','3333','최민성');

---------article 더미 데이터
INSERT INTO article(id,title,content,username) VALUES (4,'당신의 인생 영화는?','댓글 ㄱ','최민성');
INSERT INTO article(id,title,content,username) VALUES (5,'당신의 소울 푸드는?','댓글 ㄱㄱ','최민성');
INSERT INTO article(id,title,content,username) VALUES (6,'당신의 취미는?','댓글 ㄱㄱㄱ','최민성');

--comment 더미 데이터
--4번 게시글의 댓글들
INSERT INTO comment(id,article_id,body,username) VALUES (1,4,'굳 윌 헌팅','최민성');
INSERT INTO comment(id,article_id,body,username) VALUES (2,4,'아이 엠 샘','최민성');
INSERT INTO comment(id,article_id,body,username) VALUES (3,4,'쇼생크의 탈출','최민성');
--5번 게시글의 댓글들
INSERT INTO comment(id,article_id,body,username) VALUES (4,5,'치킨','최민성');
INSERT INTO comment(id,article_id,body,username) VALUES (5,5,'샤브샤브','최민성');
INSERT INTO comment(id,article_id,body,username) VALUES (6,5,'초밥','최민성');
--6번 게시글의 댓글들
INSERT INTO comment(id,article_id,body,username) VALUES (7,6,'조깅','최민성');
INSERT INTO comment(id,article_id,body,username) VALUES (8,6,'유튜브','최민성');
INSERT INTO comment(id,article_id,body,username) VALUES (9,6,'독서','최민성');