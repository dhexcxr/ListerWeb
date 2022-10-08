package meprod.ListerWeb;

import static java.lang.System.out;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HiberFunc {

	private HiberFunc() {};
	
	private static SessionFactory sessionFactory;

	static {
		out.println("static instant of HiberFunc");
		openDbConnection();
	}
	
	private static void openDbConnection() {
		// attempt to set up session factory
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception ex) { 
			out.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}


	protected static List<ToDoList> getLists() {
		// get all lists currently in DB
		System.out.println("start of getLists");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<ToDoList> listerLists = null;
		try {
			tx = session.beginTransaction();
			listerLists = session.createQuery("FROM ToDoList", ToDoList.class).list(); 
			tx.commit();
			System.out.println("we got to the end of the GetLists() query");
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return listerLists;
	}
	
	protected static ToDoList getList(String listPK) {
		return getList(Integer.parseInt(listPK));
	}
	
	protected static ToDoList getList(int listPK) {
		// get one list by index/primary key
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ToDoList listToOpen = null;
		try {
			tx = session.beginTransaction();
			listToOpen = session.get(ToDoList.class, listPK); 
			tx.commit();
		} catch (HibernateException e) {
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
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(listToSave);
			tx.commit();
			result = true;		// if we make it here, the process was successful
		} catch (HibernateException e) {
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
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.remove(listToDelete);
			tx.commit();
			result = true;		// if we make it here, the process was successful
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close();
		}
		return result;
	}
}
