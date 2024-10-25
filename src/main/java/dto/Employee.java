package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private Long id;
	private String name;
	private String company;
	private String email;
	public Employee(String name, String company, String email) {
		this.name = name;
		this.company = company;
		this.email = email;
	}
}
