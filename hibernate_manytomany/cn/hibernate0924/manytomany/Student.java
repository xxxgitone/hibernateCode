package cn.hibernate0924.manytomany;

import java.io.Serializable;
import java.util.Set;

public class Student implements Serializable{
	private Long sid;
	private String sname;
	private String description;
	
	private Set<Course> courses;

	
	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
