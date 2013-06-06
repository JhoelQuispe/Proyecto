package repositorio.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.Date;

import domain.Tarea;

import repositorio.TareaDao;

@Repository
public class DaoTareaJdbc extends DaoGenericoJdbc < Tarea, Integer> implements TareaDao{

	private final TareaMapper mapper = new TareaMapper();
	protected PasswordEncoder encoder = new Md5PasswordEncoder();
	
	
	@Override
	public void guardar(Tarea tarea) {
	  super.guardar(tarea);
	}
	
	
	@Override
	public Tarea encontrarXfecha (Date fecha) {
    String sql = "SELECT * FROM " + getTableName() + " WHERE fecha = :fecha";
    SqlParameterSource namedParameters = new MapSqlParameterSource("fecha", fecha);
    return jdbcTemplate.queryForObject(sql, getRowMapper(), namedParameters);
	}

	@Override
	public List<Tarea> filtrarXfecha (Date fecha) {
    String sql = "SELECT * FROM " + getTableName() + " WHERE fecha LIKE :fecha";
    SqlParameterSource namedParameters = new MapSqlParameterSource("fecha", fecha);
    return jdbcTemplate.query(sql, getRowMapper(), namedParameters);
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<Tarea> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Tarea.TABLE_NAME;
	}

	public final class TareaMapper implements RowMapper<Tarea> {
		public Tarea mapRow(ResultSet rs, int rowNum) throws SQLException {
			Tarea tarea = new Tarea();
			tarea.setId(rs.getInt("id"));
			tarea.setDescripcion(rs.getString("descripcion"));
			tarea.setFecha(rs.getDate("fecha"));
			return tarea;
		}
	}
}
