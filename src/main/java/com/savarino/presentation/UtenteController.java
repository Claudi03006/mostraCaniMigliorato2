/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.savarino.presentation;

import com.savarino.dto.LoginDTO;
import com.savarino.entities.Proprietario;
import com.savarino.services.ProprietarioService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author eddie
 */
@Controller
@RequestMapping("/")
public class UtenteController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private ProprietarioService utenteService;
    
    @DeleteMapping(value = {"logout"})
    public String doLogout(HttpSession session) {
          System.out.println("Logout");
          session.removeAttribute("loginUser");
          session.invalidate();
          return "login";
    }

  
    //ENDPOINT MVC
    @GetMapping(value = {"index","/"})
    public String doLogin(Model m) {
          System.out.println("Login");
          

          m.addAttribute("loginDTO", new LoginDTO());
          return "login";
    }
    
    @PostMapping("login")
    public String doLogin(@RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,Model m, HttpSession session) {

        System.out.println("DO LOGIN");

        System.out.println(username);
        System.out.println(password);
        
        Optional<Proprietario> optUtente=utenteService.findByUsernameAndPassword(username, password);
        if(optUtente.isEmpty()){
            logger.warn("not founded user...");
            return "redirect:/";
        }
        else{
            System.out.println("user founded!!!");
            Proprietario utente=optUtente.get();
            System.out.println("User id: "+utente.getId()+"/"+utente.getUsername());
            m.addAttribute("loginUser",  utente);
            session.setAttribute("loginUser", utente);
            return "imieicani";
        }

        

    }
}
