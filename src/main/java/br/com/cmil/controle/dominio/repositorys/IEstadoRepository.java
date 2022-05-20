package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ti
 */
public interface IEstadoRepository extends JpaRepository<Estado,Long> {

}
