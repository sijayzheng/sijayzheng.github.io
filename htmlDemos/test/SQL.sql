WITH CTE(ID, PID, TYPE, NAMES, ORDERS, ICON) AS (SELECT ID, SUPER_DEPARTMENT_ID AS SUPERID, '0' AS TYPE, FULL_NAME AS NAMES, ORDERS, ''
                                                 FROM (SELECT ID, SUPER_DEPARTMENT_ID, FULL_NAME, ORDERS
                                                       FROM PBAC_DEPARTMENT A,
                                                            PBAC_DEPARTMENT_RELATION B
                                                       WHERE A.ID = B.DEPARTMENT_ID)
                                                 WHERE ID IN (SELECT DEPARTMENTID FROM INSTANCE)
                                                 UNION ALL
                                                 SELECT A.ID, A.SUPERID, '0' AS TYPE, A.NAMES, A.ORDERS, ''
                                                 FROM BUSSINESSSYSTEM AS A,
                                                      CTE
                                                 WHERE CTE.PID = A.ID)
SELECT DISTINCT*
FROM CTE
UNION ALL
SELECT A.ID, A.DEPARTMENTID, '1', A.NAMES, A.ORDERS, B.ICON
FROM INSTANCE A,
     MODEL B
WHERE A.MODELID = B.ID
UNION ALL
SELECT ID, INSTANCEID, '2', NAMES, ORDERS, ''
FROM ITEM