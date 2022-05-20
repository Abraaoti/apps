
package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Perfil;

/**
 *
 * @author cmilseg
 */
public interface IPerfilService {
    
   // Perfil save(Perfil role);

    Iterable<Perfil> findAll();

   // Perfil findById(Long id);

   // void delete(Long id);
}
