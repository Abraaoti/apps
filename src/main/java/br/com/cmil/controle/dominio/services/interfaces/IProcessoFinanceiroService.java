package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.ProcessoFinanceiro;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cmilseg
 */
public interface IProcessoFinanceiroService {

    ProcessoFinanceiro save(ProcessoFinanceiro processoPagarConta)throws IOException;

    Iterable<ProcessoFinanceiro> listAllProcessoFinanceiro();

    ProcessoFinanceiro buscarProcessoPagarContaPorId(Long id);
  

    void delete(Long id);

    ProcessoFinanceiro update(ProcessoFinanceiro processoPagarConta)throws IOException;

    List<String> buscarProcessoFinanceiroByValor(BigDecimal valor);

   // List<ProcessoPagarConta> buscarByData(Date emissao, Date vencimento);

    List<String> buscarProcessoFinanceiroTermo(String titulo);

    Set<ProcessoFinanceiro> buscarProcessoFinanceiroDocumento(String[] documento);

    Map<String, Object> buscarProcessoFinanceiro(HttpServletRequest request);

    Map<String, Object> buscarProcessoFinanceiroByFornecedor(Long id, HttpServletRequest request);

    ProcessoFinanceiro buscarPorEmail(String email);

}
