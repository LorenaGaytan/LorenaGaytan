package net.tecgurus.schoolmanager.controller.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.tecgurus.schoolmanager.dao.mysql.StudentMySQLDaoImpl;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.service.StudentService;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/deletestudent")
public class StudentDelete extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(StudentDelete.class.getName());
    private final StudentService studentService = new StudentService(new StudentMySQLDaoImpl());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        long id = Long.parseLong(studentId);

        try {
            this.studentService.deleteUseCase(id);
            resp.sendRedirect(req.getContextPath() + "/student");
        } catch (DAOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
