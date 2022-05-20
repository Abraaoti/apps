package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.ContaPagar;
import br.com.cmil.controle.dominio.entidades.ProcessoFinanceiro;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cmilseg
 */
public interface IContaPagarService {

    ContaPagar save(ContaPagar contaPagar);

    Iterable<ContaPagar> listAllContaPagar();

    ContaPagar buscarContaPagarPorId(Long id);

    ContaPagar buscarContaPagarPorDataInicialDataFinal(LocalDate dataInicial, LocalDate dataFinal);

    // ProcessoFinanceiro buscarPorUsuarioEmail(String email);
    void delete(Long id);

    ContaPagar update(ContaPagar ContaPagar);

    ContaPagar pagarConta(ContaPagar ContaPagar);

    List<String> buscarContaPagarByValor(BigDecimal valor);

    // List<ProcessoPagarConta> buscarByData(Date emissao, Date vencimento);
    List<String> buscarContaPagarTermo(String titulo);

    Set<ProcessoFinanceiro> buscarContaPagarDocumento(String[] documento);

    Map<String, Object> buscarContaPagar(HttpServletRequest request);

    Map<String, Object> buscarProcessoFinanceiroByFornecedor(Long id, HttpServletRequest request);

}
