package classroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import classroom.entities.Role;
import classroom.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	public boolean existsByEmail(String email);

	public User findByEmail(String email);

	public List<User> findByRole(Role role);

}
