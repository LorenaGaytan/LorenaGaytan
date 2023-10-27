package net.tecgurus.schoolmanager.controller.course;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.tecgurus.schoolmanager.dao.mysql.CourseMySQLDaoImpl;
import net.tecgurus.schoolmanager.dao.mysql.InscriptionMySQLDaoImpl;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.exceptions.StudentsOnCourseException;
import net.tecgurus.schoolmanager.service.CourseService;
import net.tecgurus.schoolmanager.service.InscriptionService;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/deletecourse")
public class CourseDelete extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CourseDelete.class.getName());
    private final InscriptionService inscriptionService = new InscriptionService(new InscriptionMySQLDaoImpl());
    private final CourseService courseService = new CourseService(new CourseMySQLDaoImpl(), inscriptionService);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseIdStr = req.getParameter("courseId");
        Long courseId = Long.parseLong(courseIdStr);

        try {
            this.courseService.deleteUseCase(courseId);
            resp.sendRedirect(req.getContextPath() + "/course");
        } catch (DAOException e) {
            LOGGER.warning(e.getMessage());
        } catch (StudentsOnCourseException ex) {
            LOGGER.warning(ex.getMessage());
            req.setAttribute("error", ex.getMessage());
            new CourseList().doGet(req, resp);
        }
    }
}
