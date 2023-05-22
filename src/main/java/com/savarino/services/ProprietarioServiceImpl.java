package com.savarino.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savarino.entities.Proprietario;
import com.savarino.repo.ProprietarioDAO;
import java.util.Optional;

@Service
public class ProprietarioServiceImpl implements ProprietarioService {

    @Autowired
    private ProprietarioDAO proprietarioDAO;

    @Override
    public List<Proprietario> listaProprietari() {
        // TODO Auto-generated method stub
        return proprietarioDAO.findAll();
    }

    @Override
    public void addProprietario(Proprietario proprietario) {
        // TODO Auto-generated method stub
        proprietarioDAO.save(proprietario);

    }

    @Override
    public Optional<Proprietario> findByUsernameAndPassword(String u, String p) {
        return this.proprietarioDAO.findByUsernameAndPassword(u, p);
    }

    @Override
    public Optional<Proprietario> findById(int id) {
       return this.proprietarioDAO.findById(id);
    }

}
