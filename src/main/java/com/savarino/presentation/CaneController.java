package com.savarino.presentation;

//HTTP SESSION MANTIENE I DATI FRA UNA RICHIESTA HTTP E L'ALTRA
//È GRAZIE A HTTP SESSION CHE AD ESEMPIO È POSSIBILE GESTIRE UN CARRELLO ELETTRONICO
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaneController {

    @GetMapping("classifica")
    public String caniView(Model m) {
        //ACCESSO NON CONTROLLATO
        return "classifica";
    }
    
    @GetMapping("imieicani")
    public String imieiCaniView(Model m, HttpSession session) {
        //ACCESSO CONTROLLATO
        if(session.getAttribute("loginUser")==null){
            return "redirect:/"; //utente non loggato
        }
        
        return "imieicani";
    }
}
