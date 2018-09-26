package com.mycompany.app;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.app.common.repository.ModelARepository;
import com.mycompany.app.common.repository.ModelBRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AppContextTest {
	
	@Autowired
	ModelARepository repoA;
	@Autowired
	ModelBRepository repoB;
	
	@Test
	public void testContextLoads() {
		assertNotNull(repoA);
		assertNotNull(repoB);
	}
	
	@Configuration
	@ComponentScan("com.mycompany.app.common.repository")
	public static class Config{
		
		@Bean
		JdbcTemplate jdbcTemplate() {
			return Mockito.mock(JdbcTemplate.class);
		}
		
	}
}
