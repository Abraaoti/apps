package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.ContaPagar;
import br.com.cmil.controle.dominio.services.interfaces.IContaPagarService;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author cmilseg
 */
@Controller
@RequestMapping("pagarcontas")
public class ContaPagarController {

    private final IProcessoFinanceiroService iProcessoFinanceiroService;
    private final IContaPagarService iContaPagarService;

    public ContaPagarController(IProcessoFinanceiroService iProcessoFinanceiroService, IContaPagarService iContaPagarService) {
        this.iProcessoFinanceiroService = iProcessoFinanceiroService;
        this.iContaPagarService = iContaPagarService;
    }
    

    private final String CONTAPAGAR = "financeiro/contas/";

    @GetMapping("/novo")
    public ModelAndView form() {
        Map<String, Object> model = new HashMap<>();
        model.put("conta_a_pagar", new ContaPagar());
        return new ModelAndView(CONTAPAGAR + "parcelas", model);
    }
    

    @GetMapping("/pagar")
    public ModelAndView contas() {
        return new ModelAndView(CONTAPAGAR + "processos");
    }
    

    @PostMapping("/salvar")
    public String salvar(ContaPagar contaPagar, RedirectAttributes attr) {
        Integer parcela = 0;
        BigDecimal valor;
        Integer totalparcela = contaPagar.getParcela();
        LocalDate vencimento = LocalDate.from(contaPagar.getVencimento());
        BigDecimal valor_total = contaPagar.getValorPagar();
        ContaPagar conta = new ContaPagar();
        do {
            parcela += 1;
            valor = valor_total.divide(BigDecimal.valueOf(totalparcela), 3, RoundingMode.CEILING);
            conta.setValorPagar(valor);
            conta.setProcessoFinanceiro(contaPagar.getProcessoFinanceiro());
            contaPagar.setProcessoFinanceiro(contaPagar.getProcessoFinanceiro());
            conta.setVencimento(vencimento.plusMonths(parcela));
            conta.setData_pagamento(null);            
            conta.setBanco(contaPagar.getBanco());
            conta.setDocumento(contaPagar.getDocumento() + "/" + parcela);
            conta.setObservacao(contaPagar.getObservacao());
           

            iContaPagarService.save(conta);
        } while (parcela < totalparcela);
        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return "redirect:/pagarcontas/novo";
    }

    
    @PostMapping("/editar")
    public String update(ContaPagar contaPagar, RedirectAttributes attr) {
        iContaPagarService.update(contaPagar);

        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return "redirect:/pagarcontas/novo";
    }
    

    @GetMapping("/pagar/{id}")
    public ModelAndView pagarConta(@PathVariable("id") Long id, RedirectAttributes attr) {
        Map<String, Object> model = new HashMap<>();
        model.put("conta_a_pagar", iContaPagarService.buscarContaPagarPorId(id));
        return new ModelAndView(CONTAPAGAR + "pagar", model);
    }
    

    @PostMapping("/pagar")
    public String pagar(ContaPagar contaPagar, RedirectAttributes attr) {

        iContaPagarService.pagarConta(contaPagar);

        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return "redirect:/pagarcontas/novo";
    }
    

    @GetMapping("/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("conta_a_pagar", iContaPagarService.buscarContaPagarPorId(id));
        return new ModelAndView(CONTAPAGAR + "parcelas", model);
    }
    

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        iContaPagarService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/pagarcontas/novo", model);
    }
    

    @GetMapping("/datatables/server")
    public ResponseEntity<?> getContaPagar(HttpServletRequest request) {
        return ResponseEntity.ok(iContaPagarService.buscarContaPagar(request));
    }
    

    @GetMapping("/titulo")
    public ResponseEntity<?> getContaPagarTermo(@RequestParam("termo") String termo) {
        List<String> processopagarconta = iContaPagarService.buscarContaPagarTermo(termo);
        return ResponseEntity.ok(processopagarconta);
    }
   
    

    @GetMapping("/gerar/parcela/{id}")
    public ResponseEntity<?> gerar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(iProcessoFinanceiroService.buscarProcessoPagarContaPorId(id));
    }
    

    @GetMapping("/datatables/server/processo/{id}")
    public ResponseEntity<?> getContaPagarPorProcesso(@PathVariable("id") Long id, HttpServletRequest request) {

        return ResponseEntity.ok(iProcessoFinanceiroService.buscarProcessoFinanceiroByFornecedor(id, request));
    }

}
