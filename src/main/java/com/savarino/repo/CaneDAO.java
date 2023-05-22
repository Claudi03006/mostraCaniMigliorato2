package com.savarino.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savarino.entities.Cane;
import com.savarino.entities.Proprietario;

public interface CaneDAO extends JpaRepository<Cane, Integer>{
	 List<Cane> findAllByOrderByVotoDesc();
         
         List<Cane> findByProprietarioOrderByVotoDesc(Proprietario p);
    

}
