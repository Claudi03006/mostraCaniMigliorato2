package com.savarino.presentation;

import org.springframework.stereotype.Controller;

@Controller
public class ConcorsoView {

    
    
    public String classificaView() {
        System.out.println("INDEX");
        return "index";
    }

}
