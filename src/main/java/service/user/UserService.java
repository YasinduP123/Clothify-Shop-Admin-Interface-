package service.user;

import dto.User;
import jakarta.mail.MessagingException;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface UserService extends SuperService {
	boolean addUser(User user);

	ObservableList<User> getUsers();

	boolean updateUser(User user);

	boolean deleteUser(String id);

	User searchUser(String id);

	List<String> getUserIds();

	boolean sendEmail(String email) throws MessagingException;
	boolean updatePassword(String txtNewPassword,String email);

	User searchByEmail(String email);
}
