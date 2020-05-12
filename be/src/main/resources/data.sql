INSERT INTO user (id ,email, nickname, win, lose, draw)
VALUES (1, 'jay@codesquad.com', '제이', 0, 0, 0),
        (2, 'olaf@codesquad.com', '올라프', 0, 0, 0),
        (3, 'sally@codesquad.com', '샐리', 0, 0, 0),
        (4, 'lynn@codesquad.com', '린', 0, 0, 0);

INSERT INTO team (id, name, url, thumbnail_url, color)
VALUES (1, '두산', 'https://user-images.githubusercontent.com/33659848/81492104-4c596000-92d0-11ea-8aa9-267b0c971a11.png', 'https://user-images.githubusercontent.com/33659848/81492137-80348580-92d0-11ea-9334-ef4023bffc09.png', '#100F27'),
       (2, '한화', 'https://user-images.githubusercontent.com/33659848/81492105-4f545080-92d0-11ea-95f9-33d92afeac0f.png', 'https://user-images.githubusercontent.com/33659848/81492139-8165b280-92d0-11ea-8273-613e4025edef.png', '#FF6F31'),
       (3, '기아', 'https://user-images.githubusercontent.com/33659848/81492108-4fece700-92d0-11ea-876a-0e3bd652d421.png', 'https://user-images.githubusercontent.com/33659848/81492140-8296df80-92d0-11ea-9b8c-8f42c314ac99.png', '#992A32'),
       (4, '키움', 'https://user-images.githubusercontent.com/33659848/81492109-50857d80-92d0-11ea-8d04-ca12801b2515.png', 'https://user-images.githubusercontent.com/33659848/81492141-832f7600-92d0-11ea-91e7-553e2239ac3d.png', '#721725'),
       (5, 'KT', 'https://user-images.githubusercontent.com/33659848/81492110-511e1400-92d0-11ea-9534-86ec4611bbe4.png', 'https://user-images.githubusercontent.com/33659848/81492143-83c80c80-92d0-11ea-8c0f-007ebc2804fa.png', '#363536'),
       (6, 'LG', 'https://user-images.githubusercontent.com/33659848/81492111-51b6aa80-92d0-11ea-9bbf-2ec3708cd3dc.png', 'https://user-images.githubusercontent.com/33659848/81492144-8460a300-92d0-11ea-8287-13dcf105fe8d.png', '#B0253E'),
       (7, '롯데', 'https://user-images.githubusercontent.com/33659848/81492112-51b6aa80-92d0-11ea-9318-1f37f009b963.png', 'https://user-images.githubusercontent.com/33659848/81492146-8460a300-92d0-11ea-97ae-b51f25439d6c.png', '#0A254C'),
       (8, 'NC', 'https://user-images.githubusercontent.com/33659848/81492113-524f4100-92d0-11ea-88d2-4c3cc728aef7.png', 'https://user-images.githubusercontent.com/33659848/81492147-84f93980-92d0-11ea-8f04-15aabe520809.png', '#274578'),
       (9, '삼성', 'https://user-images.githubusercontent.com/33659848/81492114-52e7d780-92d0-11ea-9091-b0db161829bf.png', 'https://user-images.githubusercontent.com/33659848/81492149-84f93980-92d0-11ea-89a7-698f8d3ea5d4.png', '#2D6CAF'),
       (10, 'SK', 'https://user-images.githubusercontent.com/33659848/81492115-53806e00-92d0-11ea-8169-5d1a4e8f9dbf.png', 'https://user-images.githubusercontent.com/33659848/81492150-8591d000-92d0-11ea-9635-574f687c7cf7.png', '#E48A3D');

INSERT INTO player (name, is_pitcher, average, batting_order, team)
-- 두산
VALUES ('정수빈', false, 0.211, 1, 1), ('오재원', false, 0.237, 2, 1), ('김재호', false, 0.268, 3, 1), ('페르난데스', false, 0.344, 4, 1), ('박건우', false, 0.319, 5, 1),
        ('이흥련', false, 0.310, 6, 1), ('오재일', false, 0.293, 7, 1), ('박세혁', false, 0.279, 8, 1), ('신성현', false, 0.195, 9, 1), ('린드블럼', true, -1, -1, 1),
-- 한화
        ('노시환', false, 0.186, 1, 2), ('김인환', false, 0.214, 2, 2), ('강경학', false, 0.239, 3, 2), ('김태균', false, 0.305, 4, 2), ('정근우', false, 0.278, 5, 2),
        ('정은원', false, 0.262, 6, 2), ('이성열', false, 0.256, 7, 2), ('오선진', false, 0.224, 8, 2), ('양성우', false, 0.168, 9, 2), ('정우람', true, -1, -1, 2),
-- 기아
        ('나지완', false, 0.186, 1, 3), ('최원준', false, 0.198, 2, 3), ('김선빈', false, 0.292, 3, 3), ('최형우', false, 0.300, 4, 3), ('터커', false, 0.315, 5, 3),
        ('김주찬', false, 0.300, 6, 3), ('박찬호', false, 0.260, 7, 3), ('한승택', false, 0.223, 8, 3), ('김민식', false, 0.167, 9, 3), ('양현종', true, -1, -1, 3),
-- 키움
        ('박정음', false, 0.197, 1, 4), ('송성문', false, 0.227, 2, 4), ('김혜성', false, 0.276, 3, 4), ('이정후', false, 0.336, 4, 4), ('샌즈', false, 0.305, 5, 4),
        ('김하성', false, 0.307, 6, 4), ('장영석', false, 0.247, 7, 4), ('임병욱', false, 0.243, 8, 4), ('임지열', false, 0.197, 9, 4), ('오주원', true, -1, -1, 4),
-- 케이티
        ('박승욱', false, 0.233, 1, 5), ('오태곤', false, 0.231, 2, 5), ('송민섭', false, 0.302, 3, 5), ('강백호', false, 0.336, 4, 5), ('로하스', false, 0.322, 5, 5),
        ('황재균', false, 0.283, 6, 5), ('심우준', false, 0.279, 7, 5), ('장성우', false, 0.262, 8, 5), ('문상철', false, 0.200, 9, 5), ('김재윤', true, -1, -1, 5),
-- 엘지
        ('김용의', false, 0.218, 1, 6), ('오지환', false, 0.252, 2, 6), ('김현수', false, 0.304, 3, 6), ('채은성', false, 0.315, 4, 6), ('이천웅', false, 0.308, 5, 6),
        ('박용택', false, 0.282, 6, 6), ('유강남', false, 0.270, 7, 6), ('김민성', false, 0.107, 8, 6), ('정주현', false, 0.231, 9, 6), ('차우찬', true, -1, -1, 6),
-- 롯데
        ('민병헌', false, 0.304, 1, 7), ('전준우', false, 0.301, 2, 7), ('손아섭', false, 0.295, 3, 7), ('이대호', false, 0.285, 4, 7), ('안치홍', false, 0.315, 5, 7),
        ('정훈', false, 0.226, 6, 7), ('마차도', false, 0.240, 7, 7), ('한동희', false, 0.203, 8, 7), ('정보근', false, 0.125, 9, 7), ('스트레일리', true, -1, -1, 7),
-- 엔시
        ('박민우', false, 0.344, 1, 8), ('이명기', false, 0.293, 2, 8), ('나성범', false, 0.268, 3, 8), ('양의지', false, 0.354, 4, 8), ('이원재', false, 0.258, 5, 8),
        ('노진혁', false, 0.264, 6, 8), ('권희동', false, 0.256, 7, 8), ('김태진', false, 0.230, 8, 8), ('김성욱', false, 0.224, 9, 8), ('이재학', true, -1, -1, 8),
-- 삼성
        ('김헌곤', false, 0.297, 1, 9), ('구자욱', false, 0.267, 2, 9), ('살라디노', false, 0.273, 3, 9), ('이원석', false, 0.246, 4, 9), ('러프', false, 0.292, 5, 9),
        ('김동엽', false, 0.215, 6, 9), ('강민호', false, 0.234, 7, 9), ('박해민', false, 0.239, 8, 9), ('이성규', false, 0.256, 9, 9), ('백정현', true, -1, -1, 9),
-- 에스케이
        ('노수광', false, 0.250, 1, 10), ('고종욱', false, 0.323, 2, 10), ('최정', false, 0.292, 3, 10), ('로맥', false, 0.276, 4, 10), ('한동민', false, 0.265, 5, 10),
        ('나주환', false, 0.222, 6, 10), ('이재원', false, 0.268, 7, 10), ('김창평', false, 0.178, 8, 10), ('정현', false, 0.246, 9, 10), ('킹엄', true, -1, -1, 10);

INSERT INTO game (id, home_batting_order, away_batting_order, is_over)
VALUES (1, 1, 1, false),
        (2, 1, 1, false),
        (3, 1, 1, false),
        (4, 1, 1, true),
        (5, 1, 1, false);

INSERT INTO team_game (team, game, user, is_home)
VALUES (1, 1, 1, true), (2, 1, 2, false),
        (3, 2, null, true), (4, 2, null, false),
        (5, 3, null, true), (6, 3, null, false),
        (7, 4, 3, true), (8, 4, null, false),
        (9, 5, null, true), (10, 5, null, false);
