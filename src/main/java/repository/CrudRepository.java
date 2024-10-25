package repository;

import entity.ProductEntity;

import java.util.List;

public interface CrudRepository<T> extends SuperDao{
	boolean save(T entity);
	boolean update(T entity);
	boolean delete(Long entity);
	List<T> findAll();
}
