package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.Fornecedor;
import br.com.cmil.controle.dominio.repositorys.IFornecedorRepository;
import br.com.cmil.controle.dominio.services.interfaces.IFornecedorService;
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
 * @author cmil
 */
@Service
@Transactional
public class FornecedorServiceImplementa implements IFornecedorService {

    private final Datatables datatables;
    private final IFornecedorRepository iFornecedorRepository;

    @Autowired
    public FornecedorServiceImplementa(Datatables datatables, IFornecedorRepository iFornecedorRepository) {
        this.datatables = datatables;
        this.iFornecedorRepository = iFornecedorRepository;
    }

    @Override
    public Fornecedor save(Fornecedor fornecedor) {
        if (fornecedor.getId() == null) {
            return add(fornecedor);
        }
        return update(fornecedor);
    }

    @Override
    public Iterable<Fornecedor> listAllFornecedor() {
        return iFornecedorRepository.findAll();
    }

    @Override
    public Fornecedor buscarFornecedorPorId(Long id) {
        Optional<Fornecedor> fornecedorId = iFornecedorRepository.findById(id);
        if (fornecedorId.isEmpty()) {
            throw new UnsupportedOperationException("Não consta no nosso BD");
        }
        return fornecedorId.get();
    }

    @Override
    public void delete(Long id) {
        Optional<Fornecedor> fornecedorId = iFornecedorRepository.findById(id);
        if (fornecedorId.isEmpty()) {
            throw new UnsupportedOperationException("Não consta no nosso BD");
        }
        iFornecedorRepository.delete(fornecedorId.get());
    }

    @Override
    public Fornecedor update(Fornecedor fornecedor) {
        Optional<Fornecedor> fornecedorId = iFornecedorRepository.findById(fornecedor.getId());
        if (fornecedorId.isEmpty()) {
            throw new UnsupportedOperationException("Não consta no nosso BD");
        }
        Fornecedor addFornecedor = fornecedorId.get();
        addFornecedor.setNome(fornecedor.getNome());
        addFornecedor.setSobrenome(fornecedor.getSobrenome());
        addFornecedor.setNascimento(fornecedor.getNascimento());
        addFornecedor.setCnpj(fornecedor.getCnpj());
        addFornecedor.setIe(fornecedor.getIe());
        addFornecedor.setIm(fornecedor.getIm());
        return iFornecedorRepository.save(addFornecedor);

    }

    @Override
    public List<String> buscarFornecedorTermo(String titulo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Set<Fornecedor> buscarFornecedorDocumento(String[] documento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<String, Object> buscarFornecedor(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.FORNECEDORES);
        Page<?> page = datatables.getSearch().isEmpty() ? iFornecedorRepository.findAll(datatables.getPageable())
                : iFornecedorRepository.findAllFornecedor(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public Map<String, Object> buscarFornecedorPor(Long id, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected Fornecedor add(Fornecedor fornecedor) {
        Fornecedor addFornecedor = new Fornecedor();
        addFornecedor.setNome(fornecedor.getNome());
        addFornecedor.setSobrenome(fornecedor.getSobrenome());
        addFornecedor.setNascimento(fornecedor.getNascimento());
        addFornecedor.setCnpj(fornecedor.getCnpj());
        addFornecedor.setIe(fornecedor.getIe());
        addFornecedor.setIm(fornecedor.getIm());
        return iFornecedorRepository.save(addFornecedor);
    }

}
