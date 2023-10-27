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

@WebServlet("/updatestudent")
public class StudentUpdate extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(StudentUpdate.class.getName());
    private final StudentService studentService = new StudentService(new StudentMySQLDaoImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        long id = Long.parseLong(studentId);

        try {
            Student student = this.studentService.getByIdUseCase(id);
            req.setAttribute("student", student);
            req.getRequestDispatcher("studentUpdate.jsp").forward(req, resp);
        } catch (DAOException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String studentId = req.getParameter("studentId");
        long id = Long.parseLong(studentId);

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setAge(Integer.parseInt(age));
        student.setGender(gender.charAt(0));

        try {
            studentService.updateUseCase(student);
            resp.sendRedirect(req.getContextPath() + "/studentdetails?studentId=" + studentId);
        } catch (DAOException e) {
            LOGGER.warning(e.getMessage());
        } catch (NonUniqueStudentException ex) {
            LOGGER.warning(ex.getMessage());
            req.setAttribute("error", ex.getMessage());
            this.doGet(req, resp);
        }
    }
}
