package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.Endereco;
import br.com.cmil.controle.dominio.entidades.PessoaFisica;
import br.com.cmil.controle.dominio.services.interfaces.IEnderecoService;
import br.com.cmil.controle.dominio.services.interfaces.IFuncionarioService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author cmilseg
 */
@Controller
@RequestMapping("enderecos")
public class EnderecoController {

    private final String ENDERECO = "rh/endereco/";
    private final IEnderecoService iEnderecoService;
    private final IFuncionarioService iFuncionarioService;

    public EnderecoController(IEnderecoService iEnderecoService, IFuncionarioService iFuncionarioService) {
        this.iEnderecoService = iEnderecoService;
        this.iFuncionarioService = iFuncionarioService;
    }

    @GetMapping("/novo/{id}")
    public ModelAndView novo(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        Endereco endereco = new Endereco();
        endereco.setPessoaEndereco(iFuncionarioService.buscarFuncionarioPorId(id));
        model.put("endereco", endereco);
        return new ModelAndView(ENDERECO + "add-endereco", model);
    }

    @GetMapping("/lista")
    public ModelAndView enderecos() {
        return new ModelAndView(ENDERECO + "enderecos");
    }

    @PostMapping("/salvar")
    public String salvar(Endereco endereco, RedirectAttributes red) {       
        PessoaFisica pessoa = iFuncionarioService.buscarFuncionarioPorId(endereco.getPessoaEndereco().getId());
        if (endereco.getPessoaEndereco().getId() != null) {
            endereco.setPessoaEndereco(pessoa);
        }
        Endereco endereco_pessoa_id =    iEnderecoService.save(endereco);
        red.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
        
        return "redirect:/enderecos/novo/" +endereco_pessoa_id.getPessoaEndereco().getId();
    }

    @PostMapping("/editar")
    public String update(Endereco endereco, RedirectAttributes red) {       
        Endereco endereco_pessoa_id =iEnderecoService.update(endereco);
        red.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
        return "redirect:/enderecos/novo/" + endereco_pessoa_id.getPessoaEndereco().getId();
    }

   
    @GetMapping("/editar/{id}")
    public ModelAndView preEditarEndereco(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("endereco", iEnderecoService.buscarEnderecoPorId(id));
        return new ModelAndView(ENDERECO + "add-endereco", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getEndereco(HttpServletRequest request) {
        return ResponseEntity.ok(iEnderecoService.buscarTodos(request));
    }


    @GetMapping("/datatables/server/enderecos")
    public ResponseEntity<?> enderecosDataTables(HttpServletRequest request) {
        return ResponseEntity.ok(iEnderecoService.buscarTodos(request));
    }


}
