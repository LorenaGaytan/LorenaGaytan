package net.tecgurus.schoolmanager.controller.course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.tecgurus.schoolmanager.dao.mysql.CourseMySQLDaoImpl;
import net.tecgurus.schoolmanager.dao.mysql.InscriptionMySQLDaoImpl;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.entity.Course;
import net.tecgurus.schoolmanager.service.CourseService;
import net.tecgurus.schoolmanager.service.InscriptionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/course")
public class CourseList extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CourseList.class.getName());

    private final InscriptionService inscriptionService = new InscriptionService(new InscriptionMySQLDaoImpl());
    private final CourseService courseService = new CourseService(new CourseMySQLDaoImpl(), inscriptionService);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses = new ArrayList<>();
        try {
            courses = this.courseService.listAllUseCase();
        } catch (DAOException e) {
            LOGGER.warning(e.getMessage());
        }
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("courseList.jsp").forward(req, resp);
    }
}
