package classroom.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import classroom.custom_exception.ResourceNotFoundException;
import classroom.entities.Role;
import classroom.entities.User;
import classroom.repository.IUserRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	IUserRepository userRepo;

	@Override
	public User addFaculty(User u) {
		u.setRole(classroom.entities.Role.ROLE_FACULTY);
		return userRepo.save(u);
	}

	@Override
	public List<User> getAllFaculty() {

		return userRepo.findByRole(Role.ROLE_FACULTY);
	}

	@Override
	public User addStudent(User u) {
		u.setRole(Role.ROLE_STUDENT);
		return userRepo.save(u);
	}

	@Override
	public User getFacultyById(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Faculty ID !!!!!!!"));
	}

	@Override
	public User updateFacultyDetails(User detachedFaculty, Long id) {
		User u = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Faculty ID !!!!!!!"));
		u.setAddress(detachedFaculty.getAddress());
		u.setDob(detachedFaculty.getDob());
		u.setEmail(detachedFaculty.getEmail());
		u.setName(detachedFaculty.getName());
		u.setMobNo(detachedFaculty.getMobNo());
		userRepo.save(u);
		return u;
	}

	@Override
	public String deleteFaculty(Long id) {
		User u = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Faculty ID !!!!!!!"));
		String msg;
		userRepo.delete(u);
		msg = "Faculty Deleted Succesfully";
		return msg;
	}

	@Override
	public List<User> getAllStudent() {
		return userRepo.findByRole(Role.ROLE_STUDENT);
	}

	@Override
	public User getStudentById(Long id) {

		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Student ID !!!!!!!"));
	}

	@Override
	public User updateStudentDetails(User detachedStudent, Long id) {
		User u = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Student ID !!!!!!!"));
		u.setAddress(detachedStudent.getAddress());
		u.setDob(detachedStudent.getDob());
		u.setEmail(detachedStudent.getEmail());
		u.setName(detachedStudent.getName());
		u.setMobNo(detachedStudent.getMobNo());
		userRepo.save(u);
		return u;
	}

	@Override
	public String deleteStudent(Long id) {
		User u = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Student ID !!!!!!!"));
		String msg;
		userRepo.delete(u);
		msg = "Student Deleted Succesfully";
		return msg;
	}
}