package classroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import classroom.entities.NoticeBoard;
import classroom.entities.User;
@Repository
public interface INoticeboardRepository extends JpaRepository<NoticeBoard, Long> {

	public List<NoticeBoard> findByFaculty(User u);
}

