package net.tecgurus.schoolmanager.controller.inscription;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.tecgurus.schoolmanager.dao.mysql.InscriptionMySQLDaoImpl;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.service.InscriptionService;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/deleteinscription")
public class InscriptionDelete extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(InscriptionDelete.class.getName());
    private final InscriptionService inscriptionService = new InscriptionService(new InscriptionMySQLDaoImpl());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String inscriptionIdStr = req.getParameter("inscriptionId");
        Long inscriptionId = Long.parseLong(inscriptionIdStr);

        String studentIdStr = req.getParameter("studentId");
        long studentId = Long.parseLong(studentIdStr);

        try {
            this.inscriptionService.deleteUseCase(inscriptionId);
            resp.sendRedirect(req.getContextPath() + "/studentdetails?studentId=" + studentId);
        } catch (DAOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
