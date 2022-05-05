package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.CentroCusto;
import br.com.cmil.controle.dominio.services.interfaces.ICentroCustoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author cmilseg
 */
@Controller
@RequestMapping("centros")
public class CentroCustoController {

    private final ICentroCustoService iCentroCustoService;
    private final String CENTRO_CUSTO = "suprimento/centros/";

    public CentroCustoController(ICentroCustoService iCentroCustoService) {
        this.iCentroCustoService = iCentroCustoService;
    }

    @GetMapping({"", "/"})
    public ModelAndView abrirFormCentroCusto() {
        Map<String, Object> model = new HashMap<>();
        model.put("centro", new CentroCusto());
        return new ModelAndView(CENTRO_CUSTO + "centro_custo", model);
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(CentroCusto centro_custo, RedirectAttributes redir) {
        // Map<String, Object> model = new HashMap<>();
        CentroCusto dep = iCentroCustoService.save(centro_custo);
        System.out.println("Ok " + dep.getCentro());
        redir.addAttribute("sucesso", "Operação realizada com sucesso");
        // model.clear();
        return new ModelAndView("redirect:/centros");
    }

    @PostMapping("/editar")
    public ModelAndView editar(CentroCusto centro_custo, RedirectAttributes redir) {
        // Map<String, Object> model = new HashMap<>();
        CentroCusto cc = iCentroCustoService.update(centro_custo);
        System.out.println("Ok " + cc.getCentro());
        redir.addAttribute("sucesso", "Operação realizada com sucesso");
        // model.clear();
        return new ModelAndView("redirect:/centros");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("centro", iCentroCustoService.buscarCentroCustoPorId(id));
        return new ModelAndView(CENTRO_CUSTO + "centro_custo", model);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        iCentroCustoService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/departamentos", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getCentroCusto(HttpServletRequest request) {
        return ResponseEntity.ok(iCentroCustoService.buscarCentroCusto(request));
    }

    @GetMapping("/titulo")
    public ResponseEntity<?> getEspecialidadesPorTermo(@RequestParam("termo") String termo) {
        List<String> especialidade = iCentroCustoService.buscarCentroCustoByTermo(termo);
        return ResponseEntity.ok(especialidade);
    }

}
