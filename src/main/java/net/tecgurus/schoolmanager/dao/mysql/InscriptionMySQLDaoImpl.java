package net.tecgurus.schoolmanager.dao.mysql;

import net.tecgurus.schoolmanager.dao.IInscriptionDao;
import net.tecgurus.schoolmanager.dao.jdbc.jdbctemplate.JdbcTemplate;
import net.tecgurus.schoolmanager.dao.jdbc.jdbctemplate.KeyHolder;
import net.tecgurus.schoolmanager.exceptions.DAOException;
import net.tecgurus.schoolmanager.model.dto.InscriptionDto;

import java.util.List;

public class InscriptionMySQLDaoImpl implements IInscriptionDao {

    private final JdbcTemplate<InscriptionDto> jdbcTemplate = new JdbcTemplate<>();

    @Override
    public List<InscriptionDto> listByStudentId(Long id) throws DAOException {

        final String query =
            """
                SELECT 
                	i.id, i.student, i.course,s.name as studentName, 
                	c.name as courseName 
                FROM inscriptions i 
                	JOIN courses c ON i.course = c.id 
                	JOIN students s ON i.student = s.id 
                WHERE i.student = ?
                """;

        return this.jdbcTemplate.queryForList(
            query,
            ps -> {
                ps.setLong(1, id);
            },
            rs -> {
                InscriptionDto inscriptionDto = new InscriptionDto();
                inscriptionDto.setId(rs.getLong("id"));
                inscriptionDto.setStudent((rs.getLong("student")));
                inscriptionDto.setCourse(rs.getLong("course"));
                inscriptionDto.setStudentName(rs.getString("studentName"));
                inscriptionDto.setCourseName(rs.getString("courseName"));
                return inscriptionDto;
            }
        );
    }

    @Override
    public List<InscriptionDto> listByCourseId(Long courseId) throws DAOException {
        final String query = "SELECT * FROM inscriptions WHERE course = ? LIMIT 1";
        return this.jdbcTemplate.queryForList(
            query,
            ps -> ps.setLong(1, courseId),
            rs -> {
                InscriptionDto inscriptionDto = new InscriptionDto();
                inscriptionDto.setId(rs.getLong("id"));
                inscriptionDto.setStudent((rs.getLong("student")));
                inscriptionDto.setCourse(rs.getLong("course"));
                return inscriptionDto;
            }
        );
    }

    @Override
    public void create(InscriptionDto inscripcionDto) throws DAOException {
        final String query = "INSERT INTO inscriptions(student, course) VALUES(?, ?)";
        KeyHolder keyHolder = new KeyHolder();
        this.jdbcTemplate.update(
            query,
            ps -> {
                ps.setLong(1, inscripcionDto.getStudent());
                ps.setLong(2, inscripcionDto.getCourse());
            },
            keyHolder
        );
        inscripcionDto.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) throws DAOException {
        final String query = "DELETE FROM inscriptions WHERE id = ?";
        this.jdbcTemplate.update(query, ps -> {
            ps.setLong(1, id);
        });
    }

}
