package com.mycompany.app.common.service;

import com.mycompany.app.common.exception.RepositoryDataAccessException;
import com.mycompany.app.common.model.ModelA;
import com.mycompany.app.common.model.ModelB;

public interface ModelService {

	void save(ModelA a, ModelB b) throws RepositoryDataAccessException;
	
	ModelA getModelA(String name) throws RepositoryDataAccessException;
	
	ModelB getModelB(String name) throws RepositoryDataAccessException;
}
