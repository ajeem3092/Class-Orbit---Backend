package classroom.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "assignmentanswer")
public class AssignmentAnswer extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "assignmentid")
	private Assignment assignmentId;
	@ManyToOne
	@JoinColumn(name = "facultyid")
	private User faculty;
	@NotBlank(message = "file is required")
	@Column(name = "filename")
	private String fileName;
	@Column(length = 2)
	private String grade;
	@Column(length = 50)
	private String remark;
	@Column(name = "modulename")
	private String moduleName;
	@ManyToOne
	@JoinColumn(name = "studentid")
	private User student;
	@Column(name = "studentname")
	private String studentName;

	public Long getFacultyId() {
		return faculty.getId();
	}

	public String getFacultyName() {
		return faculty.getName();
	}

	public Long getStudentId() {
		return student.getId();
	}

}
