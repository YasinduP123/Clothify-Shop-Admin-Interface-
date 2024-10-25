package service.supplier;

import dto.Supplier;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {
	boolean isSaveSupplier(Supplier supplier);
	Supplier findById(Long id);

	List<Long> getAllIds();

	ObservableList<Supplier> getAllSuppliers();

	boolean deleteById(Long id);

	List<Supplier> findByEmail(String email);

	boolean isUpdateSupplier(Supplier supplier);
}
