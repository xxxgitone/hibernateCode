package cn.hibernate0924.manytomany;


import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class realse_Rebuild_R {
	/**
	 * 7.已经存在一个学生，已经存在一个课程，解除该学生和原来一个课程之间的关系，建立该学生和新的一个课程之  间的关系
	 * 		
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 分析：
		 * 		1.从需求分析上看，这个例子就是关系的操作
		 * 		2.要查看谁来维护关系，从映射文件上看，student维护关系
		 * 		3.步骤：
		 * 			1.查询sid为3的学生
		 * 			2.得到该学生的所有课程
		 * 			3.遍历课程
		 * 				1.有两种可能操作：先移除集合中的一个元素，再添加；
		 *                            直接修改元素集合
		 *      多对多的关系操作包括两个操作对第三张表的增加、删除
		 */
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/manytomany/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		//获取sid为3的学生
		Student student=(Student)session.get(Student.class, 3L);
		//等到cid为1的课程
		Course course2=(Course)session.get(Course.class, 1L);
		//得到该学生的所有课程
		Set<Course> courses=student.getCourses();
		for(Course course:courses){
			if(course.getCid().longValue()==2){
				courses.remove(course);  //移除
				break;
			}
		}
		//增加
		courses.add(course2);
		
		
		transaction.commit();
		session.close();
		
		
		
	}

}
