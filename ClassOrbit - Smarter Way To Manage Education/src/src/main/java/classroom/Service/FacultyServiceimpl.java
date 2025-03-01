package classroom.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import classroom.custom_exception.ResourceNotFoundException;
import classroom.entities.Assignment;
import classroom.entities.AssignmentAnswer;
import classroom.entities.NoticeBoard;
import classroom.entities.Role;
import classroom.entities.TimeTable;
import classroom.entities.User;
import classroom.repository.IAssignmentAnswerRepository;
import classroom.repository.IAssignmentRepository;
import classroom.repository.INoticeboardRepository;
import classroom.repository.ITimetableRepository;
import classroom.repository.IUserRepository;
import jakarta.validation.Valid;

@Service
public class FacultyServiceimpl implements IFacultyService {

	@Autowired
	private INoticeboardRepository noticeRepo;

	@Autowired
	private IAssignmentRepository assignRepo;

	@Autowired
	private IUserRepository userRepo;

	@Autowired
	private IAssignmentAnswerRepository answerRepo;

	@Autowired
	private ITimetableRepository timetableRepo;

	@Override
	public Assignment addAssignment(Assignment assignment, Long facultyId, String filecode) {
		User u = userRepo.findById(facultyId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Faculty ID !!!!!!!"));
		assignment.setFaculty(u);
		assignment.setFileName(filecode);
		return assignRepo.save(assignment);
	}

	@Override
	public List<Assignment> getAllAssignment() {
		return assignRepo.findAll();

	}

	@Override
	public List<Assignment> getAssignmentByFaculty(Long facultyId) {
		User u = userRepo.findById(facultyId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Faculty ID !!!!!!!"));
		return assignRepo.findByFaculty(u);
	}

	@Override
	public NoticeBoard addNoticeBoard(NoticeBoard notice, Long facultyId) {
		User u = userRepo.findById(facultyId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Faculty ID !!!!!!!"));
		notice.setFaculty(u);
		return noticeRepo.save(notice);
	}

	@Override
	public List<NoticeBoard> getAllNoticeBoard() {

		return noticeRepo.findAll();
	}

	@Override
	public List<TimeTable> getAllTimeTableByFacultyId(Long facultyId) {
		User u = userRepo.findById(facultyId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Faculty ID !!!!!!!"));
		return timetableRepo.findByFaculty(u);
	}

	@Override
	public List<TimeTable> getAllTimeTable() {
		return timetableRepo.findAll();
	}

	@Override
	public List<User> getAllStudentByRoleStudent() {
		return userRepo.findByRole(Role.ROLE_STUDENT);
	}

	@Override
	public List<NoticeBoard> getNoticeBoardByFaculty(Long facultyId) {
		User u = userRepo.findById(facultyId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Faculty ID !!!!!!!"));
		return noticeRepo.findByFaculty(u);
	}

	@Override
	public TimeTable addTimeTable(@Valid TimeTable timetable, Long facultyId) {
		User u = userRepo.findById(facultyId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Faculty ID !!!!!!!"));
		timetable.setFaculty(u);
		return timetableRepo.save(timetable);
	}

	@Override
	public String deleteNoticeBoardById(Long id) {
		NoticeBoard notice = noticeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid NoticeBoard ID !!!!!!!"));
		noticeRepo.delete(notice);

		return "Noticeboard Deleted Succesfully ";
	}

	@Override
	public NoticeBoard getNoticeBoardById(Long id) {
		NoticeBoard notice = noticeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid NoticeBoard ID !!!!!!!"));
		return notice;
	}

	@Override
	public NoticeBoard updateNoticeBoardDetails(NoticeBoard detachedNoticeBoard, Long id) {
		NoticeBoard notice = noticeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid NoticeBoard ID !!!!!!!"));
		notice.setModuleName(detachedNoticeBoard.getModuleName());
		notice.setDate(detachedNoticeBoard.getDate());
		notice.setDescription(detachedNoticeBoard.getDescription());
		noticeRepo.save(notice);
		return notice;
	}

	@Override
	public String deleteTimeTableById(Long id) {

		TimeTable timetable = timetableRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid TimeTable ID !!!!!!!"));
		timetableRepo.delete(timetable);
		return "TimeTable Deleted Succesfully ";
	}

	@Override
	public TimeTable getTimeTableById(Long id) {
		TimeTable timetable = timetableRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid TimeTable ID !!!!!!!"));
		return timetable;
	}

	@Override
	public TimeTable updateTimeTableDetails(TimeTable detachedTimeTable, Long id) {

		TimeTable timetable = timetableRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid TimeTable ID !!!!!!!"));

		timetable.setModuleName(detachedTimeTable.getModuleName());
		timetable.setDate(detachedTimeTable.getDate());
		timetable.setStartTime(detachedTimeTable.getStartTime());
		timetable.setEndTime(detachedTimeTable.getEndTime());
		timetable.setPlatform(detachedTimeTable.getPlatform());
		timetable.setLink(detachedTimeTable.getLink());
		timetableRepo.save(timetable);
		return timetable;

	}

	@Override
	public List<AssignmentAnswer> getAllAssignmentAnswerByFacultyId(Long facultyId) {

		User u = userRepo.findById(facultyId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid TimeTable ID !!!!!!!"));

		List<AssignmentAnswer> aa = answerRepo.findByFaculty(u);

		return aa;
	}

	@Override
	public AssignmentAnswer updateStudentGradeByAssignmentAnswerId(String grade, Long id) {
		AssignmentAnswer aa = answerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid AssignmentAnswer  ID !!!!!!!"));
		aa.setGrade(grade);
		answerRepo.save(aa);
		return aa;
	}

	@Override
	public AssignmentAnswer updateStudentRemarkByAssignmentAnswerId(String remark, Long id) {
		AssignmentAnswer aa = answerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid AssignmentAnswer  ID !!!!!!!"));
		aa.setRemark(remark);
		answerRepo.save(aa);
		return aa;
	}

}

