package cn.hibernate0924.onetomany.single;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.sessionFactory;

public class Realse_Rebuild {
	/**
	 * 7.已经存在一个学生，已经存在一个班级，解除该学生和原来班级之间的关系，建立该学生和新班级之  间的关系
	 * 		1.查找班级
	 * 		2.查找学生
	 * 		3.解除关系
	 * 		4.建立关系
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		//Classes classes=(Classes)session.get(Classes.class, 4L);
		
		Student student=(Student)session.get(Student.class, 1L);
		Classes classes2=(Classes)session.get(Classes.class, 5L);
		
		//classes.getStudents().remove(student);//解除关系  
		
		classes2.getStudents().add(student);//建立关系
		transaction.commit();
		session.close();
		
	}

}
