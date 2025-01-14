package com.upload.demo.hibernaterepo;

import java.util.List;
import java.util.Optional;

import com.upload.demo.model.User;

public interface UserRepo {

	public void addUser(User user);

	public void delUser(int id);

	public void updateUser(User user);

	public List<User> getAllUsers();

	public User findById(int id);

	Optional<User> findByEmail(String email);

	public User Login(String email, String password);

}
