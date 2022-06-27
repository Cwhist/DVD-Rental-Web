package com.example.dvdrental.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    @GetMapping(path = "/")
//    public String home(Model model) {
//
//        model.addAttribute("intro", "DVD Rental");
//
//        return "home";
//    }

    @GetMapping("/return")
    public String returnPage() {

        return "return_page";
    }

}
