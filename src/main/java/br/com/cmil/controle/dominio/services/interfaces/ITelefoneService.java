package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Telefone;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ti
 */
public interface ITelefoneService {

    Telefone save(Telefone telefone);

    Telefone buscarTelefonePorId(Long id);

    void delete(Long id);

    Telefone update(Telefone telefoneDTO);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    Telefone buscarPorNumero(String numero);

}
