package service.user.impl;

import controller.ForgottenPassword;
import dto.User;
import entity.UserEntity;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.NoResultException;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.SuperDao;
import repository.User.UserDao;
import service.user.UserService;
import util.DaoType;
import util.GenarateOtp;
import util.TempOtpStore;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;



public class UserServiceImpl implements UserService {
	private String otp = GenarateOtp.genarateOtp()+"";
	ModelMapper mapper = new ModelMapper();

	@Override
	public boolean addUser(User user) {

		UserEntity userEntity = mapper.map(user, UserEntity.class);
		UserDao daoType = DaoFactory.getInstance().getDaoType(DaoType.USER);
		daoType.save(userEntity);
		return true;

	}

	@Override
	public ObservableList<User> getUsers() {
		return null;
	}

	@Override
	public boolean updateUser(User user) {

		return false;
	}

	@Override
	public boolean deleteUser(String id) {
		return false;
	}

	@Override
	public User searchUser(String id) {
		return null;
	}

	@Override
	public List<String> getUserIds() {
		return null;
	}


	public boolean sendEmail(String email) throws MessagingException {

		System.out.println(email);
		List<String> connection = TempOtpStore.getInstance().getConnection();

		connection.add(otp);

		System.out.println(otp);

		java.util.Properties properties = new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");

		String myEmail = "yasindupathiraja10@gmail.com";
		String password = "jizmejwjvtfooaqp";

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail,password);
			}
		});

		Message message = prepareMessage(session , myEmail ,email,otp);

		try {
			if (message != null){
				Transport.send(message);
				return true;
			}
		}catch (SendFailedException e){
			new Alert(Alert.AlertType.ERROR,"Invalid email address").show();
		}
		return false;
	}

	@Override
	public boolean updatePassword(String txtNewPassword,String email) {

		UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
		System.out.println("email1 "+email+" , "+"Password1 "+txtNewPassword);
		return userDao.updateByEmail(txtNewPassword,email);
	}

	@Override
	public User searchByEmail(String email){
		UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
		return mapper.map(userDao.findByEmail(email),User.class);
	}

	public Message prepareMessage(Session session, String myEmail, String email, String msg) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{
					new InternetAddress(email)
			});

			message.setSubject("Clothify OTP");
			message.setText(msg);

			return message;

		}catch (Exception e){
			Logger.getLogger(ForgottenPassword.class.getName()).log(Level.SEVERE,null,e);
		}
		return null;
	}



}
