package br.edu.infnet.prjbloco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AppController {

    @GetMapping(value = "/")
    public String mainForm(){
        return "index" ;
    }


}
