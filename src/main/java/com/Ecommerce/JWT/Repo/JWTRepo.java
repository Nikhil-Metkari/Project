package com.Ecommerce.JWT.Repo;

import org.springframework.data.repository.CrudRepository;

import com.Ecommerce.JWT.model.DAOUser;

public interface JWTRepo extends CrudRepository<DAOUser,Integer>{
	public DAOUser findByusername(String username);
}
