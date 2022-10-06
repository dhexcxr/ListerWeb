package meprod.ListerWeb;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HiberFunc {

	private HiberFunc() {};
	
//	private static final SessionFactory sessionFactory;

	static {
//		try {
//			 sessionFactory = new Configuration().configure().buildSessionFactory();
//		} catch (Throwable ex) {
//			// Log the exception. 
//			System.err.println("Initial SessionFactory creation failed." + ex);
//			throw new ExceptionInInitializerError(ex);
//		}
		System.out.println("static instant of HiberFunc");
		Lister.openDbConnection();
	}

//	protected static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}


	protected static List<ToDoList> getLists() {
		// get all lists currently in DB
		System.out.println("start of getLists");
		Session session = Lister.factory.openSession();
//		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<ToDoList> listerLists = null;
		try {
			tx = session.beginTransaction();
			listerLists = session.createQuery("FROM ToDoList", ToDoList.class).list(); 
			tx.commit();
			System.out.println("we got to the end og the GetLists() quety");
		} catch (HibernateException e) {
//		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return listerLists;
	}
	
	protected static ToDoList getList(int listPK) {
		// get one list by index/primary key
		Session session = Lister.factory.openSession();
		Transaction tx = null;
		ToDoList listToOpen = null;
		try {
			tx = session.beginTransaction();
			listToOpen = session.get(ToDoList.class, listPK); 
			tx.commit();
		} catch (HibernateException e) {
//		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return listToOpen;
	}

	protected static boolean saveList(ToDoList listToSave) {
		// save a ToDoList object
		boolean result = false; 
		Session session = Lister.factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(listToSave);
			tx.commit();
			result = true;		// if we make it here, the process was successful
		} catch (HibernateException e) {
//		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return result;
	}
	
	protected static boolean deleteList(ToDoList listToDelete) {
		// save a ToDoList object
		boolean result = false; 
		Session session = Lister.factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(listToDelete);
			tx.commit();
			result = true;		// if we make it here, the process was successful
		} catch (HibernateException e) {
//		} catch (Exception e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return result;
	}

}
