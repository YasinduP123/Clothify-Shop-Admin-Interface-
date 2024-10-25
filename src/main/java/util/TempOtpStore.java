package util;

import java.util.ArrayList;
import java.util.List;

public class TempOtpStore {
	private static TempOtpStore instance;
	private static ArrayList<String> otpList;
	private TempOtpStore(){
		otpList = new ArrayList<>();
	}

	public static TempOtpStore getInstance(){
		return instance == null ? instance = new TempOtpStore() : instance;
	}

	public List<String> getConnection(){
		return otpList;
	}
}
