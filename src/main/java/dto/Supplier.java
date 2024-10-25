package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
	private Long id;
	private String name;
	private String company;
	private String item;
	private String emailAddress;

	public Supplier(String name, String company, String item, String emailAddress) {
		this.name = name;
		this.company = company;
		this.item = item;
		this.emailAddress = emailAddress;
	}


}
