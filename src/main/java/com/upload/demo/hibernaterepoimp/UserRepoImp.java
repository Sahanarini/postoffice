package com.upload.demo.hibernaterepoimp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upload.demo.hibernaterepo.UserRepo;
import com.upload.demo.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

@Repository
public class UserRepoImp implements UserRepo {

	@Autowired
	EntityManager em;

	@Override
	public void addUser(User user) {
		em.persist(user);

	}

	@Override
	public void delUser(int id) {
		User us = em.find(User.class, id);
		if (us != null) {
			em.remove(us);
		}

	}

	@Override
	public void updateUser(User user) {
		em.merge(user);

	}

	@Override
	public List<User> getAllUsers() {
		return em.createQuery("from User").getResultList();
	}

	@Override
	public User findById(int id) {
		Query q = em.createQuery("from User where userid = ?1");
		q.setParameter(1, id);
		return (User) q.getSingleResult();

	}

	@Override
	public Optional<User> findByEmail(String email) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
		query.setParameter("email", email);
		try {
			User user = (User) query.getSingleResult();
			return Optional.of(user);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public User Login(String email, String password) {
		Query q = em.createQuery("from User where email =?1 and password = ?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		return (User) q.getSingleResult();
	}

}
