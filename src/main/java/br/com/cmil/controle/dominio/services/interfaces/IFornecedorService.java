package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Fornecedor;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cmilseg
 */
public interface IFornecedorService {

    Fornecedor save(Fornecedor fornecedor);

    Iterable<Fornecedor> listAllFornecedor();

    Fornecedor buscarFornecedorPorId(Long id);

    void delete(Long id);

    Fornecedor update(Fornecedor fornecedor);

    List<String> buscarFornecedorTermo(String titulo);

    Set<Fornecedor> buscarFornecedorDocumento(String[] documento);

    Map<String, Object> buscarFornecedor(HttpServletRequest request);

    Map<String, Object> buscarFornecedorPor(Long id, HttpServletRequest request);

}
