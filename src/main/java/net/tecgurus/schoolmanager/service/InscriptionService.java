package net.tecgurus.schoolmanager.service;

import net.tecgurus.schoolmanager.dao.IInscriptionDao;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.dto.InscriptionDto;

import java.util.List;

public record InscriptionService(IInscriptionDao inscriptionDao) {

    public List<InscriptionDto> listByStudentIdUseCase(Long studentId) throws DAOException {
        return inscriptionDao.listByStudentId(studentId);
    }

    public List<InscriptionDto> listBySCourseIdUseCase(Long courseId) throws DAOException {
        return inscriptionDao.listByCourseId(courseId);
    }

    public void createUseCase(InscriptionDto inscripcionDto) throws DAOException {
        inscriptionDao.create(inscripcionDto);
    }

    public void deleteUseCase(Long id) throws DAOException {
        inscriptionDao.delete(id);
    }

}
