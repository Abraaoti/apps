package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Departamento;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ti
 */
public interface IDepartamentoService {

    Departamento save(Departamento departamento);

    Iterable<Departamento> listAllDepartamento();

    Departamento buscarDepartamentoPorId(Long id);

    void delete(Long id);

    Departamento update(Departamento departamento);

    public List<String> buscarDepartamentoByTermo(String termo);

    Set<Departamento> buscarDepartamentoByTitulo(String[] titulo);

    Map<String, Object> buscarDepartamento(HttpServletRequest request);

}
