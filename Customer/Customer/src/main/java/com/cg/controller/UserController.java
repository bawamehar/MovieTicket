package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.UserDto;
import com.cg.entity.Login;
import com.cg.entity.User;
import com.cg.exception.UserException;
import com.cg.service.UserServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "This is User Controller")
//@RequestMapping("/UserCtrl")
public class UserController {

	@Autowired
	UserServiceImpl service;
	

	
	// http://localhost:5001/UserCtrl/updateUser/101
	//@HystrixCommand(fallbackMethod = "noUserToUpdate")
	@ApiOperation(value = "Update details of specified User", response = Boolean.class)
	@PutMapping("/updateUser/{userId}")
	public boolean updateUserById(@RequestBody User user, @PathVariable Integer userId) throws UserException{
		System.out.println("indise update ctrl");
		boolean b= service.update(user);
		System.out.println(b);
		return b;	
	}
	
	//  http://localhost:5001/UserCtrl/addUser
	@ApiOperation(value = "Add a new User", response = String.class)
	@PostMapping("/addUser")
	@HystrixCommand(fallbackMethod = "noUserAdded")
	public String addUser(@RequestBody UserDto user)  throws UserException {
		System.out.println("indise add ctrl");
		String b = service.add(user);
		System.out.println(b);
		return b;
	}
	
	//  http://localhost:5001/UserCtrl/updatePassword/101
	@PutMapping("/updatePassword/{userName}")
	public boolean updatePasswordByUserName(@RequestBody Login login, @PathVariable String userName)  throws UserException{
		Login l = service.findByUserName(userName);
		//service.updatePassword(login.getUserPassword());
		l.setUserPassword(login.getUserPassword());
		boolean b = service.updatePassword(l);
		return b;
		
	}
	
	//  http://localhost:5001/UserCtrl/user/bawa/bawa
	@GetMapping("/user/{userName}/{userPassword}")
	@HystrixCommand(fallbackMethod = "invalidUser")
	public Login getUserByName(@PathVariable("userName") String userName, @PathVariable("userPassword") String userPassword) throws UserException{
		System.out.println("Entered in bawa function");
		return service.getUserByName(userName, userPassword);
	}
	
	public Login invalidUser(@PathVariable("userName") String userName, @PathVariable("userPassword") String userPassword) {
		return new Login(null, "", "");
	}
	
	
	/*
	
	public boolean noUserToUpdate(@RequestBody User user, @PathVariable Integer userId) {
		return false;
	}*/
	
	public String noUserAdded(@RequestBody UserDto user) {
		return "User not added";
	}
}
