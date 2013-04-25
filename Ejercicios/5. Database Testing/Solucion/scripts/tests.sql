create or replace
PACKAGE ut_te_employee
IS
   PROCEDURE ut_setup;
   PROCEDURE ut_teardown;
 
   PROCEDURE ut_ADD;
   PROCEDURE ut_FIND;
   PROCEDURE ut_REMOVE;
END ut_te_employee;

create or replace
PACKAGE BODY ut_te_employee
IS
   PROCEDURE ut_setup
   IS
   BEGIN
      --EXECUTE IMMEDIATE 'CREATE TABLE expected_findemployee AS SELECT * FROM employee';
      null;
   END;
   
   PROCEDURE ut_teardown
   IS
   BEGIN
       -- EXECUTE IMMEDIATE 'DROP TABLE expected_findemployee';
        --EXECUTE IMMEDIATE 'DROP TABLE actual_findemployee';
        rollback;
   END;
   
   PROCEDURE ut_ADD
   IS
   BEGIN
      
      TE_EMPLOYEE.ADD (
         P_ID => 4,
         P_LASTNAME => 'lastname',
         P_HIREDATE => TO_DATE('20121210','yyyymmdd')
       );
       
      utAssert.eqqueryvalue('Check num employees','select count(1) from employee',1); 
      
      rollback;
   END ut_ADD;

   PROCEDURE ut_FIND
   IS
    c_employee te_employee.cursor_employee;
    v_employeenum number;
    v_id number;
    v_name varchar2(200);
    v_date date;
    
   BEGIN
            
            
      --EXECUTE IMMEDIATE 'CREATE TABLE actual_findemployee AS SELECT * FROM employee';
      insert into employee values(1,'pacheco',null);
      insert into employee values(2,'gutierrez',null);
      insert into employee values(3,'valencia',null);  
      
      --EXECUTE IMMEDIATE 'CREATE TABLE expected_findemployee AS SELECT * FROM employee';
      
      TE_EMPLOYEE.FIND (
         P_LASTNAME => null,
         P_BEGINDATE => null,
         P_ENDDATE => null,
         C_EMPLOYEE => c_employee 
       );
       
       v_employeenum:=0;
       LOOP
        FETCH c_employee INTO v_id, v_name, v_date;
        EXIT WHEN c_employee%NOTFOUND;
          v_employeenum:=v_employeenum+1;
         -- EXECUTE IMMEDIATE 'insert into actual_findemployee values(:v_id,:v_name,:v_date)' USING v_id,v_name,v_date;
        END LOOP;

      --utassert.eqtable ('Delete rows', 'actual_findemployee', 'expected_findemployee');
      utAssert.eq('check num of employee',v_employeenum,3);
      
      rollback;
   END ut_FIND;

   PROCEDURE ut_REMOVE
   IS
   BEGIN    
       insert into employee values(1,'pacheco',null);
   
      TE_EMPLOYEE.REMOVE (P_ID => 1);
 
      utAssert.eqqueryvalue('Check num employees','select count(1) from employee',0); 
       rollback;
   END ut_REMOVE;

END ut_te_employee;


begin
utplsql.test('te_employee');
end;