
package com.application.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author abdelghani_bend
 */
@Entity
public class Ayd_cvm implements Serializable{

    @Id @GeneratedValue
    private Long id_ayd;
	@ManyToOne  @JoinColumn(name="nss")
	private Assure_cvm assure_cvm;
	@Column(length = 4)
	private String lp;
	private Date fin_pc;
	private String num_pc;
	private Date dateN;
	private String nom;
	private String prenom;

    public Ayd_cvm() {
    }

    public Ayd_cvm(Assure_cvm assure_cvm, String lp, Date fin_pc, String num_pc,Date dateN, String nom, String prenom) {
        this.assure_cvm = assure_cvm;
        this.lp = lp;
        this.fin_pc = fin_pc;
        this.num_pc = num_pc;
        this.dateN = dateN;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Assure_cvm getAssure_cvm() {
        return assure_cvm;
    }

    public String getLp() {
        return lp;
    }

    public Date getFin_pc() {
        return fin_pc;
    }

    public String getNum_pc() {
        return num_pc;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setAssure_cvm(Assure_cvm assure_cvm) {
        this.assure_cvm = assure_cvm;
    }

    public void setLp(String lp) {
        this.lp = lp;
    }

    public void setFin_pc(Date fin_pc) {
        this.fin_pc = fin_pc;
    }

    public void setNum_pc(String num_pc) {
        this.num_pc = num_pc;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Long getId_ayd() {
        return id_ayd;
    }

    
    
	public Date getDateN() {
		return dateN;
	}

	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}

	@Override
	public String toString() {
		return this.lp+": " +this.nom +" "+this.prenom;
	}

	

	
	
	
}
