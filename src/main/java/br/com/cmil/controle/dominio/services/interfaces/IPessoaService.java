
package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Pessoa;

/**
 *
 * @author cmil
 */
public interface IPessoaService {
   Pessoa findByPessoaFisicaId(Long id);
}
