/*package com.uxpsystems.assignment.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.uxpsystems.assignment.dao.User;
import com.uxpsystems.assignment.dao.UserStatus;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	static User userObj;
	static Session sessionObj;
	
	public static SessionFactory buildSessionFactory() {
		try {
			Configuration configObj = new Configuration().configure("hibernate.cfg.xml");
			ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
			// Create the SessionFactory from hibernate.cfg.xml
			return configObj.buildSessionFactory(serviceRegistryObj);
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	
	public static void main(String[] args) {
		try {
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			for(int i = 101; i <= 105; i++) {
				userObj = new User();
				userObj.setUserid(i);
				userObj.setUsername("UserName " + i);
				userObj.setPassword("password");
				userObj.setStatus(UserStatus.Activated);

				sessionObj.save(userObj);
			}
			System.out.println("\n.......Records Saved Successfully To The Database.......\n");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}*/