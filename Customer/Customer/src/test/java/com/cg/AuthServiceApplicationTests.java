package com.cg;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cg.dto.UserDto;
import com.cg.entity.Login;
import com.cg.entity.User;
import com.cg.exception.UserException;
import com.cg.repository.LoginRepository;
import com.cg.repository.UserRepository;
import com.cg.service.UserServiceImpl;



@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthServiceApplicationTests {

	@MockBean
	private LoginRepository loginRepo;
	
	@MockBean
	private UserRepository repo;
	
	@InjectMocks
	private UserServiceImpl service;

	User user;
	Login login;
	UserDto userDto;
	List<User> list;
	List<Login> loginList;

	@Test
	public void contextLoads() {
	}
		
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		user = new User(101,"bawamehar", "mehar", "singh",  8320167895l);
		login = new Login ("bawa", "bawa", "admin");
		userDto = new UserDto(101,"bawa","mehar", "bawa", 8741201254l, "admin", "password");
	}

	
	@Test
	public void testUpdateUserById_Success(){
		//mock external calls
		 Mockito.when(repo.save(Mockito.any(User.class))).thenReturn(user);
		 // invoke test method
		boolean response = service.update(user);
		assertTrue(response);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateUserById_Failure(){
		//mock external calls
		 Mockito.when(repo.save(Mockito.any(User.class))).thenThrow(new IllegalArgumentException());
		 // invoke test method
		service.update(user);
	}
	
	
	@Test
	public void testFindByUserName_Success() {
		String name ="bawa";
		Mockito.when(loginRepo.findByUserName(name)).thenReturn(login);
		service.findByUserName(name);
		
	}
	
	@Test(expected = IllegalArgumentException.class )
	public void testFindByUserName_Failure() {
		String name ="bawa";
		Mockito.when(loginRepo.findByUserName(name)).thenThrow(new IllegalArgumentException());
		service.findByUserName(name);	
	}
	
	@Test
	public void testGetUserByName_Success() throws UserException {
		String name="bawa";
		String password = "bawa";
		Mockito.when(loginRepo.findByUserName(name)).thenReturn(login);
		service.getUserByName(name, password);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetUserByName_Failure() throws UserException {
		String name="bawa";
		String password = "bawa";
		Mockito.when(loginRepo.findByUserName(name)).thenThrow(new IllegalArgumentException());
		service.getUserByName(name, password);
	}

	
	@Test
	public void testUpdatePassword_Success() {
		Mockito.when(loginRepo.save(Mockito.any(Login.class))).thenReturn(login);
		service.updatePassword(login);
	}
	
	

 	

	@Test(expected = IllegalArgumentException.class )
	public void testUpdatePassword_Failure() {
		Mockito.doThrow(new IllegalArgumentException()).when(loginRepo).save(Mockito.any(Login.class));
		//Mockito.when(repo.save(Mockito.any(User.class))).thenThrow(new IllegalArgumentException());
		service.updatePassword(login);
	}

		
	
	@Test
	public void testAddUser_Success() {
		Mockito.when(repo.saveAndFlush(Mockito.any(User.class))).thenReturn(user);
		Mockito.when(loginRepo.saveAndFlush(Mockito.any(Login.class))).thenReturn(login);
		
		service.add(userDto);
	}

	@Test(expected = IllegalArgumentException.class )
	public void testAddUser_Failure() {
		Mockito.when(repo.saveAndFlush(Mockito.any(User.class))).thenThrow(new IllegalArgumentException());
		Mockito.when(loginRepo.saveAndFlush(Mockito.any(Login.class))).thenThrow(new IllegalArgumentException());
		
		service.add(userDto);
	}


}
