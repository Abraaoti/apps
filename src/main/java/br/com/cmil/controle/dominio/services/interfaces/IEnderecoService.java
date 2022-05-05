package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Endereco;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ti
 */
public interface IEnderecoService {

    Endereco save(Endereco endereco);

    Endereco buscarEnderecoPorId(Long id);

    void delete(Long id);

    Endereco update(Endereco endereco);

    Map<String, Object> buscarTodos(HttpServletRequest request);   

    Endereco buscarPorCep(String cep);
   // Optional<Endereco> buscarPorCep(String cep);

}
