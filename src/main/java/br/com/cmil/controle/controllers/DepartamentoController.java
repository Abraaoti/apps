package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.Departamento;
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
@RequestMapping("departamentos")
public class DepartamentoController {
private final IDepartamentoService iDepartamentoService;

    public DepartamentoController(IDepartamentoService iDepartamentoService) {
        this.iDepartamentoService = iDepartamentoService;
    }


  private final String DEPARTAMENTO = "departamento/";
  
   @GetMapping({"", "/"})
    public ModelAndView abrirFormDepartamento() {
        Map<String, Object> model = new HashMap<>();
        model.put("departamento", new Departamento());
        return new ModelAndView(DEPARTAMENTO + "departamento", model);
    }


    @PostMapping("/salvar")
    public ModelAndView salvarDepartamento(Departamento departamento, RedirectAttributes redir) {
       // Map<String, Object> model = new HashMap<>();
        Departamento dep = iDepartamentoService.save(departamento);
         System.out.println("Ok " + dep.getDepa());
        redir.addAttribute("sucesso", "Operação realizada com sucesso");
       // model.clear();
        return new ModelAndView("redirect:/departamentos");
    }
    @PostMapping("/editar")
    public ModelAndView editarDepartamento(Departamento departamento, RedirectAttributes redir) {
       // Map<String, Object> model = new HashMap<>();
        Departamento dep = iDepartamentoService.update(departamento);
         System.out.println("Ok " + dep.getDepa());
        redir.addAttribute("sucesso", "Operação realizada com sucesso");
       // model.clear();
        return new ModelAndView("redirect:/departamentos");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("departamento", iDepartamentoService.buscarDepartamentoPorId(id));
        return new ModelAndView(DEPARTAMENTO + "departamento", model);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        iDepartamentoService.delete(id);
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
        return ResponseEntity.ok(iDepartamentoService.buscarDepartamento(request));
    }
    @GetMapping("/titulo")
    public ResponseEntity<?> getEspecialidadesPorTermo(@RequestParam("termo") String termo) {
       List<String> especialidade = iDepartamentoService.buscarDepartamentoByTermo(termo);
        return ResponseEntity.ok(especialidade);
    }
   // @GetMapping("/datatables/server/medico/{id}")
   // public ResponseEntity<?> getEspecialidade(@PathVariable("id") Long id,HttpServletRequest request) {
        
     //   return ResponseEntity.ok(iDepartamentoService.buscarDepartamentoByFuncionario(id,request));
   // }

}

