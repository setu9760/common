package com.mycompany.app;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mycompany.app.common.exception.RepositoryDataAccessException;
import com.mycompany.app.common.model.ModelA;
import com.mycompany.app.common.repository.ModelARepository;
import com.mycompany.app.common.repository.ModelARepositoryImpl;
import com.mycompany.app.common.repository.ModelARepositoryImpl.ModelARowMapper;

@RunWith(MockitoJUnitRunner.class)
public class ModelARepositoryTest {

	@Mock
	private JdbcTemplate jdbcTemplate;
	private ModelARepository repo1;
	
	@Before
	public void setUp() {
		repo1 = new ModelARepositoryImpl(jdbcTemplate);
	}
	
	@After
	public void tearDown() {

	}
	
	@Test
	public void testSave() throws Exception {
		when(jdbcTemplate.update(anyString(), anyVararg())).thenReturn(1);
		repo1.save(new ModelA("a", 1));
		verify(jdbcTemplate).update(anyString(), anyVararg());
		verifyNoMoreInteractions(jdbcTemplate);
	}
	
	@Test(expected=RepositoryDataAccessException.class)
	public void testSave_Exception() throws Exception {
		when(jdbcTemplate.update(anyString(), anyVararg())).thenThrow(new EmptyResultDataAccessException(1));
		repo1.save(new ModelA("a", 1));
	}
	
	@Test
	public void testGet() throws Exception {
		when(jdbcTemplate.queryForObject(anyString(), eq(new Object[] {"test"}) , any(ModelARowMapper.class))).thenReturn(new ModelA("test", 1));
		ModelA m = repo1.get("test");
		assertNotNull(m);
		verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[] {"test"}) , any(ModelARowMapper.class));
	}
	
	@Test(expected=RepositoryDataAccessException.class)
	public void testGet_Exception() throws Exception {
		when(jdbcTemplate.queryForObject(anyString(), eq(new Object[] {"test"}) , any(ModelARowMapper.class))).thenThrow(new DuplicateKeyException("test"));
		ModelA m = repo1.get("test");
		assertNotNull(m);
		verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[] {"test"}) , any(ModelARowMapper.class));
	}
}
