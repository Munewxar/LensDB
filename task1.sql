SELECT DISTINCT a.c_id 
FROM A a 
WHERE NOT EXISTS(
	SELECT * FROM A a2 WHERE a2.c_id = a.c_id AND a2.amt < 0
);
