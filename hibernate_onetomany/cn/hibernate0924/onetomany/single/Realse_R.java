package cn.hibernate0924.onetomany.single;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.sessionFactory;

public class Realse_R {
	/**
	 * 8.已经存在一个学生，解除该学生和该班级所在班级之间的关系
	 * 		1.查询该学生
	 * 		2.查询该班级
	 * 		3.解除关系
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		Student student=(Student)session.get(Student.class, 1L);
		Classes classes=(Classes)session.get(Classes.class, 5L);
		
		classes.getStudents().remove(student);
		
		transaction.commit();
		session.close();
	}

}
