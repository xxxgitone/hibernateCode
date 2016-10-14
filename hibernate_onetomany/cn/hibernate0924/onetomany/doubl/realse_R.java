package cn.hibernate0924.onetomany.doubl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.sessionFactory;

public class realse_R {
	/**
	 * 8.已经存在一个学生，解除该学生和该班级所在班级之间的关系
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		
		Student student=(Student)session.get(Student.class, 3L);
		student.setClasses(null);
		
		transaction.commit();
		session.close();
	}

}
