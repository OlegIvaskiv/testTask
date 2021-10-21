package com.testTask.dao;

import com.testTask.exceptions.DaoException;
import com.testTask.model.Role;

public interface RoleDao {
	Role getById(int id) throws DaoException;

	Role getByName(String name) throws DaoException;

}
