package repository.supplier.impl;

import entity.SupplierEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.supplier.SupplierDao;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
	@Override
	public boolean save(SupplierEntity entity) {

		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		session.persist(entity);
		session.getTransaction().commit();
		session.close();

		return true;
	}

	@Override
	public boolean update(SupplierEntity entity) {
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
		Query query = session.createQuery("DELETE FROM SupplierEntity WHERE id = :id")
				.setParameter("id",id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public List<SupplierEntity> findAll() {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<SupplierEntity> fromSupplierEntity = session.createQuery("FROM SupplierEntity", SupplierEntity.class);
		List<SupplierEntity> resultList = fromSupplierEntity.getResultList();
		session.getTransaction().commit();
		session.close();
		return resultList;
	}

	@Override
	public SupplierEntity findById(Long id) {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<SupplierEntity> query = session.createQuery("FROM SupplierEntity WHERE id = :id ", SupplierEntity.class)
				.setParameter("id",id);
		SupplierEntity singleResult = query.getSingleResult();
		session.getTransaction().commit();
		session.close();
		return singleResult;
	}

	@Override
	public List<Long> getAllIds() {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<Long> query = session.createQuery("SELECT id FROM SupplierEntity", Long.class);
		List<Long> resultList = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return resultList;
	}

	@Override
	public List<SupplierEntity> findByEmail(String email) {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<SupplierEntity> query = session.createQuery("FROM SupplierEntity WHERE emailAddress = :email", SupplierEntity.class)
				.setParameter("email",email);
		List<SupplierEntity> resultList = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return resultList;
	}
}
