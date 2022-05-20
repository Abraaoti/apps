package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.Empresa;
import br.com.cmil.controle.dominio.services.interfaces.IEmpresaService;
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
@RequestMapping("empresas")
public class EmpresaController {

    private final IEmpresaService iEmpresaService;  
    private final String EMPRESA = "adm/empresas/";

    public EmpresaController(IEmpresaService iEmpresaService) {
        this.iEmpresaService = iEmpresaService;
    }

    
    // abrir pagina home
    @GetMapping({"", "/"})
    public ModelAndView abrirFormEmpresa() {
        Map<String, Object> model = new HashMap<>();       
        model.put("empresa", new Empresa());
        return new ModelAndView(EMPRESA + "add-empresa", model);
    }

    @GetMapping("/empresas")
    public ModelAndView empresas() {

        return new ModelAndView(EMPRESA + "empresas");
    }

    @PostMapping("/salvar")
    public String salvarEmpresa(Empresa empresa, RedirectAttributes redir) {
        // Map<String, Object> model = new HashMap<>();       
       iEmpresaService.save(empresa);
        System.out.println("\nFuncionário: " + empresa.toString());

        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
        return "redirect:/empresas";
    }

   

    @PostMapping("/editar")
    public ModelAndView editarEmpresa(Empresa empresa, RedirectAttributes redir) {
        // Map<String, Object> model = new HashMap<>();
        Empresa dep = iEmpresaService.update(empresa);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        // model.clear();
        return new ModelAndView("redirect:/empresas");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("empresa", iEmpresaService.buscarEmpresaPorId(id));       
        return new ModelAndView(EMPRESA + "add-empresa", model);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        iEmpresaService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/empresas", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getEmpresa(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        // model.put("status", iEspecialidadeService.buscarEspecialidade(request));
        model.put("error", "Acesso Negado");
        model.put("message", "Não tem permissão para acessar esta página");
        //return new ModelAndView(ESPECIALIDADE + "especialidade", model);  </div>
        return ResponseEntity.ok(iEmpresaService.buscarEmpresa(request));
    }

    @GetMapping("/titulo")
    public ResponseEntity<?> getEmpresaPorTermo(@RequestParam("termo") String termo) {
        List<String> empresa = iEmpresaService.buscarEmpresaTermo(termo);
        return ResponseEntity.ok(empresa);
    }

}
