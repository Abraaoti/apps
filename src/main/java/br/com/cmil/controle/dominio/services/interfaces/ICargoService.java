package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Cargo;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ti
 */
public interface ICargoService {

    Cargo save(Cargo cargo);

    Iterable<Cargo> listAllCargo();

    Cargo buscarCargoPorId(Long id);

    void delete(Long id);

    Cargo update(Cargo cargo);

    public List<String> buscarCargoByTermo(String termo);

    Set<Cargo> buscarCargoByTitulo(String[] titulo);

    Map<String, Object> buscarCargo(HttpServletRequest request);

}
