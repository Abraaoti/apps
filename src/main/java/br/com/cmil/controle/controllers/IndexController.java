package br.com.cmil.controle.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author cmilseg
 */
@Controller
public class IndexController {

    // abrir pagina home
    @GetMapping({"/", "/index"})
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/login-error")
    public ModelAndView loginErro() {
        Map<String, Object> model = new HashMap<>();
        model.put("alerta", "erro");
        model.put("titulo", "Credencias inválidas");
        model.put("texto", "Login ou Senha incorreta! tente novamente");
        model.put("subtexto", "Acesso permitido apenas para cadastros já ativados");
        return new ModelAndView("public/error/", model);
    }
    @GetMapping("/acesso-negado")
    public ModelAndView acessoNegado( HttpServletResponse response) {
        Map<String, Object> model = new HashMap<>();
        model.put("status", response.getStatus());
        model.put("error", "Acesso Negado");
        model.put("message", "Não tem permissão para acessar esta página");
        return new ModelAndView("acesso-negado", model);
    }

}
