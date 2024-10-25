package service.supplier.impl;

import dto.Supplier;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.supplier.SupplierDao;
import service.supplier.SupplierService;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {

	ModelMapper mapper = new ModelMapper();
	SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);

	@Override
	public boolean isSaveSupplier(Supplier supplier) {
		if (supplier != null){
			return supplierDao.save(mapper.map(supplier, SupplierEntity.class));
		}
		return false;
	}

	@Override
	public Supplier findById(Long id) {
		Supplier map = mapper.map(supplierDao.findById(id), Supplier.class);
		System.out.println("map "+map);
		return map;
	}

	@Override
	public List<Long> getAllIds() {
		return supplierDao.getAllIds();
	}

	@Override
	public ObservableList<Supplier> getAllSuppliers() {
		ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
		supplierDao.findAll().forEach(supplier -> suppliers.add(mapper.map(supplier,Supplier.class)));
		return suppliers;
	}

	@Override
	public boolean deleteById(Long id) {
		return supplierDao.delete(id);
	}

	@Override
	public List<Supplier> findByEmail(String email) {
		List<Supplier> suppliers = new ArrayList<>();
		supplierDao.findByEmail(email).forEach(supplierEntity -> suppliers.add(mapper.map(supplierEntity , Supplier.class)));
		return suppliers;
	}

	@Override
	public boolean isUpdateSupplier(Supplier supplier) {
		return supplierDao.update(mapper.map(supplier,SupplierEntity.class));
	}
}
