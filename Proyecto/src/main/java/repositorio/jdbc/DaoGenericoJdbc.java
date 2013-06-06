package repositorio.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import repositorio.DaoGenerico;

import domain.EntidadBase;

public abstract class DaoGenericoJdbc<T extends EntidadBase<PK>, PK extends Number> implements DaoGenerico<T, PK> {

	protected JdbcTemplate jdbcTemplate;
	protected SimpleJdbcInsert jdbcInsert;
	
	protected abstract SimpleJdbcInsert createJdbcInsert();

	protected abstract RowMapper<T> getRowMapper();

	protected abstract String getTableName();

	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcInsert = createJdbcInsert();
    }
    
	@Override
    public long contar() {
        String sql = "select count(*) from " + getTableName();
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

	@Override
	@SuppressWarnings("unchecked")
    public void guardar(T entity) {
    	Number generatedId = jdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(entity));
    	entity.setId((PK)generatedId);
    }
	
	@Override
	public boolean existe(PK id) {
        String sql = "SELECT id FROM " + getTableName() + " WHERE id = ?";
        return jdbcTemplate.queryForRowSet(sql, id).next();
	}
	
	@Override
	public T encontrar(PK id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, getRowMapper(), id);
	}
	
	@Override
	public List<T> encontrarTodos() {
        String sql = "SELECT * FROM " + getTableName();
        return jdbcTemplate.query(sql, getRowMapper());
	}
	
	
	@Override
	public void borrar(T entity) {
		borrarXid(entity.getId());
	}
	
	@Override
	public void borrarXid(PK id) {
		jdbcTemplate.update("DELETE FROM " + getTableName() + " WHERE id = ?", id);
	}
}
