package it.unibz.infosec.examproject.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestPageController {
    
    @GetMapping("/")
    public String testPage(Model model) {
        model.addAttribute("name", "Mario");
        return "test";
    }

}
