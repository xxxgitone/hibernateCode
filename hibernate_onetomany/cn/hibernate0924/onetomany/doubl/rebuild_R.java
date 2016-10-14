package cn.hibernate0924.onetomany.doubl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.sessionFactory;


public class rebuild_R {
	/**
	 * 7.已经存在一个学生，已经存在一个班级，解除该学生和原来班级之间的关系，建立该学生和新班级之  间的关系
	 * 		涉及到关系的由学生来维护
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		
		Student student=(Student)session.get(Student.class, 3L);
		Classes classes=(Classes)session.get(Classes.class, 1L);
		
		student.setClasses(classes);
		
		transaction.commit();
		session.close();
	}

}
