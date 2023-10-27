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
import java.util.logging.Logger;

@WebServlet("/createcourse")
public class CourseCreate extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CourseCreate.class.getName());
    private final InscriptionService inscriptionService = new InscriptionService(new InscriptionMySQLDaoImpl());
    private final CourseService courseService = new CourseService(new CourseMySQLDaoImpl(), inscriptionService);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("courseCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        Course course = new Course();
        course.setName(name);

        try {
            this.courseService.createUseCase(course);
            resp.sendRedirect(req.getContextPath() + "/course");
        } catch (DAOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
