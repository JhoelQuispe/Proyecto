package repositorio.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import repositorio.AdministradorDao;

import domain.Administrador;
import domain.Tarea;

@Repository
public class DaoAdministradorJdbc extends DaoGenericoJdbc<Administrador, Integer> implements AdministradorDao {

	private final AdministradorMapper mapper = new AdministradorMapper();
	protected PasswordEncoder encoder = new Md5PasswordEncoder();

	@Override
	public void guardar(Administrador administrador) {
		administrador.setPassword(encoder.encodePassword(administrador.getPassword(), null));
	  super.guardar(administrador);
	}

	@Override
	public Administrador encontrarXnombre (String nombre) {
    String sql = "SELECT * FROM " + getTableName() + " WHERE nombre = :nombre";
    SqlParameterSource namedParameters = new MapSqlParameterSource("nombre", nombre);
    return jdbcTemplate.queryForObject(sql, getRowMapper(), namedParameters);
	}

	@Override
	public List<Administrador> filtrarXnombre(String nombre) {
    String sql = "SELECT * FROM " + getTableName() + " WHERE nombre LIKE :nombre";
    SqlParameterSource namedParameters = new MapSqlParameterSource("nombre", nombre);
    return jdbcTemplate.query(sql, getRowMapper(), namedParameters);
	}

	@Override
	protected SimpleJdbcInsert createJdbcInsert() {
		return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(getTableName()).usingGeneratedKeyColumns("id");
	}

	@Override
	protected RowMapper<Administrador> getRowMapper() {
		return mapper;
	}

	@Override
	protected String getTableName() {
		return Administrador.TABLE_NAME;
	}

	public final class AdministradorMapper implements RowMapper<Administrador> {
		public Administrador mapRow(ResultSet rs, int rowNum) throws SQLException {
			Administrador administrador = new Administrador();
			administrador.setId(rs.getInt("id"));
			administrador.setNombre(rs.getString("nombre"));
			administrador.setPassword(rs.getString("password"));
			return administrador;
		}
	}

}