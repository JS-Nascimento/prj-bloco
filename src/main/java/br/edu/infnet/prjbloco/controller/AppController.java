package br.edu.infnet.prjbloco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppController {

    @GetMapping(value = "/")
    public String indexForm() {
        return "index";
    }

    @GetMapping(value = "/home")
    public String mainForm() {

        return "index";
    }



}
