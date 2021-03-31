package com.application.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Assure_cvm implements Serializable{

	@Id  @Column(name="nss")
	private String nss;
	private String obs;
	private String nom;
	private String prenom;
	private Date dateN;
	private Date date_fin_pc;
	private String adresse;
	private String wilaya;
	private String daira;
	
	public Assure_cvm() {
		super();
	}


	public Assure_cvm(String nss, String obs, String nom, String prenom, Date dateN, Date date_fin_pc, String adresse,
			String wilaya, String daira) {
		super();
		this.nss = nss;
		this.obs = obs;
		this.nom = nom;
		this.prenom = prenom;
		this.dateN = dateN;
		this.date_fin_pc = date_fin_pc;
		this.adresse = adresse;
		this.wilaya = wilaya;
		this.daira = daira;
	}


	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateN() {
		return dateN;
	}

	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public Date getDate_fin_pc() {
		return date_fin_pc;
	}


	public void setDate_fin_pc(Date date_fin_pc) {
		this.date_fin_pc = date_fin_pc;
	}


	public String getWilaya() {
		return wilaya;
	}


	public void setWilaya(String wilaya) {
		this.wilaya = wilaya;
	}


	public String getDaira() {
		return daira;
	}


	public void setDaira(String daira) {
		this.daira = daira;
	}
	
	
	
}
