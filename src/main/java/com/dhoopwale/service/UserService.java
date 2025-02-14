package com.dhoopwale.service;

import com.dhoopwale.exception.UserException;
import com.dhoopwale.model.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

}
