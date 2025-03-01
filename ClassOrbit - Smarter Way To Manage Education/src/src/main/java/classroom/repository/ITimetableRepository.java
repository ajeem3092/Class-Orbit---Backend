package classroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import classroom.entities.TimeTable;
import classroom.entities.User;

@Repository
public interface ITimetableRepository extends JpaRepository<TimeTable, Long> {

	public List<TimeTable> findByFaculty(User u);

}
