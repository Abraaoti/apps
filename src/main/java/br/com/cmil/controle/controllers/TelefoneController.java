package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.PessoaFisica;
import br.com.cmil.controle.dominio.entidades.Telefone;
import br.com.cmil.controle.dominio.services.interfaces.IFuncionarioService;
import br.com.cmil.controle.dominio.services.interfaces.ITelefoneService;
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
@RequestMapping("telefones")
public class TelefoneController {

    private final String TELEFONE = "rh/telefone/";
    private final ITelefoneService iTelefoneService;
    private final IFuncionarioService iFuncionarioService;

    public TelefoneController(ITelefoneService iTelefoneService, IFuncionarioService iFuncionarioService) {
        this.iTelefoneService = iTelefoneService;
        this.iFuncionarioService = iFuncionarioService;
    }

    @GetMapping("/novo/{id}")
    public ModelAndView novo(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        Telefone telefone = new Telefone();
        PessoaFisica pessoa = iFuncionarioService.buscarFuncionarioPorId(id);
        telefone.setPessoaTelefone(pessoa);
        model.put("telefone", telefone);
        return new ModelAndView(TELEFONE + "add-telefone", model);
    }

    @GetMapping("/lista")
    public ModelAndView telefones() {
        return new ModelAndView(TELEFONE + "telefones");
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(Telefone telefone, RedirectAttributes red) {
        if (telefone.getPessoaTelefone().getId() != null) {
            PessoaFisica pessoa = iFuncionarioService.buscarFuncionarioPorId(telefone.getPessoaTelefone().getId());
            telefone.setPessoaTelefone(pessoa);
        }
        Telefone telefone_pessoa_id = iTelefoneService.save(telefone);
        red.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/telefones/novo/" + telefone_pessoa_id.getPessoaTelefone().getId());
    }

    @PostMapping("/editar")
    public ModelAndView update(Telefone telefone, RedirectAttributes red) {
        if (telefone.getPessoaTelefone().getId() != null) {
            PessoaFisica pessoa = iFuncionarioService.buscarFuncionarioPorId(telefone.getPessoaTelefone().getId());
            telefone.setPessoaTelefone(pessoa);
        }
        Telefone telefone_pessoa_id = iTelefoneService.update(telefone);
        red.addFlashAttribute("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/telefones/novo/" + telefone_pessoa_id.getPessoaTelefone().getId());
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getTelefone(HttpServletRequest request) {
        return ResponseEntity.ok(iTelefoneService.buscarTodos(request));
    }

    @GetMapping("/editar/{id}")
    public ModelAndView preEditarTelefone(@PathVariable("id") Long telefoneId) {
        Map<String, Object> model = new HashMap<>();
        model.put("telefone", iTelefoneService.buscarTelefonePorId(telefoneId));
        return new ModelAndView(TELEFONE + "add-telefone", model);
    }
    @GetMapping("/excluir/{id}")
    public ModelAndView ecluirTelefone(@PathVariable("id") Long telefoneId) {
        Map<String, Object> model = new HashMap<>();
        iTelefoneService.delete(telefoneId);
        return new ModelAndView(TELEFONE + "add-telefone", model);
    }

}
