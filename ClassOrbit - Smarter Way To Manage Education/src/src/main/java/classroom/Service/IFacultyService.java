package classroom.Service;

import java.util.List;

import classroom.entities.Assignment;
import classroom.entities.AssignmentAnswer;
import classroom.entities.NoticeBoard;
import classroom.entities.TimeTable;
import classroom.entities.User;
import jakarta.validation.Valid;

public interface IFacultyService {

	public Assignment addAssignment(Assignment assignment, Long facultyId, String filecode);

	public List<Assignment> getAllAssignment();

	public List<Assignment> getAssignmentByFaculty(Long facultyId);

	public NoticeBoard addNoticeBoard(NoticeBoard notice, Long facultyId);

	public List<NoticeBoard> getAllNoticeBoard();

	public List<User> getAllStudentByRoleStudent();

	public List<TimeTable> getAllTimeTable();

	public List<NoticeBoard> getNoticeBoardByFaculty(Long facultyId);

	public List<TimeTable> getAllTimeTableByFacultyId(Long id);

	public TimeTable addTimeTable(@Valid TimeTable timetable, Long facultyId);

	public String deleteNoticeBoardById(Long id);

	public NoticeBoard getNoticeBoardById(Long id);

	public NoticeBoard updateNoticeBoardDetails(NoticeBoard detachedNoticeBoard, Long id);

	public String deleteTimeTableById(Long id);

	public TimeTable getTimeTableById(Long id);

	public TimeTable updateTimeTableDetails(TimeTable detachedTimeTable, Long id);

	public List<AssignmentAnswer> getAllAssignmentAnswerByFacultyId(Long facultyId);

	public AssignmentAnswer updateStudentGradeByAssignmentAnswerId(String grade, Long id);

	public AssignmentAnswer updateStudentRemarkByAssignmentAnswerId(String remark, Long id);
}