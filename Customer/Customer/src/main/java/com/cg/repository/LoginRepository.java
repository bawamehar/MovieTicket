package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

	public Login findByUserName(String userName);
	//Optional<Login> findByUserName(String userName);
}
