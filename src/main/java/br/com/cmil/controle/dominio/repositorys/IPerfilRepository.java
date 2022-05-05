
package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cmilseg
 */
@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Long>{
    
}
