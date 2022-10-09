package com.example.KangnamShare.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FirstController {
    @GetMapping("/ShareLogin")
    public String id(Model model){
        model.addAttribute( "username","문공표");
        return "login";
    }
    @GetMapping("/bye")
    public String seeYou(Model model){
        model.addAttribute( "nickname","문공표");
        return "goodbye";
    }
}
