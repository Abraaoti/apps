
package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.entidades.Perfil;
import br.com.cmil.controle.dominio.repositorys.IPerfilRepository;
import br.com.cmil.controle.dominio.services.interfaces.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cmilseg
 */
@Service
@Transactional
public class PerfilServiceImplementa implements IPerfilService {
    private final IPerfilRepository iPerfilRepository;
@Autowired
    public PerfilServiceImplementa(IPerfilRepository iPerfilRepository) {
        this.iPerfilRepository = iPerfilRepository;
    }

    @Override
    public Iterable<Perfil> findAll() {
        return iPerfilRepository.findAll();
    }
    
} 
