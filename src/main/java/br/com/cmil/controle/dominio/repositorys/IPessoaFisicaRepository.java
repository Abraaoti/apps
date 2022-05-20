
package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cmilseg
 */
@Repository
public interface IPessoaFisicaRepository extends JpaRepository<PessoaFisica, Long>{
    
}
