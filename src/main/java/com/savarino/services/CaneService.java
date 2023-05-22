package com.savarino.services;

import java.util.List;

import com.savarino.entities.Cane;
import com.savarino.entities.Proprietario;
import java.util.Optional;

public interface CaneService {
	List<Cane>findAllByOrderByVotoDesc();
	void addCane(Cane cane);
	List<Cane>findCaniByProprietario(Proprietario proprietario);
        void incrementaVotoCane(Cane cane,int voto);
        
        Optional<Cane> loadById(int id);
}
