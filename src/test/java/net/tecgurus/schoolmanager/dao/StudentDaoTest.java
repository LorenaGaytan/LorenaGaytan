package net.tecgurus.schoolmanager.dao;

import net.tecgurus.schoolmanager.dao.mysql.StudentMySQLDaoImpl;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StudentDaoTest {

    private IStudentDao studentDao = new StudentMySQLDaoImpl();

    @Test
    public void listAll() throws DAOException {
        List<Student> students = this.studentDao.listAll();
        Assertions.assertNotNull(students);
    }

    @Test
    public void getById() throws DAOException {
        Student student = studentDao.getById(1L);
        Assertions.assertNotNull(student);
    }

    @Test
    public void create() throws DAOException {
        Student student = new Student();
        student.setName("test student");
        student.setAge(36);
        student.setEmail("student@student.com");
        student.setGender('f');
        this.studentDao.create(student);

        Assertions.assertNotNull(student.getId());

        Student currentStudent = this.studentDao.getById(student.getId());
        Assertions.assertNotNull(currentStudent);
    }

    @Test
    public void update() throws DAOException {

        Student student = new Student();
        student.setName("test student");
        student.setAge(36);
        student.setEmail("student@student.com");
        student.setGender('f');
        this.studentDao.create(student);

        Assertions.assertNotNull(student.getId());

        Student currentStudent = this.studentDao.getById(student.getId());
        currentStudent.setName(student.getName() + " - UPDATED");
        this.studentDao.update(currentStudent);

        Student finalStudent = this.studentDao.getById(student.getId());
        Assertions.assertNotNull(finalStudent);
        Assertions.assertEquals("test student - UPDATED", finalStudent.getName());
    }

    @Test
    public void delete() throws DAOException {
        Student student = new Student();
        student.setName("test delete student");
        student.setAge(36);
        student.setEmail("student@student.com");
        student.setGender('f');
        this.studentDao.create(student);
        Assertions.assertNotNull(student.getId());

        Student currentStudent = this.studentDao.getById(student.getId());
        Assertions.assertNotNull(currentStudent);

        this.studentDao.delete(currentStudent.getId());

        Student deletedStudent = this.studentDao.getById(student.getId());
        Assertions.assertNull(deletedStudent);
    }

}
