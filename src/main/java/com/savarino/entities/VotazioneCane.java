package com.savarino.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

	@Entity
	public class VotazioneCane {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    private int voto;
	    
	   
	    @ManyToOne
	    @JoinColumn(name = "proprietario_id")
	    private Proprietario proprietario;
	    
	  //  @NotNull
	    @ManyToOne
	    @JoinColumn(name = "cane_id")
	    private Cane cane;

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public Proprietario getProprietario() {
	        return proprietario;
	    }

	    public void setProprietario(Proprietario proprietario) {
	        this.proprietario = proprietario;
	    }

	    public int getVoto() {
	        return voto;
	    }

	    public void setVoto(int voto) {
	        this.voto = voto;
	    }

	    public Cane getCane() {
	        return cane;
	    }

	    public void setCane(Cane cane) {
	        this.cane = cane;
	    }

}
