package com.cg.service;

import java.util.List;

import com.cg.dto.UserDto;
import com.cg.entity.Login;
import com.cg.entity.User;
import com.cg.exception.UserException;

public interface UserService {
	//public List<User> getAllUser();
	//public User getUserById(Integer userId);
	public boolean update(User user);
	public String add(UserDto userDto);
	//boolean deleteUser(Integer userId);
	public Login findByUserName(String userName);
	public boolean updatePassword(Login login);
	public Login getUserByName(String userName, String userPassword) throws UserException;
}
