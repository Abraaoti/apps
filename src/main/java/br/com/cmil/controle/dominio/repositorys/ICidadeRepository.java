package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ti
 */
public interface ICidadeRepository extends JpaRepository<Cidade,Long> {

}
