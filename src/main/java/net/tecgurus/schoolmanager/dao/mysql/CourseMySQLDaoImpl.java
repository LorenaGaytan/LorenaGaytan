package net.tecgurus.schoolmanager.dao.mysql;

import net.tecgurus.schoolmanager.dao.ICourseDao;
import net.tecgurus.schoolmanager.dao.jdbc.jdbctemplate.JdbcTemplate;
import net.tecgurus.schoolmanager.dao.jdbc.jdbctemplate.KeyHolder;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.entity.Course;

import java.util.List;

public class CourseMySQLDaoImpl implements ICourseDao {

    private final JdbcTemplate<Course> jdbcTemplate = new JdbcTemplate<>();

    @Override
    public List<Course> listAll() throws DAOException {
        String query = "SELECT * FROM courses";
        return this.jdbcTemplate.queryForList(
            query,
            rs -> {
                Course course = new Course();
                course.setId(rs.getLong("id"));
                course.setName(rs.getString("name"));
                return course;
            }
        );
    }

    @Override
    public Course getById(Long id) throws DAOException {
        String query = "SELECT * FROM courses WHERE id = ?";
        return this.jdbcTemplate.queryForObject(
            query,
            ps -> ps.setLong(1, id),
            rs -> {
                Course course = new Course();
                course.setId(rs.getLong("id"));
                course.setName(rs.getString("name"));
                return course;
            }
        );
    }

    @Override
    public void create(Course course) throws DAOException {
        String query = "INSERT INTO courses(name) VALUES(?)";
        KeyHolder keyHolder = new KeyHolder();
        this.jdbcTemplate.update(
            query,
            ps -> ps.setString(1, course.getName()),
            keyHolder
        );
        course.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) throws DAOException {
        String query = "DELETE FROM courses WHERE id = ?";
        this.jdbcTemplate.update(
            query,
            ps -> ps.setLong(1, id)
        );
    }

    @Override
    public void update(Course course) throws DAOException {
        String query = "UPDATE courses SET name = ? WHERE id = ?";
        this.jdbcTemplate.update(
            query,
            ps -> {
                ps.setString(1, course.getName());
                ps.setLong(2, course.getId());
            }
        );
    }
}
