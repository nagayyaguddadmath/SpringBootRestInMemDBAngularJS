package com.uxpsystems.assignment.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.model.UserStatus;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {


	@PersistenceContext
	EntityManager em;

	public static final String SELECTQUERYBYNAME = "SELECT U FROM User U WHERE U.username=:username AND U.status=:status";
	private static final String SELECTQUERYBYID = "SELECT U FROM User U WHERE U.userid=:userid AND U.status=:status";
	private static final String SELECTALLQUERY = "SELECT U FROM User U WHERE U.status=:status";


	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByName(String username) throws Exception {
		Query query = em.createQuery(SELECTQUERYBYNAME)
				.setParameter("username", username)
				.setParameter("status", UserStatus.Activated);
		query.setHint("org.hibernate.cacheable", true);
		query.setHint("org.hibernate.cacheMode", "NORMAL");

		return (List<User>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		Query query = em.createQuery(SELECTALLQUERY)
				.setParameter("status", UserStatus.Activated);
		query.setHint("org.hibernate.cacheable", true);
		query.setHint("org.hibernate.cacheMode", "NORMAL");

		System.out.println("\n.......Found Users From The Database.......\n");
		return (List<User>)query.getResultList();
	}

	@Override
	public User createNewUer(User user) throws Exception {

		try {
			em.persist(user);
			System.out.println("\n.......User Saved Successfully To The Database.......\n");

			return user;
		} catch(Exception exception) {
			throw exception;
		}

	}

	@Override
	public User deleteUser(User user) throws Exception {
		User newUser = em.merge(user);
		System.out.println("\n.......Deleted User Successfully From The Database.......\n");
		return newUser;
	}

	@Override
	public User updateUser(User user) throws Exception{
		User newUser = em.merge(user);
		System.out.println("\n.......User Updated Successfully To The Database.......\n");
		return newUser;
	}

	@Override
	public User findUserById(int userid) throws Exception {
		Query query = em.createQuery(SELECTQUERYBYID)
				.setParameter("userid", userid)
				.setParameter("status", UserStatus.Activated);
		query.setHint("org.hibernate.cacheable", true);
		query.setHint("org.hibernate.cacheMode", "NORMAL");

		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users != null && users.size() == 1) {
			System.out.println("\n.......Found User From The Database.......\n");
			return users.get(0);
		} else {
			throw new Exception("User Not found in the database");
		}
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}

