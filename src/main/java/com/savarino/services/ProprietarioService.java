package com.savarino.services;

import java.util.List;

import com.savarino.entities.Proprietario;
import java.util.Optional;

public interface ProprietarioService {
        
	List<Proprietario>listaProprietari();
	void addProprietario(Proprietario proprietario);
        Optional<Proprietario> findByUsernameAndPassword(String u,String p);
        Optional<Proprietario> findById(int id);
}
