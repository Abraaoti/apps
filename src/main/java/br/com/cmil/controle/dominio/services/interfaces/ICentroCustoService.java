package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.CentroCusto;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cmilseg
 */
public interface ICentroCustoService {

    CentroCusto save(CentroCusto centro_custo);

    Iterable<CentroCusto> listAllCentroCusto();

    CentroCusto buscarCentroCustoPorId(Long id);

    void delete(Long id);

    CentroCusto update(CentroCusto centro_custo);

    List<String> buscarCentroCustoByTermo(String termo);

    Set<CentroCusto> buscarCentroCustoByTitulo(String[] titulo);

    Map<String, Object> buscarCentroCusto(HttpServletRequest request);

}
