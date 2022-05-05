package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.CentroCusto;
import br.com.cmil.controle.dominio.repositorys.ICentroCustoRepository;
import br.com.cmil.controle.dominio.services.interfaces.ICentroCustoService;
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
 * @author ti
 */
@Service
@Transactional
public class CentroCustoServiceImp implements ICentroCustoService {

    private final ICentroCustoRepository iCentroCustoRepository;
    private final Datatables datatables;

    @Autowired
    public CentroCustoServiceImp(ICentroCustoRepository iCentroCustoRepository, Datatables datatables) {
        this.iCentroCustoRepository = iCentroCustoRepository;
        this.datatables = datatables;
    }

    @Override
    public CentroCusto save(CentroCusto centro_custo) {
        if (centro_custo.getId() == null) {
            return add(centro_custo);
        }
        return null;
    }

    @Override
    public Iterable<CentroCusto> listAllCentroCusto() {
        return iCentroCustoRepository.findAll();
    }

    @Override
    public CentroCusto buscarCentroCustoPorId(Long id) {
        return iCentroCustoRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        CentroCusto centroCustoId = iCentroCustoRepository.findById(id).get();
        iCentroCustoRepository.delete(centroCustoId);
    }

    @Override
    public CentroCusto update(CentroCusto centro_custo) {
        Optional<CentroCusto> centroCustoId = iCentroCustoRepository.findById(centro_custo.getId());
        if (centroCustoId.isEmpty()) {
            return null;
        }
        var centro = centroCustoId.get();
        centro.setCentro(centro_custo.getCentro());
        centro.setId(centro_custo.getId());
        return iCentroCustoRepository.save(centro);
    }

    @Override
    public List<String> buscarCentroCustoByTermo(String termo) {
        return iCentroCustoRepository.findByCentroCustoTermo(termo);
    }

    @Override
    public Set<CentroCusto> buscarCentroCustoByTitulo(String[] titulo) {
        return iCentroCustoRepository.findByCentroCustoTitulo(titulo);
    }

    @Override
    public Map<String, Object> buscarCentroCusto(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.CENTROCUSTO);
        Page<?> page = datatables.getSearch().isEmpty() ? iCentroCustoRepository.findAll(datatables.getPageable())
                : iCentroCustoRepository.findAllByTitulo(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    protected CentroCusto add(CentroCusto centroCusto) {
        CentroCusto centro = new CentroCusto();
        centro.setCentro(centroCusto.getCentro());
        return iCentroCustoRepository.save(centro);
    }

}
