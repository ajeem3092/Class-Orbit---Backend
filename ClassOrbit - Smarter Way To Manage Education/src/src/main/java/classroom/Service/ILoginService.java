package classroom.Service;

import classroom.entities.User;

public interface ILoginService {

	User authenticateUser(String email, String password);
}

