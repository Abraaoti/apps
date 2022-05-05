package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.Cargo;
import br.com.cmil.controle.dominio.services.interfaces.ICargoService;
import br.com.cmil.controle.dominio.services.interfaces.IDepartamentoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author cmilseg
 */
@Controller
@RequestMapping("cargos")
public class CargoController {

    private final ICargoService iCargoService;
    private final IDepartamentoService iDepartamentoService;

    public CargoController(ICargoService iCargoService, IDepartamentoService iDepartamentoService) {
        this.iCargoService = iCargoService;
        this.iDepartamentoService = iDepartamentoService;
    }

    private final String CARGO = "rh/cargos/";

    @GetMapping({"", "/"})
    public ModelAndView abrirForm() {
        Map<String, Object> model = new HashMap<>();
        model.put("cargo", new Cargo());
        model.put("departamentos", iDepartamentoService.listAllDepartamento());
        return new ModelAndView(CARGO + "add-cargo", model);
    }

    @PostMapping("/salvar")
    public ModelAndView salvarCargo(Cargo cargo, RedirectAttributes redir) {
        // Map<String, Object> model = new HashMap<>();
        iCargoService.save(cargo);
       
        redir.addAttribute("sucesso", "Operação realizada com sucesso");
        // model.clear();
        return new ModelAndView("redirect:/cargos");
    }

    @PostMapping("/editar")
    public ModelAndView editarDepartamento(Cargo cargo, RedirectAttributes redir) {
        // Map<String, Object> model = new HashMap<>();
         iCargoService.update(cargo);
      
        redir.addAttribute("sucesso", "Operação realizada com sucesso");
        // model.clear();
        return new ModelAndView("redirect:/departamentos");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("cargo", iCargoService.buscarCargoPorId(id));
        return new ModelAndView(CARGO + "departamento", model);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        iCargoService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/departamentos", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getDepartamento(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        // model.put("status", iEspecialidadeService.buscarEspecialidade(request));
        model.put("error", "Acesso Negado");
        model.put("message", "Não tem permissão para acessar esta página");
        //return new ModelAndView(ESPECIALIDADE + "especialidade", model);
        return ResponseEntity.ok(iCargoService.buscarCargo(request));
    }

    @GetMapping("/titulo")
    public ResponseEntity<?> getEspecialidadesPorTermo(@RequestParam("termo") String termo) {
        List<String> cargo = iCargoService.buscarCargoByTermo(termo);
        return ResponseEntity.ok(cargo);
    }
    // @GetMapping("/datatables/server/medico/{id}")
    // public ResponseEntity<?> getEspecialidade(@PathVariable("id") Long id,HttpServletRequest request) {

    //   return ResponseEntity.ok(iDepartamentoService.buscarDepartamentoByFuncionario(id,request));
    // }
}
