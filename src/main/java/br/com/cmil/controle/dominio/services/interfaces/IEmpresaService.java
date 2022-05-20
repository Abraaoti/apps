package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Empresa;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cmil
 */
public interface IEmpresaService {

    Empresa save(Empresa empresa);

    Iterable<Empresa> listAllEmpresa();

    Empresa buscarEmpresaPorId(Long id);

    void delete(Long id);

    Empresa update(Empresa empresa);

    List<String> buscarEmpresaTermo(String titulo);

    Set<Empresa> buscarEmpresaDocumento(String[] documento);

    Map<String, Object> buscarEmpresa(HttpServletRequest request);

    Map<String, Object> buscarEmpresaPor(Long id, HttpServletRequest request);

}
