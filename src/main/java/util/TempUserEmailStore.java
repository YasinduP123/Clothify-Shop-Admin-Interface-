package util;

import java.util.ArrayList;
import java.util.List;

public class TempUserEmailStore {
	private static TempUserEmailStore instance;
	private static ArrayList<String> email;
	private TempUserEmailStore(){
		email = new ArrayList<>();
	}

	public static TempUserEmailStore getInstance(){
		return instance == null ? instance = new TempUserEmailStore() : instance;
	}

	public List<String> getConnection(){
		return email;
	}
}
