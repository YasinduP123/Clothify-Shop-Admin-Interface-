package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import util.RoleTypes;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "User")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoleTypes role;

}
