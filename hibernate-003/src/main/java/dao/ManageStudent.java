package dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entities.Student;

public class ManageStudent {
	private static SessionFactory factory;

	public static void main(String[] args) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		ManageStudent ms = new ManageStudent();

		/* Add few employee records in database */
		Integer sID1 = ms.addStudent("Shanmukha");
		Integer sID2 = ms.addStudent("Chandu");
		Integer sID3 = ms.addStudent("Lokesh");
		Integer sID4 = ms.addStudent("Sathi");
		
		ms.listEmployees();
	}

	public Integer addStudent(String name) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer studentRollNumber = null;

		try {
			tx = session.beginTransaction();
			Student student = new Student(name);
			studentRollNumber = (Integer) session.save(student);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return studentRollNumber;
	}

	public void listEmployees() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List students = session.createQuery("FROM Student").list();
			for (Iterator iterator = students.iterator(); iterator.hasNext();) {
				Student employee = (Student) iterator.next();
				System.out.println("Name: " + employee.getName());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
