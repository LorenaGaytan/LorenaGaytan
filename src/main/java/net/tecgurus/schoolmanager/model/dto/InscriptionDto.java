package net.tecgurus.schoolmanager.model.dto;

public class InscriptionDto {
	private Long id;
	private Long course;
	private Long student;
	private String studentName;
	private String courseName;
	
	public InscriptionDto() {}
	
	public InscriptionDto(Long id, Long course, Long student, String studentName, String courseName) {
		super();
		this.id = id;
		this.course = course;
		this.student = student;
		this.studentName = studentName;
		this.courseName = courseName;
	}
	
	@Override
	public String toString() {
		return "InscriptionDto [id=" + id + ", course=" + course + ", student=" + student + ", studentName="
				+ studentName + ", courseName=" + courseName + "]";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCourse() {
		return course;
	}
	public void setCourse(Long course) {
		this.course = course;
	}
	public Long getStudent() {
		return student;
	}
	public void setStudent(Long student) {
		this.student = student;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
