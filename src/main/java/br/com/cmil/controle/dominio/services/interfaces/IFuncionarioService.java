package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Funcionario;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ti
 */
public interface IFuncionarioService {

    Funcionario save(Funcionario funcionario);

    Iterable<Funcionario> findAllFuncionario();

    Funcionario buscarFuncionarioPorId(Long id);

    void delete(Long id);

    Funcionario update(Funcionario funcionario);

    List<String> buscarFuncionarioByTermo(String termo);

    Set<Funcionario> buscarFuncionarioByTitulo(String[] titulo);

    Map<String, Object> buscarFuncionario(HttpServletRequest request);

   
}
