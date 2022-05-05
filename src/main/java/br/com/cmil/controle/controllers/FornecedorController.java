package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.Fornecedor;
import br.com.cmil.controle.dominio.services.interfaces.IFornecedorService;
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
@RequestMapping("fornecedores")
public class FornecedorController {

    private final IFornecedorService iFornecedorService;
    private final String FORNECEDOR = "adm/fornecedores/";

    public FornecedorController(IFornecedorService iFornecedorService) {
        this.iFornecedorService = iFornecedorService;
    }

    // abrir pagina home
    @GetMapping({"", "/"})
    public ModelAndView abrirFormFornecedor() {
        Map<String, Object> model = new HashMap<>();
        model.put("fornecedor", new Fornecedor());
        return new ModelAndView(FORNECEDOR + "add-fornecedor", model);
    }

    @GetMapping("/fornecedores")
    public ModelAndView fornecedores() {

        return new ModelAndView(FORNECEDOR + "fornecedores");
    }

    @PostMapping("/salvar")
    public String salvarFornecedor(Fornecedor fornecedor, RedirectAttributes redir) {
        // Map<String, Object> model = new HashMap<>();

        iFornecedorService.save(fornecedor);
        System.out.println("\nFuncionário: " + fornecedor.toString());

        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
        return "redirect:/fornecedores";
    }

    @PostMapping("/editar")
    public ModelAndView editarFornecedor(Fornecedor fornecedor, RedirectAttributes redir) {
        // Map<String, Object> model = new HashMap<>();
        Fornecedor dep = iFornecedorService.update(fornecedor);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        // model.clear();
        return new ModelAndView("redirect:/fornecedores");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("fornecedor", iFornecedorService.buscarFornecedorPorId(id));
        return new ModelAndView(FORNECEDOR + "add-fornecedor", model);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        iFornecedorService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/fornecedores", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getFornecedor(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        // model.put("status", iEspecialidadeService.buscarEspecialidade(request));
        model.put("error", "Acesso Negado");
        model.put("message", "Não tem permissão para acessar esta página");
        //return new ModelAndView(ESPECIALIDADE + "especialidade", model);  </div>
        return ResponseEntity.ok(iFornecedorService.buscarFornecedor(request));
    }

    @GetMapping("/titulo")
    public ResponseEntity<?> getFuncionariosPorTermo(@RequestParam("termo") String termo) {
        List<String> fornecedor = iFornecedorService.buscarFornecedorTermo(termo);
        return ResponseEntity.ok(fornecedor);
    }

}
