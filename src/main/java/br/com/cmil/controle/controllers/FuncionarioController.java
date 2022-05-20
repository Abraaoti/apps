package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.Funcionario;
import br.com.cmil.controle.dominio.entidades.Pessoa;
import br.com.cmil.controle.dominio.services.interfaces.ICargoService;
import br.com.cmil.controle.dominio.services.interfaces.IEmpresaService;
import br.com.cmil.controle.dominio.services.interfaces.IFuncionarioService;
import java.time.LocalDate;
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
@RequestMapping("funcionarios")
public class FuncionarioController {

    private final IFuncionarioService iFuncionarioService;
    private final ICargoService iCargoService;
    private final IEmpresaService iEmpresaService;
    private final String FUNCIONARIO = "rh/funcionarios/";

    public FuncionarioController(IFuncionarioService iFuncionarioService, ICargoService iCargoService, IEmpresaService iEmpresaService) {
        this.iFuncionarioService = iFuncionarioService;
        this.iCargoService = iCargoService;
        this.iEmpresaService = iEmpresaService;
    }

    // abrir pagina home
    @GetMapping({"", "/"})
    public ModelAndView abrirFormFuncionaro() {
        Map<String, Object> model = new HashMap<>();
      
        model.put("funcionario", new Funcionario());
        model.put("cargos", iCargoService.listAllCargo());
        model.put("empresa", iEmpresaService.listAllEmpresa());
        return new ModelAndView(FUNCIONARIO + "add-funcionario", model);
    }

    @GetMapping("/funcionarios")
    public ModelAndView listarFuncionarios() {
        return new ModelAndView(FUNCIONARIO + "funcionarios");
    }

    @PostMapping("/salvar")
    public String salvar(Funcionario funcionario, RedirectAttributes redir) {
        // Map<String, Object> model = new HashMap<>();
        funcionario.setAdmissao(LocalDate.now());
        iFuncionarioService.save(funcionario);
        System.out.println("\nFuncionário: " + funcionario.toString());

        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
        return "redirect:/funcionarios";
    }

    @PostMapping("/editar")
    public ModelAndView editarFuncionario(Funcionario funcionario, RedirectAttributes redir) {
        // Map<String, Object> model = new HashMap<>();
       
        iFuncionarioService.update(funcionario);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        // model.clear();
        return new ModelAndView("redirect:/funcionarios");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("funcionario", iFuncionarioService.buscarFuncionarioPorId(id));
        model.put("cargos", iCargoService.listAllCargo());
        model.put("empresa", iEmpresaService.listAllEmpresa());
        return new ModelAndView(FUNCIONARIO + "add-funcionario", model);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        iFuncionarioService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/funcionarios", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getFuncionario(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        // model.put("status", iEspecialidadeService.buscarEspecialidade(request));
        model.put("error", "Acesso Negado");
        model.put("message", "Não tem permissão para acessar esta página");
        //return new ModelAndView(ESPECIALIDADE + "especialidade", model);  </div>
        return ResponseEntity.ok(iFuncionarioService.buscarFuncionario(request));
    }

    @GetMapping("/titulo")
    public ResponseEntity<?> getFuncionariosPorTermo(@RequestParam("termo") String termo) {
        List<String> especialidade = iFuncionarioService.buscarFuncionarioByTermo(termo);
        return ResponseEntity.ok(especialidade);
    }

}
