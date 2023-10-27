package net.tecgurus.schoolmanager.dao;

import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.dto.InscriptionDto;

import java.util.List;

public interface IInscriptionDao {
    List<InscriptionDto> listByStudentId(Long id) throws DAOException;

    List<InscriptionDto> listByCourseId(Long courseId) throws DAOException;

    void create(InscriptionDto inscripcionDto) throws DAOException;

    void delete(Long id) throws DAOException;
}
