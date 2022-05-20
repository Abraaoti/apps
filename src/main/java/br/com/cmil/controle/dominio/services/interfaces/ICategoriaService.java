package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Categoria;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ti
 */
public interface ICategoriaService {

    Categoria save(Categoria categoria);

    Iterable<Categoria> findLista();

    Categoria buscarCategoriaPorId(Long id);

    List<String> buscarCategoriaByTermo(String termo);

    Set<Categoria> buscarCategoriaByTitulo(String[] titulo);

    void delete(Long id);

    Categoria update(Categoria categoria);

    Map<String, Object> buscarCategoria(HttpServletRequest request);

   
}
