package com.savarino.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.savarino.entities.Proprietario;
import java.util.Optional;

public interface ProprietarioDAO extends JpaRepository<Proprietario, Integer>  {

    Optional<Proprietario> findByUsernameAndPassword(String u,String p);
}
