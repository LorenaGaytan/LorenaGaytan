package net.tecgurus.schoolmanager.model.entity;

public class Course {
	private Long id;
	private String name;
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}
	
	public Course() {}
	
	public Course(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
