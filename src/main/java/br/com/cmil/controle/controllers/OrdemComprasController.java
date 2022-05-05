package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.OrdemCompra;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author cmilseg
 */
@Controller
@RequestMapping("oc")
public class OrdemComprasController {

  private final String ORDEM_COMPRA = "suprimento/oc/";
    @GetMapping("/dados")
    public ModelAndView abrirFormOrdemCompra() {
        Map<String, Object> model = new HashMap<>();
        model.put("oc", new OrdemCompra());
        return new ModelAndView(ORDEM_COMPRA+"cadastro", model);
    }

}
