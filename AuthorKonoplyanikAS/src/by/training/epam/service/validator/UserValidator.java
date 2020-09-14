package by.training.epam.service.validator;

import by.training.epam.bean.User;

public class UserValidator {
	
	public boolean validateSignUp(User user) {
		if (user == null || user.getLogin().isEmpty() || 
				user.getPassword().isEmpty() || user.getName().isEmpty()) {
			return false;
		}
		return true;
	}
	
}

