package net.tecgurus.schoolmanager.dao;

import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.entity.Student;

public interface IStudentDao extends CRUD<Student, Long> {
    Student getByEmai(String email) throws DAOException;
}
