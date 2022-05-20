package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.OrdemCompra;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ti
 */
public interface IOrdemCompraRepository extends JpaRepository<OrdemCompra,Long> {

}
