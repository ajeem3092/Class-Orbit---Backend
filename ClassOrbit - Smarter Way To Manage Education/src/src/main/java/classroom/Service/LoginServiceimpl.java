package classroom.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import classroom.entities.User;
import classroom.repository.IUserRepository;

@Service
public class LoginServiceimpl implements ILoginService {

	@Autowired
	IUserRepository userRepo;

	@Override
	public User authenticateUser(String mail, String password) {
		User user = userRepo.findByEmail(mail);
		if (user != null && user.getPassword().equals(password))
			return user;
		return null;
	}

}
