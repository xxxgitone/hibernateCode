package cn.hibernate0924.onetomany.single;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.sessionFactory;

public class RealseAll_R {
	/**
	 * 10.解除该班级和所有的学生之间的关系
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		
		Classes classes=(Classes)session.get(Classes.class, 5L);
		classes.setStudents(null);
		
		transaction.commit();
		session.close();
	}

}
