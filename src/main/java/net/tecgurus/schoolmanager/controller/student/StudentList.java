package net.tecgurus.schoolmanager.controller.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.tecgurus.schoolmanager.dao.mysql.StudentMySQLDaoImpl;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.entity.Student;
import net.tecgurus.schoolmanager.service.StudentService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/student")
public class StudentList extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(StudentList.class.getName());

    private final StudentService studentService = new StudentService(new StudentMySQLDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Student> students = studentService.listAllUseCase();
            req.setAttribute("students", students);
            req.getRequestDispatcher("studentList.jsp").forward(req, resp);
        } catch (DAOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
