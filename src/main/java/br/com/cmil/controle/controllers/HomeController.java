package br.com.cmil.controle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author cmilseg
 */
@Controller
public class HomeController {

    // abrir pagina home
    @GetMapping( "/home")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    

}
