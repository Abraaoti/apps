package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.ProcessoFinanceiro;
import br.com.cmil.controle.dominio.enuns.FormaPagamento;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.cmil.controle.dominio.repositorys.IProcessoFinanceiroRepository;
import br.com.cmil.controle.dominio.services.interfaces.IProcessoFinanceiroService;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author cmilseg
 */
@Service
@Transactional
public class ProcessoFinanceiroServiceImplementa implements IProcessoFinanceiroService {

    private final IProcessoFinanceiroRepository iProcessoPagarContaRepository;
    private final Datatables datatables;
    private final String PATHS;

    @Autowired
    public ProcessoFinanceiroServiceImplementa(IProcessoFinanceiroRepository iProcessoPagarContaRepository, Datatables datatables, @Value("${app.file.upload-dir}") String root) {
        this.iProcessoPagarContaRepository = iProcessoPagarContaRepository;
        this.datatables = datatables;
        this.PATHS = root;
    }

    @Override
    public ProcessoFinanceiro save(ProcessoFinanceiro processoPagarConta, MultipartFile arquivo) throws IOException {
        if (processoPagarConta.getId() == null) {
            return add(processoPagarConta, arquivo);
        }
        return update(processoPagarConta);
    }

    @Override
    public Iterable<ProcessoFinanceiro> listAllProcessoFinanceiro() {
        return iProcessoPagarContaRepository.findAll();
    }

    @Override
    public ProcessoFinanceiro buscarProcessoPagarContaPorId(Long id) {
        Optional<ProcessoFinanceiro> processoPagarContaId = iProcessoPagarContaRepository.findById(id);
        if (processoPagarContaId.isEmpty()) {
            return null;
        }
        return processoPagarContaId.get();
    }

    @Override
    public void delete(Long id) {
        Optional<ProcessoFinanceiro> processoPagarContaId = iProcessoPagarContaRepository.findById(id);
        iProcessoPagarContaRepository.delete(processoPagarContaId.get());
    }

    @Override
    public ProcessoFinanceiro update(ProcessoFinanceiro processoFinanceiros) throws IOException {
        Optional<ProcessoFinanceiro> processoFinanceiroId = iProcessoPagarContaRepository.findById(processoFinanceiros.getId());
        if (processoFinanceiroId.isEmpty()) {
            return null;
        }
        var processoFinanceiro = processoFinanceiroId.get();
        processoFinanceiro.setEmissao(processoFinanceiros.getEmissao());
        processoFinanceiro.setVencimento(processoFinanceiros.getVencimento());
        processoFinanceiro.setDocumento(processoFinanceiros.getDocumento());
        processoFinanceiro.setFornecedor(processoFinanceiros.getFornecedor());
        processoFinanceiro.setQtdparcela(processoFinanceiros.getQtdparcela());
        processoFinanceiro.setValor(processoFinanceiros.getValor());
        processoFinanceiro.setArquivo(processoFinanceiros.getArquivo());
        for (FormaPagamento value : FormaPagamento.values()) {
            if (value.equals(processoFinanceiros.getForma_pagamento())) {
                processoFinanceiro.setForma_pagamento(value);
            }
        }

        //processoPagarConta.setValorPagar(processoPagarContas.getValorTotal().divide(BigDecimal.valueOf(processoPagarContas.getParcela()), 3, RoundingMode.CEILING));
        // processoFinanceiro.setValorPagar(processoFinanceiro.getValorPagar());
        processoFinanceiro.setUsuario(processoFinanceiros.getUsuario());
        processoFinanceiro.setId(processoFinanceiros.getId());
        return iProcessoPagarContaRepository.save(processoFinanceiro);
    }

    @Override
    public List<String> buscarProcessoFinanceiroByValor(BigDecimal valor) {
        return iProcessoPagarContaRepository.findProcessoFinanceiroByValor(valor);
    }

    @Override
    public Set<ProcessoFinanceiro> buscarProcessoFinanceiroDocumento(String[] documento) {
        return iProcessoPagarContaRepository.findByProcessoFinanceiroDocumento(documento);
    }

    @Override
    public Map<String, Object> buscarProcessoFinanceiro(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.PROCESSOPAGARCONTA);
        Page<?> page = datatables.getSearch().isEmpty() ? iProcessoPagarContaRepository.findAll(datatables.getPageable())
                : iProcessoPagarContaRepository.findAllBySearch(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    protected ProcessoFinanceiro add(ProcessoFinanceiro processoFinanceiros, MultipartFile arquivo) throws IOException {
        ProcessoFinanceiro processoFinanceiro = new ProcessoFinanceiro();
        String fileName = StringUtils.cleanPath(arquivo.getOriginalFilename());
       

        processoFinanceiro.setArquivo(fileName);
        processoFinanceiro.setEmissao(processoFinanceiros.getEmissao());
        processoFinanceiro.setVencimento(processoFinanceiros.getVencimento());
        processoFinanceiro.setDocumento(processoFinanceiros.getDocumento());
        processoFinanceiro.setFornecedor(processoFinanceiros.getFornecedor());
        processoFinanceiro.setCentroCusto(processoFinanceiros.getCentroCusto());
        processoFinanceiro.setUsuario(processoFinanceiros.getUsuario());

        processoFinanceiro.setQtdparcela(processoFinanceiros.getQtdparcela());
        processoFinanceiro.setValor(processoFinanceiros.getValor());       
        for (FormaPagamento value : FormaPagamento.values()) {
            if (value.equals(processoFinanceiros.getForma_pagamento())) {
                processoFinanceiro.setForma_pagamento(value);
            }
        }

        Path uploadPath = Paths.get(PATHS);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try ( InputStream inputStream = arquivo.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName).toAbsolutePath().normalize();
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            return iProcessoPagarContaRepository.save(processoFinanceiro);

        } catch (IOException ioe) {
            throw new IOException("C: " + fileName, ioe);
        }

    }

    @Override
    public Map<String, Object> buscarProcessoFinanceiroByFornecedor(Long id, HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.PROCESSOPAGARCONTA);
        Page<?> page = iProcessoPagarContaRepository.findAByIdFornecedor(id, datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public List<String> buscarProcessoFinanceiroTermo(String titulo) {
        return iProcessoPagarContaRepository.findProcessoFinanceiroByTermo(titulo);
    }

    @Override
    public ProcessoFinanceiro buscarPorEmail(String email) {
        return iProcessoPagarContaRepository.findByUsuarioEmail(email).orElse(new ProcessoFinanceiro());
    }

}
