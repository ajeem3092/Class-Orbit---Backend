package classroom.dto;

import classroom.entities.Assignment;
import classroom.entities.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AssignmentAnswerDto {

	private Assignment assignmentId;

	private String grade;

	private String remark;

	private User student;

	private String studentName;

	private String description;

	private User faculty;

	private String fileName;

	private String moduleName;

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
