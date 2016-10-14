package cn.hibernate0924.onetomany.single;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.sessionFactory;

public class DeleteClasses {
	/**
	 * .删除班级
		   （1）
		   	 * 解除该班级和所有学生之间的关系,如果班级具有维护关系的能力，hibernate自动帮你实现
		     * 删除该班级
		   （2）
			 * 删除班级同时删除学生,将cascade="all"；
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getSession();
		Transaction transaction=session.beginTransaction();
		
		//1.
		//Classes classes=(Classes)session.get(Classes.class, 5L);
		//session.delete(classes);
		
		//2.
		//Classes classes=(Classes)session.get(Classes.class, 4L);
		//session.delete(classes);
		
		transaction.commit();
		session.close();
	}

	/**
	 * 总结
	 * 	1.在整个例子，班级负责维护关系，只要班级维护关系，就会发出update语句
	 * 	2.解除关系就是外键相对应的设置为null，
	 * 	3.改变关系就是相对应的外键从一个值变为另一个值
	 * 	4.在代码上的一线
	 * 		classes.setStudens();   重新建立关系
	 * 		classes.getStudent().remove();   解除关系
	 * 		classes.setStudent(null);  解除所有关系
	 * 		classes.getStudents().add();  在原有的关系基础上再建立关系
	 * 
	 */
}
