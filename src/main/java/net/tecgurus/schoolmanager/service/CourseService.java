package net.tecgurus.schoolmanager.service;

import net.tecgurus.schoolmanager.dao.ICourseDao;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.exceptions.StudentsOnCourseException;
import net.tecgurus.schoolmanager.model.dto.InscriptionDto;
import net.tecgurus.schoolmanager.model.entity.Course;

import java.util.List;

public class CourseService {
    private final ICourseDao courseDao;
    private final InscriptionService inscriptionService;

    public CourseService(ICourseDao courseDao, InscriptionService inscriptionService) {
        this.courseDao = courseDao;
        this.inscriptionService = inscriptionService;
    }

    public List<Course> listAllUseCase() throws DAOException {
        return courseDao.listAll();
    }

    public Course getByIdUseCase(Long id) throws DAOException {
        return courseDao.getById(id);
    }

    public void createUseCase(Course course) throws DAOException {
        courseDao.create(course);
    }

    public void deleteUseCase(Long courseId) throws DAOException {
        List<InscriptionDto> inscriptions = this.inscriptionService.listBySCourseIdUseCase(courseId);
        if (!inscriptions.isEmpty()) {
            throw new StudentsOnCourseException("Some students are enrolled on the course you try to delete");
        }
        courseDao.delete(courseId);
    }

    public void updateUseCase(Course course) throws DAOException {
        courseDao.update(course);
    }
}
