package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.Cargo;
import br.com.cmil.controle.dominio.entidades.Departamento;
import br.com.cmil.controle.dominio.repositorys.ICargoRepository;
import br.com.cmil.controle.dominio.services.interfaces.ICargoService;
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
public class CargoServiceImp implements ICargoService {

    private final ICargoRepository iCargoRepository;
    private final Datatables datatables;

    @Autowired
    public CargoServiceImp(ICargoRepository iCargoRepository, Datatables datatables) {
        this.iCargoRepository = iCargoRepository;
        this.datatables = datatables;
    }

    @Override
    public Cargo save(Cargo cargo) {
        if (cargo.getIdCargo()== null) {
            return add(cargo);
        }
        return update(cargo);
    }

    @Override
    public Iterable<Cargo> listAllCargo() {
        return iCargoRepository.findAll();
    }

    @Override
    public Cargo buscarCargoPorId(Long id) {
        Optional<Cargo> cargoId = iCargoRepository.findById(id);
        if (cargoId.isEmpty()) {
            return null;
        }
        return cargoId.get();
    }

    @Override
    public void delete(Long id) {
       Optional<Cargo> cargoId = iCargoRepository.findById(id);
        iCargoRepository.delete(cargoId.get());
    }

    @Override
    public Cargo update(Cargo cargos) {
        Optional<Cargo> cargoId = iCargoRepository.findById(cargos.getIdCargo());
        if (cargoId.isEmpty()) {
            return null;
        }
        var cargo = cargoId.get();
        cargo.setTitulo(cargos.getTitulo());
        for (Departamento departamento : cargos.getDepartamentos()) {
            cargo.addDepartamento(departamento);
        }
        cargo.setIdCargo(cargos.getIdCargo());        
        return iCargoRepository.save(cargo);
    }

    protected Cargo add(Cargo cargo) {
        Cargo carg = new Cargo();
        carg.setTitulo(cargo.getTitulo());
        for (Departamento departamento : cargo.getDepartamentos()) {
            carg.addDepartamento(departamento);
        }
        return iCargoRepository.save(carg);
    }

    @Override
    public List<String> buscarCargoByTermo(String termo) {
        return iCargoRepository.findByCargoTermo(termo);
    }

    @Override
    public Map<String, Object> buscarCargo(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.CARGO);
        Page<?> page = datatables.getSearch().isEmpty() ? iCargoRepository.findAll(datatables.getPageable())
                : iCargoRepository.findAllByTitulo(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public Set<Cargo> buscarCargoByTitulo(String[] titulo) {
        return iCargoRepository.findByCargoDepa(titulo);
    }

}
