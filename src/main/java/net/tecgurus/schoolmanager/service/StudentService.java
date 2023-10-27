package net.tecgurus.schoolmanager.service;

import net.tecgurus.schoolmanager.dao.IStudentDao;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.exceptions.NonUniqueStudentException;
import net.tecgurus.schoolmanager.model.entity.Student;

import java.util.List;

public class StudentService {

    private final IStudentDao studentDao;

    public StudentService(IStudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> listAllUseCase() throws DAOException {
        return studentDao.listAll();
    }

    public Student getByIdUseCase(Long id) throws DAOException {
        return studentDao.getById(id);
    }

    public void createUseCase(Student student) throws DAOException {
        Student currentStudent = this.studentDao.getByEmai(student.getEmail());
        if (currentStudent != null) {
            throw new NonUniqueStudentException("The email %s is already on use".formatted(student.getEmail()));
        }
        studentDao.create(student);
    }

    public void deleteUseCase(Long id) throws DAOException {
        studentDao.delete(id);
    }

    public void updateUseCase(Student student) throws DAOException {
        Student currentStudent = this.studentDao.getByEmai(student.getEmail());
        if (currentStudent != null && !currentStudent.getId().equals(student.getId())) {
            throw new NonUniqueStudentException("The email \"%s\" is already on use".formatted(student.getEmail()));
        }
        studentDao.update(student);
    }

}
