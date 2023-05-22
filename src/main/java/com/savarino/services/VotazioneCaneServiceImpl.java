/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.savarino.services;

import com.savarino.entities.Cane;
import com.savarino.entities.Proprietario;
import com.savarino.entities.VotazioneCane;
import com.savarino.repo.VotazioneCaneDAO;
import jakarta.annotation.Resource;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author eddie
 */

@Service
public class VotazioneCaneServiceImpl implements VotazioneCaneService{
    
    @Resource
    VotazioneCaneDAO votazioneCaneDAO;

    @Override
    public String salvaVotazioneCane(Proprietario p, Cane cane, int voto) {
        VotazioneCane v=new VotazioneCane();//questo è un oggetto jpa unmanaged
        v.setCane(cane);
        v.setProprietario(p);
        v.setVoto(voto);
        VotazioneCane v1=this.votazioneCaneDAO.save(v);//è un oggetto jpa managed
        return v1.getId()+"";
    }

    @Override
    public Optional<VotazioneCane> findByProprietarioAndCane(Proprietario proprietario, Cane cane) {
        return this.votazioneCaneDAO.findByProprietarioAndCane(proprietario, cane);
    }
}
