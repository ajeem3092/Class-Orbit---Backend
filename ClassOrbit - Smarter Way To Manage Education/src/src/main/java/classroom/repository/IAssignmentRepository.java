package classroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import classroom.entities.Assignment;
import classroom.entities.User;
@Repository
public interface IAssignmentRepository extends JpaRepository<Assignment, Long> {

	public List<Assignment> findByFaculty(User u);

}
	