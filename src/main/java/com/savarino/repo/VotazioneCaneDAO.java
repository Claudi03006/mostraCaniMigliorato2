/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.savarino.repo;

import com.savarino.entities.Cane;
import com.savarino.entities.Proprietario;
import com.savarino.entities.VotazioneCane;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author eddie
 */

@Repository
public  interface  VotazioneCaneDAO extends JpaRepository<VotazioneCane, Integer>  {
    List<VotazioneCane> findByProprietario(Proprietario p);
    Optional<VotazioneCane> findByProprietarioAndCane(Proprietario proprietario,Cane cane);
}
