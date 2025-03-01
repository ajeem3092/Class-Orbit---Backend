package classroom.Service;

import java.io.IOException;
import java.util.List;

import classroom.entities.Assignment;
import classroom.entities.AssignmentAnswer;
import classroom.entities.NoticeBoard;
import classroom.entities.TimeTable;
import classroom.entities.User;

public interface IStudentService {

	User authenticateUser(String email, String password);

	public AssignmentAnswer uploadAssignment(AssignmentAnswer answer);

	public AssignmentAnswer saveAssignmentAnswerFile(Long assignId, Long studentId, String fileName) throws IOException;

	public List<Assignment> getAllAssignment();

	List<NoticeBoard> getAllNoticeBoard();

	List<TimeTable> getAllTimeTable();

	List<User> getAllFacultyByRoleFaculty();

	List<AssignmentAnswer> getAllAssignmentByStudentIdWithGrade(Long studentId);

}
