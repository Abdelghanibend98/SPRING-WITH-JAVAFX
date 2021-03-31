package com.application.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.entities.Ayd_cvm;

public interface AydReposirtory extends JpaRepository<Ayd_cvm, String>{

	@Query("select a from Ayd_cvm a where a.assure_cvm.nss =:x")
	public ArrayList<Ayd_cvm> getByAssure(@Param("x")String id);
	
}
