package business;

import model.dao.UserDao;
import model.dao.UserRoleDao;
import model.entities.User;
import model.entities.UserRole;
import model.interfaces.IUserDao;
import model.interfaces.IUserRoleDao;

public class AccountBusiness {
	
	public void addNewUser(String name, String email, String password, String userRoleName) {
		IUserDao ud = new UserDao();
		IUserRoleDao urd = new UserRoleDao();

		UserRole userRole = urd.findUserRoleByName(userRoleName);

		User user = new User(name, email, password, null, userRole, true);
		ud.createOrUpdateUser(user);
	}
	
	public User findUserByEmailAndPassword(String email, String password) {
		IUserDao ud = new UserDao();

		User user = ud.findUserByEmailAndPassword(email, password);
		if (user == null) {
			return null;
		} else {
			return user;
		}
	}
	
	public User findUserByEmail(String email) {
		IUserDao ud = new UserDao();

		User user = ud.findUserByEmail(email);
		if (user == null) {
			return null;
		} else {
			return user;
		}
	}
}
