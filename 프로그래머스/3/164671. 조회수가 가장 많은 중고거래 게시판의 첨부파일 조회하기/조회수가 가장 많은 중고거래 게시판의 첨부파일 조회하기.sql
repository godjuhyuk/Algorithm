-- 코드를 입력하세요
SELECT
    CONCAT("/home/grep/src/", A.BOARD_ID, "/", FILE_ID, FILE_NAME, FILE_EXT) AS FILE_PATH
FROM USED_GOODS_FILE A
INNER JOIN USED_GOODS_BOARD B USING (BOARD_ID)
WHERE B.VIEWS = (SELECT MAX(VIEWS) FROM USED_GOODS_BOARD)
ORDER BY A.FILE_ID DESC