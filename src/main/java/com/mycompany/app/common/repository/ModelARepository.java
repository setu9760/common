package com.mycompany.app.common.repository;

import com.mycompany.app.common.exception.RepositoryDataAccessException;
import com.mycompany.app.common.model.ModelA;

public interface ModelARepository {

	void save(ModelA model) throws RepositoryDataAccessException;

	ModelA get(String name) throws RepositoryDataAccessException;
}
