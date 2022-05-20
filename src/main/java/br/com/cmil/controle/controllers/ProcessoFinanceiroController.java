package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.ProcessoFinanceiro;
import br.com.cmil.controle.dominio.services.interfaces.ICentroCustoService;
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
import br.com.cmil.controle.dominio.services.interfaces.IProcessoFinanceiroService;
import br.com.cmil.controle.dominio.services.interfaces.IUsuarioService;
import java.io.IOException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author cmilseg
 */
@Controller
@RequestMapping("processofinanceiro")
public class ProcessoFinanceiroController{
    
    private final IProcessoFinanceiroService iProcessoFinanceiroService;
    private final IFornecedorService iFornecedorService;
    private final ICentroCustoService iCentroCustoService;
    private final IUsuarioService iUsuarioService;
    
    public ProcessoFinanceiroController(IProcessoFinanceiroService iProcessoFinanceiroService, IFornecedorService iFornecedorService, ICentroCustoService iCentroCustoService, IUsuarioService iUsuarioService) {
        this.iProcessoFinanceiroService = iProcessoFinanceiroService;
        this.iFornecedorService = iFornecedorService;
        this.iCentroCustoService = iCentroCustoService;
        this.iUsuarioService = iUsuarioService;
    }
    
    private final String PROCESSO = "financeiro/contas/processo/";
  
    @GetMapping("/novo")
    public ModelAndView formProcessoFinanceiro(ProcessoFinanceiro processo, @AuthenticationPrincipal User user) {
        
        Map<String, Object> model = new HashMap<>();
        
        processo.setUsuario(iUsuarioService.buscarPorEmail(user.getUsername()));
        
        model.put("processo", processo);
        model.put("centroCustos", iCentroCustoService.listAllCentroCusto());
        model.put("fornecedores", iFornecedorService.listAllFornecedor());
        return new ModelAndView(PROCESSO + "add-processo", model);
    }
    
    @GetMapping("/processos")
    public ModelAndView listaProcessoFinanceiro() {
        return new ModelAndView(PROCESSO + "processos");
    }
    
    @PostMapping("/salvar")
    public String salvar(ProcessoFinanceiro processoFinanceiro,
            @RequestParam("file") MultipartFile arquivo,
            RedirectAttributes attr) throws IOException {       
      
        ProcessoFinanceiro processoId = iProcessoFinanceiroService.save(processoFinanceiro,arquivo);

        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso \n" + processoId.getArquivo() + "!");
        return "redirect:/processofinanceiro/novo";
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        
        model.put("processo", iProcessoFinanceiroService.buscarProcessoPagarContaPorId(id));
        model.put("centroCustos", iCentroCustoService.listAllCentroCusto());
        model.put("fornecedores", iFornecedorService.listAllFornecedor());
        return new ModelAndView(PROCESSO + "add-processo", model);
    }
    
    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        iProcessoFinanceiroService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/processofinanceiro/novo", model);
    }
    
    @GetMapping("/download/{fileId}")
    public ResponseEntity<?> downloadFile(@PathVariable Long fileId) throws Exception {       
        return  ResponseEntity.ok(iProcessoFinanceiroService.buscarProcessoPagarContaPorId(fileId));
    }
    
    
    @GetMapping("/gerar/parcela/{id}")
    public ModelAndView gerar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("conta_a_pagar", iProcessoFinanceiroService.buscarProcessoPagarContaPorId(id));
        return new ModelAndView(PROCESSO + "titulo", model);
    }
    
    @GetMapping("/datatables/server")
    public ResponseEntity<?> getProcessoFinanceiro(HttpServletRequest request) {
      
        return ResponseEntity.ok(iProcessoFinanceiroService.buscarProcessoFinanceiro(request));
    }
    
    @GetMapping("/titulo")
    public ResponseEntity<?> getProcessoFinanceiroPorTermo(@RequestParam("termo") String termo) {
        List<String> processopagarconta = iProcessoFinanceiroService.buscarProcessoFinanceiroTermo(termo);
        return ResponseEntity.ok(processopagarconta);
    }
    
    @GetMapping("/datatables/server/fornecedor/{id}")
    public ResponseEntity<?> getProcessoFinanceiroPorFornecedor(@PathVariable("id") Long id, HttpServletRequest request) {
        
        return ResponseEntity.ok(iProcessoFinanceiroService.buscarProcessoFinanceiroByFornecedor(id, request));
    }
    
}
