package com.cg.consumer.controller;

import java.util.Date;
import java.util.List;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//import com.cg.consumer.exceptions.ShowNotFoundException;
import com.cg.consumer.model.Movie;
import com.cg.consumer.model.Show;
import com.cg.consumer.dto.Login;
//import com.cg.show.model.Show;
import com.cg.consumer.dto.MovieEntity;
import com.cg.consumer.dto.User;
import com.cg.consumer.dto.UserDto;

@RestController
public class ConsumerController {

	@Autowired
	RestTemplate rest;
	String result;
	
	public RestTemplate getRestTemplate() {
		return rest;
	}
	
	public void RestTemplate(RestTemplate rest) {
		this.rest=rest;
	}
	
	/*@GetMapping("/movie/{id}")
	public Movie getMovie(@PathVariable(value="id") Integer movieId) {
		Movie movie=rest.getForObject("http://MovieMS/movies/"+movieId, Movie.class);
		return movie;
	}
	@GetMapping("/movies/{name}")
	public List<Movie> getMovieByMovieName(@PathVariable(value="name") String movieName) {
		return rest.getForObject("http://MovieMS/movie/"+movieName,List.class);
		
	}
	@PostMapping("/movie")
	public String addMovie(@RequestBody MovieEntity movie) {
		String response=rest.postForObject("http://MovieMS/movie",movie,String.class);
		return response;
		
	}	
	@PutMapping("/movie/update/{movieId}")
	public String updateMovie(@PathVariable(value="movieId") Integer movieId,@RequestBody MovieEntity movie) {
		System.out.println("check update");
		rest.put("http://MovieMS/mv/update/"+movieId,movie,String.class);
		Movie movies=rest.getForObject("http://MovieMS/movies/"+movieId, Movie.class);
		if(movies!=null)
			return "Movie Updation Successful";
		else
			return "Movie Updation Unsuccessful";
	}
	@DeleteMapping("/movie/delete/{id}")
	public String deleteMovie(@PathVariable(value="id") Integer movieId) {
		System.out.println("delete in consumer");
		Movie movie=rest.getForObject("http://MovieMS/movies/"+movieId, Movie.class);
		if(movie!=null) {
			rest.delete("http://MovieMS/movie/delete/"+movieId,movieId);
			return "Movie Deletion successful";
		}
		else
			return "Movie Deletion unsuccessful";
		//return rest.delete("http://MovieMS/movie/delete/"+movieId,ResponseEntity.class);
		
	}
	
	@GetMapping("/theater/{id}")
	public Movie getTheater(@PathVariable(value="id") Integer theaterId) {
		Theater theater=rest.getForObject("http://TheaterMS/theaters/"+theaterId, Theater.class);
		return theater;
	}
	@GetMapping("/theater/{theaterCity}")
	public List<Theater> getTheaterByCityName(@PathVariable(value="city") String theaterCity) {
		return rest.getForObject("http://TheaterMS/theater/"+theaterCity,List.class);
		
	}
	@PostMapping("/theater")
	public String addTheater(@RequestBody TheaterEntity theater) {
		String response=rest.postForObject("http://TheaterMS/theater",theater,String.class);
		return response;
		
	}	
	@PutMapping("/theater/update/{theaterId}")
	public String updateTheater(@PathVariable(value="theaterId") Integer theaterId,@RequestBody TheaterEntity theater) {
		System.out.println("check update");
		rest.put("http://TheaterMS/theater/update/"+theaterId,theater,String.class);
		Theater theaters=rest.getForObject("http://TheaterMS/theater/"+theaterId, Movie.class);
		if(theaters!=null)
			return "Theater Updation Successful";
		else
			return "Theater Updation Unsuccessful";
	}
	@DeleteMapping("/theater/delete/{id}")
	public String deleteTheater(@PathVariable(value="id") Integer theaterId) {
		//System.out.println("delete in consumer");
		Theater theater=rest.getForObject("http://TheaterMS/theaters/"+theaterId, Movie.class);
		if(theater!=null) {
			rest.delete("http://TheaterMS/theater/delete/"+theaterId,theaterId);
			return "Theater Deletion successful";
		}
		else
			return "Theater Deletion unsuccessful";
		//return rest.delete("http://MovieMS/movie/delete/"+movieId,ResponseEntity.class);
		
	}*/
	
	//  http://localhost:4000/user/bawa/bawa
	@GetMapping("/user/{userName}/{userPassword}")
	public Login getUserByName(@PathVariable("userName") String userName, @PathVariable("userPassword") String userPassword){
		System.out.println("Entered in consumer");
		Login log=rest.getForObject("http://AuthServiceMS/user/"+userName+"/"+userPassword, Login.class);
		return log;
	}
	//  http://localhost:4000/user/updatePassword/bawa
	@PutMapping("/user/updatePassword/{userName}")
	public String updatePasswordByUserName(@RequestBody Login login, @PathVariable String userName){
		rest.put("http://AuthServiceMS/updatePassword/"+userName,login,Boolean.class);
		return "Update of password successful";
	}
	// http://localhost:4000/user/addUser
	@PostMapping("/user/addUser")
	public String addUser(@RequestBody UserDto user)  {
		String response=rest.postForObject("http://AuthServiceMS/addUser",user,String.class);
		return response;	
	}
	
	// http://localhost:4000/user/updateUser/101
	@PutMapping("/user/updateUser/{userId}")
	public String  updateUserById(@RequestBody User user, @PathVariable Integer userId) {
			rest.put("http://AuthServiceMS/updateUser/"+userId,user /* ,Boolean.class  */);
			return "Update of user detail Successful";
	}
	
/*	//http://localhost:4000/ShowMS/show/create
	@PostMapping("/show/create")
	public String createShow(@RequestBody Show show) {
		String response=rest.postForObject("http://ShowMS/createShow",show,String.class);
		return response;
		
	}
	
	//http://localhost:4000/ShowMS/show/<enterid>
	@GetMapping("/show/{showId}")
	public String readShow(@PathVariable Integer showId) {
		String show=rest.getForObject("http://ShowMS/readShow/"+showId, String.class);
		return show;
	}
	
	//http://localhost:4000/ShowMS/show/update/<enterid>
	@PutMapping("/show/update/{showId}")
	public String updateShow(@PathVariable Integer showId, @RequestBody Show show) {
		String shows=rest.getForObject("http://TheaterMS/theater/"+showId, String.class);
		if(shows==null||shows.equals(""))
			return "Show Updation UnSuccessful";
		else {
			rest.put("http://ShowMS/showUpdate/"+showId,show,String.class);
			return "Show Updation Successful";
		}	
	}
	//http://localhost:4000/ShowMS/show/delete/<enterid>
	@DeleteMapping("/show/delete/{showId}")
	public String deleteShow(@PathVariable Integer showId) {
		String show=rest.getForObject("http://ShowMS/readShow/"+showId, String.class);
		if(show==null||show.equals("")) {
			return "Show Deletion unsuccessful";
		}
		else {
			rest.delete("http://ShowMS/deleteShow/"+showId,showId);
		return "Show Deletion successful";
		}
		
	}*/
}	

	


