package repository.supplier;

import entity.SupplierEntity;
import repository.CrudRepository;

import java.util.List;

public interface SupplierDao extends CrudRepository<SupplierEntity> {
	SupplierEntity findById(Long id);

	List<Long> getAllIds();

	List<SupplierEntity> findByEmail(String email);
}
