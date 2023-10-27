package net.tecgurus.schoolmanager.dao;

import net.tecgurus.schoolmanager.exceptions.DAOException;

import java.util.List;

public interface CRUD<T, K> {
    List<T> listAll() throws DAOException;

    T getById(K id) throws DAOException;

    void create(T t) throws DAOException;

    void delete(K id) throws DAOException;

    void update(T t) throws DAOException;
}
