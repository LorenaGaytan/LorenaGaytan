package net.tecgurus.schoolmanager.controller.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.tecgurus.schoolmanager.dao.mysql.StudentMySQLDaoImpl;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.exceptions.NonUniqueStudentException;
import net.tecgurus.schoolmanager.model.entity.Student;
import net.tecgurus.schoolmanager.service.StudentService;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/createstudent")
public class StudentCreate extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(StudentCreate.class.getName());
    private final StudentService studentService = new StudentService(new StudentMySQLDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/studentCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setAge(Integer.parseInt(age));
        student.setGender(gender.charAt(0));

        try {
            this.studentService.createUseCase(student);
            resp.sendRedirect(req.getContextPath() + "/student");
        } catch (DAOException e) {
            LOGGER.warning(e.getMessage());
            req.setAttribute("error", "Something was wrong, try again :c");
            this.doGet(req, resp);
        } catch (NonUniqueStudentException ex) {
            LOGGER.warning(ex.getMessage());
            req.setAttribute("error", ex.getMessage());
            this.doGet(req, resp);
        }
    }
}
