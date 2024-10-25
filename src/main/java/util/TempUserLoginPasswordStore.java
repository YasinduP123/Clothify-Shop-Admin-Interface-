package util;

import dto.User;

import java.util.ArrayList;
import java.util.List;

public class TempUserLoginPasswordStore {
	private static TempUserLoginPasswordStore instance;
	private static ArrayList<User> userDto;
	private TempUserLoginPasswordStore(){
		userDto = new ArrayList<>();
	}

	public static TempUserLoginPasswordStore getInstance(){
		return instance == null ? instance = new TempUserLoginPasswordStore() : instance;
	}

	public List<User> getConnection(){
		return userDto;
	}
}
