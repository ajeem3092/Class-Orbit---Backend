package classroom.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@Entity
@Table(name = "noticeboard")
public class NoticeBoard extends BaseEntity {

	private Date date;
	@NotBlank(message = "description is required")
	private String description;
	@ManyToOne
	@JoinColumn(name = "facultyid")
	private User faculty;
	@Column(name = "facultyname")
	private String facultyName;
	@Column(name = "modulename")
	private String moduleName;
	
	public Long getFaculty() {
		return faculty.getId();
	}

}
             