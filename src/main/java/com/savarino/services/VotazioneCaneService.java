/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.savarino.services;

import com.savarino.entities.Cane;
import com.savarino.entities.Proprietario;
import com.savarino.entities.VotazioneCane;
import java.util.Optional;

/**
 *
 * @author eddie
 */
public interface VotazioneCaneService {
   String salvaVotazioneCane(Proprietario p,Cane cane,int voto);
   Optional<VotazioneCane> findByProprietarioAndCane(Proprietario proprietario,Cane cane);
}
