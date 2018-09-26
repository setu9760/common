package com.mycompany.app.common.repository;

import com.mycompany.app.common.exception.RepositoryDataAccessException;
import com.mycompany.app.common.model.ModelB;

public interface ModelBRepository {

	void save(ModelB model) throws RepositoryDataAccessException;

	ModelB get(String name) throws RepositoryDataAccessException;
}
