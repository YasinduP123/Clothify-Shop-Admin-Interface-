package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	private Long userId;
	private String email;
	private String password;
	private String role;
	public User(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User(Long userId) {
	}
}
