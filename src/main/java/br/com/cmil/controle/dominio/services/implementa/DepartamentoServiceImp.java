package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.Departamento;
import br.com.cmil.controle.dominio.repositorys.IDepartamentoRepository;
import br.com.cmil.controle.dominio.services.interfaces.IDepartamentoService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author ti
 */
@Service
public class DepartamentoServiceImp implements IDepartamentoService {

    private final IDepartamentoRepository iDepartamentoRepository;
    private final Datatables datatables;

    @Autowired
    public DepartamentoServiceImp(IDepartamentoRepository iDepartamentoRepository, Datatables datatables) {
        this.iDepartamentoRepository = iDepartamentoRepository;
        this.datatables = datatables;
    }

    @Override
    public Departamento save(Departamento departamento) {
        if (departamento.getId() == null) {
            return add(departamento);
        }
        return null;
    }

    @Override
    public Iterable<Departamento> listAllDepartamento() {
       return  iDepartamentoRepository.findAll();
    }

    @Override
    public Departamento buscarDepartamentoPorId(Long id) {
       Optional<Departamento> departamento = iDepartamentoRepository.findById(id);
        if (departamento.isEmpty()) {
            return null;
        }
        return  departamento.get();
    }

    @Override
    public void delete(Long id) {
          Optional<Departamento> departamento = iDepartamentoRepository.findById(id);       
          iDepartamentoRepository.delete(departamento.get());
    }

    @Override
    public Departamento update(Departamento departamento) {
       Optional<Departamento> depa = iDepartamentoRepository.findById(departamento.getId());
        if (depa.isEmpty()) {
            return null;
        }
        var dep = depa.get();
        dep.setDepa(departamento.getDepa());
        dep.setId(departamento.getId());        
        return  iDepartamentoRepository.save(dep);
    }

    protected Departamento add(Departamento departamento) {
        Departamento dep = new Departamento();
        dep.setDepa(departamento.getDepa());
        return  iDepartamentoRepository.save(dep);
    }

    @Override
    public List<String> buscarDepartamentoByTermo(String termo) {
        return iDepartamentoRepository.findByDepartamentoTermo(termo);
    }

    @Override
    public Map<String, Object> buscarDepartamento(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.DEPARTAMENTOS);
        Page<?> page = datatables.getSearch().isEmpty() ? iDepartamentoRepository.findAll(datatables.getPageable())
                : iDepartamentoRepository.findAllByTitulo(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public Set<Departamento> buscarDepartamentoByTitulo(String[] titulo) {
        return iDepartamentoRepository.findByDepartamentoDepa(titulo);
    }
   
}
