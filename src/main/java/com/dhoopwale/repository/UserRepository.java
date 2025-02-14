package com.dhoopwale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhoopwale.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

	public User findByEmail(String email);
}
