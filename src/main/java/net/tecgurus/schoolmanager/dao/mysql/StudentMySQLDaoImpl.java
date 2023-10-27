package net.tecgurus.schoolmanager.dao.mysql;

import net.tecgurus.schoolmanager.dao.IStudentDao;
import net.tecgurus.schoolmanager.dao.jdbc.jdbctemplate.JdbcTemplate;
import net.tecgurus.schoolmanager.dao.jdbc.jdbctemplate.KeyHolder;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.entity.Student;

import java.util.List;

public class StudentMySQLDaoImpl implements IStudentDao {

    private final JdbcTemplate<Student> jdbcTemplate = new JdbcTemplate<>();

    @Override
    public List<Student> listAll() throws DAOException {
        final String query = "SELECT * FROM students";
        return this.jdbcTemplate.queryForList(
            query,
            rs -> {
                Student student = new Student();
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setAge(rs.getInt("age"));
                String gender = rs.getString("gender");
                if (gender != null) {
                    student.setGender(gender.charAt(0));
                }
                return student;
            }
        );
    }

    @Override
    public Student getById(Long id) throws DAOException {
        final String query = "SELECT * FROM students WHERE id = ?";
        return this.jdbcTemplate.queryForObject(
            query,
            ps -> ps.setLong(1, id),
            rs -> {
                Student student = new Student();
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setAge(rs.getInt("age"));
                String gender = rs.getString("gender");
                if (gender != null) {
                    student.setGender(gender.charAt(0));
                }
                return student;
            }
        );
    }

    @Override
    public void create(Student student) throws DAOException {
        final String query = "INSERT INTO students(name, email, age, gender) VALUES(?, ?, ?, ?);";
        KeyHolder keyHolder = new KeyHolder();
        this.jdbcTemplate.update(
            query,
            ps -> {
                ps.setString(1, student.getName());
                ps.setString(2, student.getEmail());
                ps.setInt(3, student.getAge());
                ps.setString(4, String.valueOf((student.getGender())));
            },
            keyHolder
        );
        student.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) throws DAOException {
        final String query = "DELETE FROM students WHERE id = ?;";
        this.jdbcTemplate.update(query, ps -> {
            ps.setLong(1, id);
        });
    }

    @Override
    public void update(Student student) throws DAOException {
        final String query = "UPDATE students SET name = ?, email = ?, age = ? , gender = ? WHERE id = ?";
        this.jdbcTemplate.update(
            query,
            ps -> {
                ps.setString(1, student.getName());
                ps.setString(2, student.getEmail());
                ps.setInt(3, student.getAge());
                ps.setString(4, String.valueOf((student.getGender())));
                ps.setLong(5, student.getId());
            }
        );
    }

    @Override
    public Student getByEmai(String email) throws DAOException {
        final String query = "SELECT * FROM students WHERE email = ?";
        return this.jdbcTemplate.queryForObject(
            query,
            ps -> ps.setString(1, email),
            rs -> {
                Student student = new Student();
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setAge(rs.getInt("age"));
                String gender = rs.getString("gender");
                if (gender != null) {
                    student.setGender(gender.charAt(0));
                }
                return student;
            }
        );
    }
}
