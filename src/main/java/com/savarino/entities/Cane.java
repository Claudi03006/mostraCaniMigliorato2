package com.savarino.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Cane {
	



	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    
	    //@NotNull
	    @ManyToOne
	    @JoinColumn(name = "proprietario_id")
	    private Proprietario proprietario;
	    
	    private String nome;
	    private String razza;
	    private int eta;
	    private String sex;
	    private double peso;
	    
	    //@Min(value =0)
	    private int voto;

	    public Proprietario getProprietario() {
	        return proprietario;
	    }

	    public void setProprietario(Proprietario proprietario) {
	        this.proprietario = proprietario;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getRazza() {
	        return razza;
	    }

	    public void setRazza(String razza) {
	        this.razza = razza;
	    }

	    public int getEta() {
	        return eta;
	    }

	    public void setEta(int eta) {
	        this.eta = eta;
	    }

	    public String getSex() {
	        return sex;
	    }

	    public void setSex(String sex) {
	        this.sex = sex;
	    }

	    public double getPeso() {
	        return peso;
	    }

	    public void setPeso(double peso) {
	        this.peso = peso;
	    }

	    public int getVoto() {
	        return voto;
	    }

	    public void setVoto(int voto) {
	        this.voto = voto;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }


}
