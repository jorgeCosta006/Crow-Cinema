package model.interfaces;

import model.entities.User;

public interface IUserDao {
	
	public void createOrUpdateUser(User user);
	
	public User findUserByEmailAndPassword(String email, String password);
	
	public User findUserByEmail(String email);
}
