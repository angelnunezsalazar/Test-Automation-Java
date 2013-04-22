package testautomation.databasetesting.hibernate;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import testautomation.databasetesting.Employee;


@Repository
public class EmployeeHibernate {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Employee> find(String lastName, Date startHireDate, Date endHireDate) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		if (lastName != null) {
			criteria.add(Restrictions.like("lastName", lastName));
		}
		if (startHireDate != null && endHireDate != null) {
			criteria.add(Restrictions.between("hireDate", startHireDate, endHireDate));
		}
		return criteria.list();
	}

	public Employee get(int id) {
		return (Employee) getSession().get(Employee.class, id);
	}

	public void create(Employee employee) {
		getSession().save(employee);
	}

	public void delete(int id) {
		Employee employee = (Employee) getSession().load(Employee.class, id);
		getSession().delete(employee);
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
