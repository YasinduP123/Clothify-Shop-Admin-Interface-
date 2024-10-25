package util;

public class GenarateOtp {

	private static GenarateOtp instance;

	private GenarateOtp(){}

	public static GenarateOtp getInstance(){
		return instance == null ? instance = new GenarateOtp() : instance;
	}

	public static int genarateOtp(){
		int otp = (int) (Math.random()*1000000);
		return otp;
	}
}
