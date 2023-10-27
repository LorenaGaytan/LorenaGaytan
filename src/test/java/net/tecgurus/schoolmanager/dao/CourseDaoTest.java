package net.tecgurus.schoolmanager.dao;

import net.tecgurus.schoolmanager.dao.mysql.CourseMySQLDaoImpl;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.entity.Course;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CourseDaoTest {

    private ICourseDao courseDao = new CourseMySQLDaoImpl();

    @Test
    public void listAll() throws DAOException {
        List<Course> courses = this.courseDao.listAll();
        Assertions.assertNotNull(courses);
    }

    @Test
    public void getById() throws DAOException {
        Course course = new Course();
        course.setName("test course");
        this.courseDao.create(course);

        Course currentCourse = this.courseDao.getById(course.getId());
        Assertions.assertNotNull(currentCourse);
    }

    @Test
    public void create() throws DAOException {
        Course course = new Course();
        course.setName("test course");
        courseDao.create(course);

        Assertions.assertNotNull(course.getId());
    }

    @Test
    public void update() throws DAOException {
        Course course = new Course();
        course.setName("java desde cero");
        this.courseDao.create(course);

        Course currentCourse = this.courseDao.getById(course.getId());
        currentCourse.setName(currentCourse.getName() + " - UPDATED");
        this.courseDao.update(currentCourse);

        Course finalCourse = this.courseDao.getById(course.getId());
        Assertions.assertEquals("java desde cero - UPDATED", finalCourse.getName());
    }

    @Test
    public void delete() throws DAOException {
        Course course = new Course();
        course.setName("java frameworks");
        this.courseDao.create(course);

        Course currentCourse = this.courseDao.getById(course.getId());
        Assertions.assertNotNull(currentCourse);

        this.courseDao.delete(currentCourse.getId());

        Course deletedCourse = this.courseDao.getById(course.getId());
        Assertions.assertNull(deletedCourse);
    }
}
