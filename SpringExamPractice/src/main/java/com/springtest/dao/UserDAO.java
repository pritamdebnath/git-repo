package com.springtest.dao;

import java.util.List;

import com.springtest.model.User;

public interface UserDAO {
	
	 List<User> list();
	 void insert(User user);
	 void update(User user);
	 void delete(int id);
	 User findById(int id);

}
