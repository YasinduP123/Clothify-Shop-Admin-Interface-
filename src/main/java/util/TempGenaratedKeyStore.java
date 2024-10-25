package util;

import java.util.ArrayList;
import java.util.List;

public class TempGenaratedKeyStore {
	private static TempGenaratedKeyStore instance;
	private Long id;
	private TempGenaratedKeyStore(){}

	public Long setId(Long id){
		return this.id = id;
	}
	public static TempGenaratedKeyStore getInstance(){
		return instance == null ? instance = new TempGenaratedKeyStore() : instance;
	}

	public Long getId(){
		return this.id;
	}
}
