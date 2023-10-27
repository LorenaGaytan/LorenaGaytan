package net.tecgurus.schoolmanager.controller.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.tecgurus.schoolmanager.dao.mysql.CourseMySQLDaoImpl;
import net.tecgurus.schoolmanager.dao.mysql.InscriptionMySQLDaoImpl;
import net.tecgurus.schoolmanager.dao.mysql.StudentMySQLDaoImpl;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.dto.InscriptionDto;
import net.tecgurus.schoolmanager.model.entity.Course;
import net.tecgurus.schoolmanager.model.entity.Student;
import net.tecgurus.schoolmanager.service.CourseService;
import net.tecgurus.schoolmanager.service.InscriptionService;
import net.tecgurus.schoolmanager.service.StudentService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/studentdetails")
public class StudentDetails extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(StudentDetails.class.getName());

    private final StudentService studentService = new StudentService(new StudentMySQLDaoImpl());
    private final InscriptionService inscriptionService = new InscriptionService(new InscriptionMySQLDaoImpl());
    private final CourseService courseService = new CourseService(new CourseMySQLDaoImpl(), inscriptionService);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        long id = Long.parseLong(studentId);
        try {
            this.addDataToStudentInfo(req, id);
        } catch (DAOException e) {
            LOGGER.warning(e.getMessage());
        }
        req.getRequestDispatcher("studentDetails.jsp").forward(req, resp);
    }

    public void addDataToStudentInfo(HttpServletRequest request, Long studentId) throws DAOException {
        Student student = this.studentService.getByIdUseCase(studentId);
        request.setAttribute("student", student);

        List<Course> courses = this.courseService.listAllUseCase();
        request.setAttribute("courses", courses);

        List<InscriptionDto> inscriptions = this.inscriptionService.listByStudentIdUseCase(studentId);
        request.setAttribute("inscriptions", inscriptions);
    }
}
