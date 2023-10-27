package net.tecgurus.schoolmanager.controller.inscription;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.tecgurus.schoolmanager.dao.mysql.InscriptionMySQLDaoImpl;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.dto.InscriptionDto;
import net.tecgurus.schoolmanager.service.InscriptionService;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/createinscription")
public class InscriptionCreate extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(InscriptionCreate.class.getName());
    private final InscriptionService inscriptionService = new InscriptionService(new InscriptionMySQLDaoImpl());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String courseId = req.getParameter("courseId");
        String studentId = req.getParameter("studentId");

        InscriptionDto inscripcion = new InscriptionDto();
        inscripcion.setCourse(Long.parseLong(courseId));
        inscripcion.setStudent(Long.parseLong(studentId));

        try {
            this.inscriptionService.createUseCase(inscripcion);
            resp.sendRedirect(req.getContextPath() + "/studentdetails?studentId=" + studentId);
        } catch (DAOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
