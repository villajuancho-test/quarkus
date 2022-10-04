CREATE TABLE PRUEBA (
    ID NUMBER,
    NOMBRE VARCHAR2(100)
);

INSERT INTO PRUEBA (ID, NOMBRE)
VALUES (1,'JUAN');

INSERT INTO PRUEBA (ID, NOMBRE)
VALUES (2,'JOSE');

SELECT * FROM PRUEBA;

    


CREATE OR REPLACE PROCEDURE SHOW_SUPPLIERS (p_name IN VARCHAR2, cursor1 OUT SYS_REFCURSOR) 
AS
BEGIN
dbms_output.put_line('INFO: ' || p_name);
OPEN cursor1 FOR SELECT ID, NOMBRE FROM SYSTEM.PRUEBA;
END;

{CALL SYSTEM.SHOW_SUPPLIERS('hola',?)};

CREATE OR REPLACE PROCEDURE SHOW_SUPPLIERS2 (p_name IN VARCHAR2, salida OUT VARCHAR2) 
AS
BEGIN
salida:= p_name;
dbms_output.put_line('INFO_02: ' || p_name);
END;

CREATE OR REPLACE PROCEDURE SHOW_SUPPLIERS3 (p_name IN VARCHAR2) 
AS
BEGIN
dbms_output.put_line('INFO_03: ' || p_name);
END;

CALL SYSTEM.SHOW_SUPPLIERS3('hola3');


CREATE OR REPLACE PACKAGE TYPES AS
type ref_cursor is ref cursor;
END;

create or replace procedure SHOW_SUPPLIERS4() AS
q SYS_REFCURSOR;
 BEGIN
    OPEN q FOR select * from PRUEBA;
     DBMS_SQL.return_result (q); -- This will display the result
END;

CALL SYSTEM.SHOW_SUPPLIERS5(2);

create or replace procedure SHOW_SUPPLIERS5(empId IN VARCHAR2) AS
q SYS_REFCURSOR;
 BEGIN
	 dbms_output.put_line('INFO_05: ' || empId);
    OPEN q FOR select * from PRUEBA;
     DBMS_SQL.return_result (q); -- This will display the result
END;


BEGIN 
	SHOW_SUPPLIERS5('ss2');
END;