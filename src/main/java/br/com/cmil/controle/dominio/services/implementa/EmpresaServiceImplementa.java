package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.Empresa;
import br.com.cmil.controle.dominio.repositorys.IEmpresaRepository;
import br.com.cmil.controle.dominio.services.interfaces.IEmpresaService;
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
public class EmpresaServiceImplementa implements IEmpresaService {

    private final IEmpresaRepository iEmpresaRepository;

    private final Datatables datatables;

    @Autowired
    public EmpresaServiceImplementa(IEmpresaRepository iEmpresaRepository, Datatables datatables) {
        this.iEmpresaRepository = iEmpresaRepository;
        this.datatables = datatables;
    }

    @Override
    public Empresa save(Empresa empresa) {
        if (empresa.getId() == null) {
            return add(empresa);
        }
        return update(empresa);
    }

    @Override
    public Empresa buscarEmpresaPorId(Long id) {
        Optional<Empresa> empresaId = iEmpresaRepository.findById(id);
        if (empresaId.isEmpty()) {
            throw new UnsupportedOperationException("Não consta no nosso BD."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        return empresaId.get();
    }

    @Override
    public void delete(Long id) {
        Optional<Empresa> empresaId = iEmpresaRepository.findById(id);
        if (empresaId.isEmpty()) {
            throw new UnsupportedOperationException("Não consta no nosso BD.");
        }
        iEmpresaRepository.delete(empresaId.get());
    }

    @Override
    public Empresa update(Empresa empresa) {
        Optional<Empresa> empresaId = iEmpresaRepository.findById(empresa.getId());
        if (empresaId.isEmpty()) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        Empresa addempresa = empresaId.get();
        addempresa.setNome(empresa.getNome());
        addempresa.setSobrenome(empresa.getSobrenome());
        addempresa.setNascimento(empresa.getNascimento());
        addempresa.setCnpj(empresa.getCnpj());
        addempresa.setIe(empresa.getIe());
        addempresa.setIm(empresa.getIm());
        addempresa.setSocios(empresa.getSocios());
        addempresa.setCapital(empresa.getCapital());
        return iEmpresaRepository.save(addempresa);
    }

    @Override
    public List<String> buscarEmpresaTermo(String titulo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Set<Empresa> buscarEmpresaDocumento(String[] documento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<String, Object> buscarEmpresa(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.EMPRESA);
        Page<?> page = datatables.getSearch().isEmpty() ? iEmpresaRepository.findAll(datatables.getPageable())
                : iEmpresaRepository.findAllEmpresa(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public Map<String, Object> buscarEmpresaPor(Long id, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    protected Empresa add(Empresa empresa) {
        Empresa addempresa = new Empresa();
        addempresa.setNome(empresa.getNome());
        addempresa.setSobrenome(empresa.getSobrenome());
        addempresa.setNascimento(empresa.getNascimento());
        addempresa.setCnpj(empresa.getCnpj());
        addempresa.setIe(empresa.getIe());
        addempresa.setIm(empresa.getIm());
        addempresa.setSocios(empresa.getSocios());
        addempresa.setCapital(empresa.getCapital());
        return iEmpresaRepository.save(addempresa);
    }

    @Override
    public Iterable<Empresa> listAllEmpresa() {
       return iEmpresaRepository.findAll();
    }
}
