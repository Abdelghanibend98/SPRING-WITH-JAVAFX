package com.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.dao.AssureRepository;
import com.application.entities.Assure_cvm;


@Service
public class AssureCvmServicesImp implements AssurCvmServices{

	@Autowired
	private AssureRepository assureRepository;
	
	@Override
	public Assure_cvm getByNSS(String nss) {
		return assureRepository.getById(nss);
	}

}
