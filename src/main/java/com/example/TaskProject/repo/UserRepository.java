package com.example.TaskProject.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TaskProject.entity.User;


@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
	
	
  Optional<User>findByUserEmail(String userEmail);

}
