package diapositivas.dbtesting;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class EmployeeTest {
	
	EmployeeADO employeeADO=new EmployeeADO();
	
	@Test
	public void testName() {
		
		
		
		
		Employee employee = new Employee("Luis");
		LoadData(employee);
		
		
		List<Employee> employees=this.employeeADO.Find("Luis");
		
		
		assertEquals(1,employees.size());
		
		
		RemoveData(employee);
		
	}

	private void RemoveData(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	private void LoadData(Employee employee) {

	}
}
