package com.uxpsystems.assignment.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
//@EnableTransactionManagement
public class UserDAOImpl {

	/*	static Session sessionObj;
	@Autowired
	private SessionFactory sessionFactory;
	 */	
	private static int userId = 1;

	@PersistenceContext
	EntityManager em;

	private static final String SELECTQUERYBYNAME = "SELECT U FROM User U WHERE U.username=:username AND U.status=:status";
	private static final String SELECTQUERYBYID = "SELECT U FROM User U WHERE U.userid=:userid AND U.status=:status";
	private static final String SELECTALLQUERY = "SELECT U FROM User U WHERE U.status=:status";


	@SuppressWarnings("unchecked")
	public List<User> getUsersByName(String username) {
		Query query = em.createQuery(SELECTQUERYBYNAME)
				.setParameter("username", username)
				.setParameter("status", UserStatus.Activated);
		return (List<User>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Query query = em.createQuery(SELECTALLQUERY)
				.setParameter("status", UserStatus.Activated);
		return (List<User>)query.getResultList();
	}

	public void createNewUer(User user) {

		try {
			User userObj = new User();
			userObj.setUserid(userId++);
			userObj.setUsername(user.getUsername());
			userObj.setPassword(user.getPassword());
			userObj.setStatus(UserStatus.Activated);

			em.persist(userObj);
			System.out.println("\n.......User Saved Successfully To The Database.......\n");

		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}

	}

	public boolean deleteUser(int userid) {
		User user = findUserById(userid);
		user.setStatus(UserStatus.Deactivated);
		em.persist(user);
		return true;
	}

	public User updateUser(User user) {
		em.merge(user);
		return user;
	}

	private User findUserById(int userid) {
		Query query = em.createQuery(SELECTQUERYBYID)
				.setParameter("userid", userid)
				.setParameter("status", UserStatus.Activated);
		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users != null && users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

}

