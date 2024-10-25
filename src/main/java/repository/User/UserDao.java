package repository.User;

import entity.UserEntity;
import jakarta.persistence.NoResultException;
import repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity> {
	boolean updateByEmail(String email,String newPassword);
	UserEntity findByEmail(String email);
}
