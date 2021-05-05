package model.interfaces;

import java.util.List;

import model.entities.UserRole;

public interface IUserRoleDao {
	
	public UserRole findUserRoleByName(String name);
	
	public List<UserRole> listOfRoles();
}
