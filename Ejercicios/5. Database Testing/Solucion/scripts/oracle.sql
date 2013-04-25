CREATE TABLE employee(
	Id number primary key NOT NULL,
	LastName varchar2(50) NULL,
	HireDate date NULL
);
/
create or replace
package te_employee
is
   TYPE cursor_employee IS REF CURSOR;
   
   procedure find(p_lastname in varchar2,p_begindate in date,p_enddate in date, c_employee OUT cursor_employee);
   procedure add(p_id in number,p_lastname in varchar2,p_hiredate in date);
   procedure remove (p_id in number);
end;
/
create or replace
package body te_employee
IS
   procedure find(p_lastname in varchar2,p_begindate in date,p_enddate in date, c_employee OUT cursor_employee)
   is
   begin
      open c_employee FOR
      select id,lastname,hiredate from employee;
   end;
   
   procedure add (p_id in number,p_lastname in varchar2,p_hiredate in date)
   is
   begin
      insert into employee values(p_id,p_lastname,p_hiredate);
   end;
   
   procedure remove (p_id in number)
   is
   begin
      delete from employee where id=p_id;
   end;
END;
/