package classroom.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {

	private String name;
	private String address;

	public UserDto(String name, String address, LocalDate dob, String role) {
		super();
		this.name = name;
		this.address = address;
	}
}
