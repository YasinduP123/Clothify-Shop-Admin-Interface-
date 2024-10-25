package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Supplier")
public class SupplierEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String company;
	private String item;

	@Column(unique = true)
	private String emailAddress;

	public SupplierEntity(String name, String company, String item, String emailAddress) {
		this.name = name;
		this.company = company;
		this.item = item;
		this.emailAddress = emailAddress;
	}

}
