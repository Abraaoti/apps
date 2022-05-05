package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.Categoria;
import br.com.cmil.controle.dominio.services.interfaces.ICategoriaService;
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
@RequestMapping("categorias")
public class CategoriaController {
private final ICategoriaService iCategoriaService;

    public CategoriaController(ICategoriaService iDepartamentoService) {
        this.iCategoriaService = iDepartamentoService;
    }


  private final String CATEGORIA = "suprimento/categoria/";
    @GetMapping({"", "/"})
    public ModelAndView abrirFormCategoria() {
        Map<String, Object> model = new HashMap<>();
        model.put("categoria", new Categoria());
        return new ModelAndView(CATEGORIA+"categoria", model);
    }
    
    

    @PostMapping("/salvar")
    public ModelAndView salvarDepartamento(Categoria categoria, RedirectAttributes redir) {
       // Map<String, Object> model = new HashMap<>();
        Categoria cat = iCategoriaService.save(categoria);
         System.out.println("Ok " + cat.getCate());
        redir.addAttribute("sucesso", "Operação realizada com sucesso");
       // model.clear();
        return new ModelAndView("redirect:/categorias");
    }
    @PostMapping("/editar")
    public ModelAndView editarCategoria(Categoria categoria, RedirectAttributes redir) {
       // Map<String, Object> model = new HashMap<>();
        Categoria dep = iCategoriaService.update(categoria);
         System.out.println("Ok " + dep.getCate());
        redir.addAttribute("sucesso", "Operação realizada com sucesso");
       // model.clear();
        return new ModelAndView("redirect:/categorias");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("categoria", iCategoriaService.buscarCategoriaPorId(id));
        return new ModelAndView(CATEGORIA + "categoria", model);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        iCategoriaService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/categorias", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getCategoria(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        // model.put("status", iEspecialidadeService.buscarEspecialidade(request));
        model.put("error", "Acesso Negado");
        model.put("message", "Não tem permissão para acessar esta página");
        //return new ModelAndView(ESPECIALIDADE + "especialidade", model);  </div>
        return ResponseEntity.ok(iCategoriaService.buscarCategoria(request));
    }
    @GetMapping("/titulo")
    public ResponseEntity<?> getCategoriasPorTermo(@RequestParam("termo") String termo) {
       List<String> especialidade = iCategoriaService.buscarCategoriaByTermo(termo);
        return ResponseEntity.ok(especialidade);
    }
   // @GetMapping("/datatables/server/medico/{id}")
   // public ResponseEntity<?> getEspecialidade(@PathVariable("id") Long id,HttpServletRequest request) {
        
     //   return ResponseEntity.ok(iDepartamentoService.buscarDepartamentoByFuncionario(id,request));
   // }

}




