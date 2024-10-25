package repository.User.impl;

import entity.ProductEntity;
import entity.UserEntity;
import jakarta.persistence.NoResultException;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.User.UserDao;
import util.HibernateUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {
	@Override
	public boolean save(UserEntity entity) {

		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		session.persist(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean update(UserEntity entity) {
		return false;
	}

	@Override
	public boolean delete(Long entity) {
		return false;
	}

	@Override
	public List<UserEntity> findAll() {
		return null;
	}


	@Override
	public boolean updateByEmail(String newPassword , String email) {
		Session session = HibernateUtil.getSession();
		try {
			session.getTransaction().begin();

			String SQL = "UPDATE UserEntity SET password = :newPassword WHERE email = :email";
			Query query = session.createQuery(SQL)
					.setParameter("newPassword",newPassword)
					.setParameter("email",email);

			int result = query.executeUpdate();
			session.getTransaction().commit();

			return result>0;
		}finally {
			session.close();
		}
	}

	@Override
	public UserEntity findByEmail(String email){


		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<UserEntity> res = session.createQuery("FROM UserEntity WHERE email = :email", UserEntity.class)
				.setParameter("email", email);

		UserEntity user = res.getSingleResult();

		if(user != null){
			return user;
		}

		return null ;
	}
}
