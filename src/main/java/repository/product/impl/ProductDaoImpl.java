package repository.product.impl;

import dto.OrderDetails;
import entity.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.product.ProductDao;
import util.CrudUtil;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

	@Override
	public boolean save(ProductEntity entity) {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		session.persist(entity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@Override
	public boolean update(ProductEntity entity) {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();

		Query<ProductEntity> rst = session.createQuery("UPDATE ProductEntity SET description = :description , size = :size , stock = :stock , price = :price , category = :category WHERE productId = :id", ProductEntity.class)
				.setParameter("id", entity.getProductId())
				.setParameter("description", entity.getDescription())
				.setParameter("size", entity.getSize())
				.setParameter("stock", entity.getStock())
				.setParameter("price", entity.getPrice())
				.setParameter("category", entity.getCategory());

		int res = rst.executeUpdate();

		session.getTransaction().commit();
		session.close();

		return res>0;
	}

	@Override
	public boolean delete(Long id) {

		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query res = session.createQuery("DELETE FROM ProductEntity WHERE productId = :id")
				.setParameter("id", id);

		int isDelete = res.executeUpdate();

		session.getTransaction().commit();
		session.close();
		return isDelete>0;
	}

	@Override
	public List<ProductEntity> findAll() {

		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<ProductEntity> query = session.createQuery("FROM ProductEntity", ProductEntity.class);
		List<ProductEntity> resultList = query.getResultList();
		session.getTransaction().commit();
		session.close();
		return resultList;
	}

	@Override
	public ProductEntity findById(Long id) {

		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<ProductEntity> query = session.createQuery("FROM ProductEntity WHERE productId = :id", ProductEntity.class)
				.setParameter("id", id);

		ProductEntity product = query.getSingleResult();

		session.getTransaction().commit();
		session.close();
		return product;
	}

	@Override
	public ObservableList<ProductEntity> findByCategory(String category) {
		ObservableList<ProductEntity> product = FXCollections.observableArrayList();
 		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<ProductEntity> query = session.createQuery("FROM ProductEntity WHERE category = :category", ProductEntity.class)
				.setParameter("category", category);

		List<ProductEntity> resultList = query.getResultList();

		product.addAll(resultList);

		session.getTransaction().commit();
		session.close();
		return product;
	}

	@Override
	public ObservableList<Long> findAllIds() {
		ObservableList<Long> productIds = FXCollections.observableArrayList();
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		Query<Long> query = session.createQuery("SELECT id FROM ProductEntity", Long.class);
		List<Long> resultList = query.getResultList();
		productIds.addAll(resultList);
		session.getTransaction().commit();
		session.close();
		System.out.println(productIds);
		return productIds;
	}

	@Override
	public boolean updateById(OrderDetails product) {
		System.out.println("IDU "+product);
		try {
			boolean execute = CrudUtil.execute("update Product set stock = stock - ? where productId = ?",
					product.getQty(),
					product.getProductId()
			);

			System.out.println("execute Update :"+execute);
			return execute;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
