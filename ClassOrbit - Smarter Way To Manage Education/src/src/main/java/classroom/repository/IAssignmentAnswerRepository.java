package classroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import classroom.entities.AssignmentAnswer;
import classroom.entities.User;
@Repository
public interface IAssignmentAnswerRepository extends JpaRepository<AssignmentAnswer, Long> {

	List<AssignmentAnswer> findByFaculty(User u);

	List<AssignmentAnswer> findByStudent(User student);

}
