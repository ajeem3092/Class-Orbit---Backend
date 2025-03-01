package classroom.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "userdetail")
public class User extends BaseEntity {

	@Column(length = 45)
	@NotBlank(message = "name is required")
	private String name;
	@Column(length = 45)
	@NotBlank(message = "address is required")
	private String address;
//	@JsonFormat(pattern = "DD-MM-YYYY")
	private LocalDate dob;
	@NotBlank(message = "password is required")
	private String password;
	@NotBlank(message = "email is required")
	private String email;
	@Column(name = "mobno")
	private String mobNo;
	@Enumerated(EnumType.STRING)
	private Role role;
	
}
