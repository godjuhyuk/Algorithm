WITH RECURSIVE A AS (
    
    SELECT 0 AS num
    UNION ALL
    SELECT num+1
    FROM A
    WHERE num <23

)

SELECT
    IFNULL(a.num, 0) AS HOUR,
    COUNT(ANIMAL_ID) AS COUNT
FROM ANIMAL_OUTS AO
RIGHT OUTER JOIN A A
ON HOUR(DATETIME) = a.num
GROUP BY a.num
HAVING COUNT(*) >= 0
ORDER BY 1 ASC