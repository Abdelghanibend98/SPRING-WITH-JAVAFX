package com.application.dao;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.entities.Assure_cvm;


public interface AssureRepository extends JpaRepository<Assure_cvm, String>{
	
	@Query("select a from Assure_cvm a where a.nss=:x")
	public Assure_cvm getById(@Param("x") String id);
	
	@Query("select a from Assure_cvm a where a.nss=:x")
	public ArrayList<Assure_cvm> getAllById(@Param("x") String id);

	@Query("select a from Assure_cvm a")
	public ArrayList<Assure_cvm> getAll();
}
