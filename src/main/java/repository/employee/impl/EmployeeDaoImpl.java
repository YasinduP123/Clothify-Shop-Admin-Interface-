package repository.employee.impl;

import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.employee.EmployeeDao;
import util.HibernateUtil;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
	@Override
	public boolean save(EmployeeEntity entity) {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		session.persist(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean update(EmployeeEntity entity) {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		session.update(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean delete(Long id) {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query id1 = session.createQuery("DELETE FROM EmployeeEntity WHERE id = :id")
				.setParameter("id", id);
		boolean isDeleted = id1.executeUpdate() > 0;
		session.getTransaction().commit();
		session.close();
		return isDeleted;
	}

	@Override
	public List<EmployeeEntity> findAll() {
		ObservableList<EmployeeEntity> employeeEntities = FXCollections.observableArrayList();
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<EmployeeEntity> fromEmployeeEntity = session.createQuery("FROM EmployeeEntity", EmployeeEntity.class);
		List<EmployeeEntity> resultList = fromEmployeeEntity.getResultList();
		employeeEntities.addAll(resultList);
		session.getTransaction().commit();
		session.close();
		return employeeEntities;
	}

	@Override
	public ObservableList<Long> getEmpIds() {
		ObservableList<Long> empIds = FXCollections.observableArrayList();
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query fromEmployeeEntity = session.createQuery("SELECT id FROM EmployeeEntity");
		List<Long> resultList = fromEmployeeEntity.getResultList();
		empIds.addAll(resultList);
		session.getTransaction().commit();
		session.close();
		return empIds;
	}

	@Override
	public EmployeeEntity findById(Long id) {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<EmployeeEntity> fromEmployeeEntity = session.createQuery("FROM EmployeeEntity WHERE id = :id", EmployeeEntity.class)
				.setParameter("id",id);
		EmployeeEntity employeeEntity = fromEmployeeEntity.getSingleResult();
		session.getTransaction().commit();
		session.close();
		return employeeEntity;
	}

	@Override
	public ObservableList<EmployeeEntity> findByEmail(String email) {
		ObservableList<EmployeeEntity> employeeEntities = FXCollections.observableArrayList();
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<EmployeeEntity> fromEmployeeEntity = session.createQuery("FROM EmployeeEntity WHERE email = :email", EmployeeEntity.class)
				.setParameter("email",email);
		employeeEntities.addAll(fromEmployeeEntity.getResultList());
		session.getTransaction().commit();
		session.close();
		return employeeEntities;
	}
}
