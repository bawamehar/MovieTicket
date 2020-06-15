package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.dto.UserDto;
import com.cg.entity.Login;
import com.cg.entity.User;
import com.cg.exception.UserException;
import com.cg.repository.LoginRepository;
import com.cg.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	LoginRepository loginRepo;
/*	
	@Override
	public List<User> getAllUser() {
		return repo.findAll();
	}
	
	@Override
	public User getUserById(Integer userId) {
		return repo.findById(userId).get();
	}
	
	@Override
	public boolean deleteUser(Integer userId) {
		repo.deleteById(userId);
		return true;
	}
*/
	@Override
	public boolean update(User user) {
		try {
			System.out.println("inside update seevice");
		repo.save(user);
		System.out.println(user);
		return true;
		}
		catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		
	}
	
	@Override
	public String add(UserDto userDto) {
		System.out.println("inside add seevice");
		User user = new User(userDto.getUserId(), userDto.getUserName(), userDto.getFirstName(), userDto.getLastName(), userDto.getUserContact());
		Login login = new Login(userDto.getUserName(), userDto.getPassword(), userDto.getRole());
		repo.saveAndFlush(user);
		System.out.println(user);
		loginRepo.saveAndFlush(login);
		System.out.println(login);
		return "New User added";
		
	}
	
	@Override
	public Login findByUserName( String userName){
		try {
			return loginRepo.findByUserName(userName);
		}
		catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		
		
	}

	@Override
	public boolean updatePassword(Login login) {
		try {
			loginRepo.save(login);
			
		}catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		return true;
	}
	
	@Override
	public Login getUserByName(String userName, String userPassword) throws UserException {
		Login login = loginRepo.findByUserName(userName);
	//	if(login.getUserName().contains(userName)) {
			if(login.getUserPassword().equalsIgnoreCase(userPassword)) {
				return login;
			}
			else {
				throw new UserException("Invalid Username or password");
			}
			//  else {    throw new UserException("Invalid Username or password");   }
		}  
	
	
	
	
}
