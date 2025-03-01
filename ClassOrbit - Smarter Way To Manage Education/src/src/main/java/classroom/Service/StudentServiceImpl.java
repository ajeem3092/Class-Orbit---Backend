package classroom.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	ILoginService loginservice;

	@Autowired
	private IAssignmentAnswerRepository assignRepo;

	@Autowired
	private IAssignmentRepository assignmentRepo;

	@Autowired
	INoticeboardRepository noticeRepo;

	@Autowired
	ITimetableRepository timeRepo;

	@Value("${file.upload.location}")
	private String folderLocation;

	public User authenticateUser(String mail, String password) {
		return loginservice.authenticateUser(mail, password);
	}

	public AssignmentAnswer uploadAssignment(AssignmentAnswer answer) {

		return assignRepo.save(answer);
	}

	public List<Assignment> getAllAssignment() {

		return assignmentRepo.findAll();
	}

	public List<NoticeBoard> getAllNoticeBoard() {
		return noticeRepo.findAll();

	}

	public List<TimeTable> getAllTimeTable() {
		return timeRepo.findAll();
	}

	public List<User> getAllFacultyByRoleFaculty() {

		return userRepo.findByRole(Role.ROLE_FACULTY);
	}

	public AssignmentAnswer saveAssignmentAnswerFile(Long assignId, Long studentId, String fileName)
			throws IOException {

		User student = userRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Invalid Student ID : Can't save file!!!!!!!"));

		Assignment assignment = assignmentRepo.findById(assignId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Assignment ID : Can't save file!!!!!!!"));

		User faculty = userRepo.findById(assignment.getFaculty())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Faculty ID : Can't save file!!!!!!!"));

		AssignmentAnswer aa = new AssignmentAnswer();
		aa.setAssignmentId(assignment);
		aa.setStudent(student);	
		aa.setFileName(fileName);
		aa.setFaculty(faculty);
		aa.setModuleName(assignment.getModuleName());
		aa.setStudentName(student.getName());
		assignRepo.save(aa);
		return aa;
	}

	public List<AssignmentAnswer> getAllAssignmentByStudentIdWithGrade(Long studentId) {
		User student = userRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Student ID!!"));

		return assignRepo.findByStudent(student);
	}
}
