package cn.hibernate0924.onetomany.single;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class saveStudent_R {
	/**
	 * 5.已经存在一个班级，新建一个学生，并且建立该学生和该班级之间的关系
	 * 		* 查询班级
	 * 		* 新建学生
	 * 		* 建立关系
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/onetomany/single/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();

		Student student=new Student();
		student.setSname("辣眼");
		student.setDescription("哈哈哈");
		
		Classes classes=(Classes)session.get(Classes.class, 1L);
		
		//建立了班级和学生之间的联系
		classes.getStudents().add(student);
		
		//classes.setStudents(students); //把班级中的学生更新了

		transaction.commit();
		session.close();
	}

}
