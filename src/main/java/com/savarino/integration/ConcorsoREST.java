package com.savarino.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savarino.entities.Cane;
import com.savarino.entities.Proprietario;
import com.savarino.entities.VotazioneCane;
import com.savarino.services.CaneService;
import com.savarino.services.ProprietarioService;
import com.savarino.services.VotazioneCaneService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api")
public class ConcorsoREST {

    @Autowired
    private ProprietarioService proprietarioService;

    @Autowired
    private CaneService caneService;

    @Autowired
    private VotazioneCaneService votazioneCaneService;
    
    @GetMapping("proprietari")
    public List<Proprietario> getProprietari() {
        return proprietarioService.listaProprietari();
    }

    @GetMapping("caniClassificaUtenteCorrente")
    public List<Cane> getCaniUtenteCorrente(HttpSession s) {

        System.out.println("-GET CANI UTENTE CORRENTE-");
        
        Proprietario utenteCorrente = (Proprietario) s.getAttribute("loginUser");

        if (utenteCorrente != null) {
            List<Cane> cani = caneService.findCaniByProprietario(utenteCorrente);
            return cani;
        } else {
            System.err.println("-UTENTE CORRENTE NON TROVATO-");
            return new java.util.ArrayList<>();
        }

    }

    @GetMapping("caniClassifica")
    public List<Cane> getCaniClassifica() {
        List<Cane> cani = caneService.findAllByOrderByVotoDesc();
        return cani;
    }

    @PostMapping(value = "nuoviCani", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> addCane(@ModelAttribute Cane cane) {
        caneService.addCane(cane);
        return ResponseEntity.status(HttpStatus.CREATED).body(Integer.valueOf(cane.getId()).toString());
    }

    @PostMapping(value = "nuoviProprietari", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> addProprietario(@ModelAttribute Proprietario proprietario) {
        proprietarioService.addProprietario(proprietario);
        return ResponseEntity.status(HttpStatus.CREATED).body(Integer.valueOf(proprietario.getId()).toString());
    }

    @PostMapping(value = "votaCane/{idCane}/{voto}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addVotoCane(HttpSession s,@PathVariable int idCane,@PathVariable int voto) {
        System.out.println("Id cane: "+idCane+" Voto: "+voto);
        
        //1)Per prima cosa mi debbo accertare che l'id del cane esista veramente sul db
        Optional<Cane> optCane=this.caneService.loadById(idCane);
        if(optCane.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Integer.toString(idCane));
        }
        
        //2)A questo punto il cane cè sul db
        //col .get recupero il cane dall'optional
        //l'optional lo posso vedere come un wrapper di un oggetto generico
        //il .get mi recupera l'oggetto dal wrapper
        Cane cane=optCane.get();
        
        //3)Carico l'utente corrente dalla sessione
        //questo oggetto è unmanaged secondo jpa
        //cioè non è "legato al database"
        //cioè non è gestito dal container jpa(PersistenceContext)
        Proprietario utenteCorrente = (Proprietario) s.getAttribute("loginUser");
        
        //4)Controllo se l'utente corrente della sessione
        //ha già votato per quel cane...
        Optional<VotazioneCane> optV=this.votazioneCaneService.findByProprietarioAndCane(utenteCorrente, cane);
        if(!optV.isEmpty()){
           return ResponseEntity.status(HttpStatus.CONFLICT).body(Integer.toString(idCane)); 
        }

        //5)Qui ci arrivo se l'utente corrente non ha mai votato per quel cane...
        
        //mi carico l'utente dal database usando l'utente corrente.
        //l'utente restituito è un oggetto managed jpa quindi
        //presente nel suo persistence context
        utenteCorrente =this.proprietarioService.findById(utenteCorrente.getId()).get();
        
        //6)Incremento il voto del cane
        this.caneService.incrementaVotoCane(cane, voto);
        
        //7)Ora mi salvo una nuova votazione cane
        //dove ho il cane, l'utente del sito che ha votato il cane e il voto dato
        this.votazioneCaneService.salvaVotazioneCane(utenteCorrente,cane, voto);
        return ResponseEntity.status(HttpStatus.OK).body(Integer.toString(idCane));
    }
}
