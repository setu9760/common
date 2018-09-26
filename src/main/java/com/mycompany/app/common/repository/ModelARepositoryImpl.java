package com.mycompany.app.common.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mycompany.app.common.exception.RepositoryDataAccessException;
import com.mycompany.app.common.model.ModelA;

@Repository
public class ModelARepositoryImpl implements ModelARepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelARepositoryImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	private final RowMapper<ModelA> rowMapper;
	
	@Autowired
	public ModelARepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		rowMapper = new ModelARowMapper();
	}

	public void save(ModelA model) throws RepositoryDataAccessException {
		LOGGER.info("Saving modelA : {}", model);
		try {
			jdbcTemplate.update("insert into model_a (name, num) values (?, ?)", model.getName(), model.getNumber());
		} catch (DataAccessException e) {
			LOGGER.error("Error occured while try to savbe modelA : {}", model);
			throw new RepositoryDataAccessException(e);
		}
	}

	public ModelA get(String name) throws RepositoryDataAccessException {
		LOGGER.info("getting modelA by name : {}", name);
		try {
			return jdbcTemplate.queryForObject("select name, num from model_a where name = ?", new Object[] { name }, rowMapper);
		} catch (DataAccessException e) {
			LOGGER.error("Error occured while try to get modelA by name : {}", name);
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public class ModelARowMapper implements RowMapper<ModelA> {

		public ModelA mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new ModelA(rs.getString(1), rs.getInt(2));
		}
	}
}
