package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.ContaPagar;
import br.com.cmil.controle.dominio.entidades.ProcessoFinanceiro;
import br.com.cmil.controle.dominio.enuns.Situacao;
import br.com.cmil.controle.dominio.repositorys.IContaPagarRepository;
import br.com.cmil.controle.dominio.services.interfaces.IContaPagarService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cmilseg
 */
@Service
@Transactional
public class ContaPagarServiceImplementa implements IContaPagarService {

    private final IContaPagarRepository iContaPagarRepository;
    private final Datatables datatables;

    @Autowired
    public ContaPagarServiceImplementa(IContaPagarRepository iContaPagarRepository, Datatables datatables) {
        this.iContaPagarRepository = iContaPagarRepository;
        this.datatables = datatables;
    }

    @Override
    public ContaPagar save(ContaPagar contaPagar) {
        if (contaPagar.getId() == null) {
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

                return addContaPagar(contaPagar);
            } while (parcela < totalparcela);

        }
        return update(contaPagar);
    }

    @Override
    public Iterable<ContaPagar> listAllContaPagar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ContaPagar buscarContaPagarPorId(Long id) {
        Optional<ContaPagar> contaPagarId = iContaPagarRepository.findById(id);
        if (contaPagarId.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        return contaPagarId.get();
    }

    @Override
    public void delete(Long id) {
        Optional<ContaPagar> contaPagarId = iContaPagarRepository.findById(id);
        if (contaPagarId.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        iContaPagarRepository.delete(contaPagarId.get());
    }

    @Override
    public ContaPagar update(ContaPagar contaPagar) {
        Optional<ContaPagar> contaPagarId = iContaPagarRepository.findById(contaPagar.getId());
        if (contaPagarId.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        ContaPagar pagar = contaPagarId.get();
        pagar.setProcessoFinanceiro(contaPagar.getProcessoFinanceiro());
        pagar.setValorPagar(contaPagar.getValorPagar());
        pagar.setVencimento(contaPagar.getVencimento());
        pagar.setData_pagamento(contaPagar.getData_pagamento());
        if (contaPagar.getVencimento().isAfter(LocalDate.now())) {
            if (pagar.getData_pagamento() == null) {
                pagar.setSituacao(Situacao.ABERTO);

            } else {
                pagar.setSituacao(Situacao.PAGO);
            }

        } else {
            pagar.setSituacao(Situacao.VENCIDA);
        }
        pagar.setBanco(contaPagar.getBanco());
        pagar.setDocumento(contaPagar.getDocumento());
        pagar.setObservacao(contaPagar.getObservacao());
        pagar.setTotal(contaPagar.getTotal());
        pagar.setId(contaPagar.getId());
        return iContaPagarRepository.save(pagar);
    }

    @Override
    public List<String> buscarContaPagarByValor(BigDecimal valor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> buscarContaPagarTermo(String titulo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Set<ProcessoFinanceiro> buscarContaPagarDocumento(String[] documento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<String, Object> buscarContaPagar(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.CONTAPAGAR);
        Page<?> page = datatables.getSearch().isEmpty() ? iContaPagarRepository.findAll(datatables.getPageable())
                : iContaPagarRepository.findAllBySearch(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public Map<String, Object> buscarProcessoFinanceiroByFornecedor(Long id, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected ContaPagar addContaPagar(ContaPagar contaPagar) {
        ContaPagar pagar = new ContaPagar();

        pagar.setProcessoFinanceiro(contaPagar.getProcessoFinanceiro());
        pagar.setValorPagar(contaPagar.getValorPagar());
        pagar.setVencimento(contaPagar.getVencimento());
        pagar.setData_pagamento(contaPagar.getData_pagamento());

        if (contaPagar.getVencimento().isAfter(LocalDate.now())) {
            if (pagar.getData_pagamento() == null) {
                pagar.setSituacao(Situacao.ABERTO);

            } else {
                pagar.setSituacao(Situacao.PAGO);
            }

        } else {
            pagar.setSituacao(Situacao.VENCIDA);
        }
        pagar.setBanco(contaPagar.getBanco());
        pagar.setDocumento(contaPagar.getDocumento());
        pagar.setObservacao(contaPagar.getObservacao());
        pagar.setTotal(contaPagar.getTotal());
        return iContaPagarRepository.save(pagar);

    }

    protected ContaPagar gerarParcela(ContaPagar contaPagar) {

        ContaPagar pagar = new ContaPagar();

        pagar.setValorPagar(contaPagar.getValorPagar());
        pagar.setProcessoFinanceiro(contaPagar.getProcessoFinanceiro());
        pagar.setVencimento(contaPagar.getVencimento());
        if (contaPagar.getVencimento().isAfter(LocalDate.now())) {
            pagar.setSituacao(Situacao.ABERTO);
        } else {
            pagar.setSituacao(Situacao.VENCIDA);
        }
        pagar.setBanco(contaPagar.getBanco());
        pagar.setDocumento(contaPagar.getDocumento());
        pagar.setObservacao(contaPagar.getObservacao());
        return iContaPagarRepository.save(pagar);

    }

    

    @Override
    public ContaPagar pagarConta(ContaPagar contaPagar) {
        Optional<ContaPagar> contaPagarId = iContaPagarRepository.findById(contaPagar.getId());
        if (contaPagarId.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        ContaPagar pagar = contaPagarId.get();
        pagar.setProcessoFinanceiro(contaPagar.getProcessoFinanceiro());
        pagar.setValorPagar(contaPagar.getValorPagar());

        pagar.setVencimento(contaPagar.getVencimento());
        pagar.setData_pagamento(contaPagar.getData_pagamento());
        if (contaPagar.getVencimento().isAfter(LocalDate.now())) {
            if (pagar.getData_pagamento() == null) {
                pagar.setSituacao(Situacao.ABERTO);

            } else {
                pagar.setSituacao(Situacao.PAGO);
            }

        } else {
            pagar.setSituacao(Situacao.VENCIDA);
        }
        pagar.setBanco(contaPagar.getBanco());
        pagar.setDocumento(contaPagar.getDocumento());
        pagar.setObservacao(contaPagar.getObservacao());
        return iContaPagarRepository.save(pagar);
    }

    @Override
    public ContaPagar buscarContaPagarPorDataInicialDataFinal(LocalDate dataInicial, LocalDate dataFinal) {
        return iContaPagarRepository.findDateInitAndDateFim(dataInicial, dataFinal);
    }

}
