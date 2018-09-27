package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Matchers.eq;
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
import com.mycompany.app.common.model.ModelB;
import com.mycompany.app.common.repository.ModelBRepository;
import com.mycompany.app.common.repository.ModelBRepositoryImpl;
import com.mycompany.app.common.repository.ModelBRepositoryImpl.ModelBRowMapper;

@RunWith(MockitoJUnitRunner.class)
public class ModelBRepositoryTest {
	@Mock
	private JdbcTemplate jdbcTemplate;
	private ModelBRepository repo1;
	
	@Before
	public void setUp() {
		repo1 = new ModelBRepositoryImpl(jdbcTemplate);
	}
	
	@After
	public void tearDown() {

	}
	
	@Test
	public void testSave() throws Exception {
		when(jdbcTemplate.update(anyString(), anyVararg())).thenReturn(1);
		repo1.save(new ModelB("a", 1));
		verify(jdbcTemplate).update(anyString(), anyVararg());
		verifyNoMoreInteractions(jdbcTemplate);
	}
	
	@Test(expected=RepositoryDataAccessException.class)
	public void testSave_Exception() throws Exception {
		when(jdbcTemplate.update(anyString(), anyVararg())).thenThrow(new EmptyResultDataAccessException(1));
		repo1.save(new ModelB("a", 1));
	}
	
	@Test
	public void testGet() throws Exception {
		ModelB mock = new ModelB("test", 1);
		when(jdbcTemplate.queryForObject(anyString(), eq(new Object[] {"test"}) , any(ModelBRowMapper.class))).thenReturn(mock);
		ModelB m = repo1.get("test");
		assertNotNull(m);
		assertEquals(mock, m);
		verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[] {"test"}) , any(ModelBRowMapper.class));
	}
	
	@Test(expected=RepositoryDataAccessException.class)
	public void testGet_Exception() throws Exception {
		when(jdbcTemplate.queryForObject(anyString(), eq(new Object[] {"test"}) , any(ModelBRowMapper.class))).thenThrow(new DuplicateKeyException("test"));
		ModelB m = repo1.get("test");
		assertNotNull(m);
		verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[] {"test"}) , any(ModelBRowMapper.class));
	}
}
