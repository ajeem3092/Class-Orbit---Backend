package classroom.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CredentialDto {

	@NotBlank(message = "email is required")
	private String email;
	@NotBlank(message = "password is required")
	private String password;

	public CredentialDto(String mail, String password) {
		this.email = mail;
		this.password = password;
	}
}
