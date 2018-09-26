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
import com.mycompany.app.common.model.ModelB;

@Repository
public class ModelBRepositoryImpl implements ModelBRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelBRepositoryImpl.class);

	private JdbcTemplate jdbcTemplate;
	private final RowMapper<ModelB> rowMapper;

	@Autowired
	public ModelBRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		rowMapper = new ModelBRowMapper();
	}

	public void save(ModelB model) throws RepositoryDataAccessException {
		LOGGER.info("Saving modelA : {}", model);
		try {
			jdbcTemplate.update("insert into model_b (name, num) values (?, ?)", model.getName(), model.getNumber());
		} catch (DataAccessException e) {
			LOGGER.error("Error occured while try to savbe modelB : {}", model);
			throw new RepositoryDataAccessException(e);
		}
	}

	public ModelB get(String name) throws RepositoryDataAccessException {
		LOGGER.info("getting modelA by name : {}", name);
		try {
			return jdbcTemplate.queryForObject("select name, num from model_b where name = ?", new Object[] { name }, rowMapper);
		} catch (DataAccessException e) {
			LOGGER.error("Error occured while try to get modelB by name : {}", name);
			throw new RepositoryDataAccessException(e);
		}
	}
	
	public class ModelBRowMapper implements RowMapper<ModelB> {

		public ModelB mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new ModelB(rs.getString(1), rs.getInt(2));
		}
	}
}
