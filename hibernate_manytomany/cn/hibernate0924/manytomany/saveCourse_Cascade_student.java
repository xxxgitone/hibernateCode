package cn.hibernate0924.manytomany;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class saveCourse_Cascade_student {
	/**
	 * 保存课程级联保存学生
	 * 
	 *     	Hibernate: select max(cid) from Course
			Hibernate: select max(sid) from Student
			Hibernate: insert into Course (cname, description, cid) values (?, ?, ?)
			Hibernate: insert into Student (sname, description, sid) values (?, ?, ?)
		往course和student分别插入一条数据，和关系表没有关系，
		   通过映射文件可以看出，student维护关系，但没有写student.setCourse语句
		
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		configuration.configure("cn/hibernate0924/manytomany/hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		//新建课程
		Course course=new Course();
		course.setCname("java基础");
		course.setDescription("名师教学");
		
		//新建学生
		Student student=new Student();
		student.setSname("王五");
		student.setDescription("加油");
		
		Set<Student> students=new HashSet<Student>();
		students.add(student);
		
		//通过课程建立与学生之间的关系  设置Course的配置文件cascade为save-update inverse为true
		course.setStudents(students); //因为课程是新的，没有学生
		session.save(course);
		
		transaction.commit();
		session.close();
	}

}
