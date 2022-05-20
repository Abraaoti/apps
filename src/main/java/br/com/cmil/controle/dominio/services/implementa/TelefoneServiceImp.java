package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.Telefone;
import br.com.cmil.controle.dominio.repositorys.ITelefoneRepository;
import br.com.cmil.controle.dominio.services.interfaces.ITelefoneService;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ti
 */
@Service
@Transactional
public class TelefoneServiceImp implements ITelefoneService {

    private final ITelefoneRepository iTelefoneRepository;
    private final Datatables datatables;

    @Autowired
    public TelefoneServiceImp(ITelefoneRepository iTelefoneRepository, Datatables datatables) {
        this.iTelefoneRepository = iTelefoneRepository;
        this.datatables = datatables;
    }

    @Override
    public Telefone save(Telefone telefone) {
        if (telefone.getTelefoneId() == null) {
            return add(telefone);
        } else {
            return update(telefone);
        }
    }

 

    @Override
    public Telefone buscarTelefonePorId(Long id) {
        Optional<Telefone> pf = iTelefoneRepository.findById(id);
        if (pf.isPresent()) {
            //Usuario us = iUsuarioRepository.save(Usuario.convert(usuarioDTO))
            return pf.get();
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Telefone> telefone = iTelefoneRepository.findById(id);
        if (telefone.isPresent()) {
            //Usuario us = iUsuarioRepository.save(Usuario.convert(usuarioDTO))
            iTelefoneRepository.delete(telefone.get());
        }
    }

    @Override
    public Telefone update(Telefone telefone) {
        Optional<Telefone> telefoneId = iTelefoneRepository.findById(telefone.getTelefoneId());
        if (telefoneId.isEmpty()) {
            return null;

        }
        Telefone tel = telefoneId.get();
        tel.setTelefone(telefone.getTelefone());
        tel.setPessoaTelefone(telefone.getPessoaTelefone());
        tel.setTelefoneId(telefone.getTelefoneId());
        return iTelefoneRepository.save(tel);
    }

    protected Telefone add(Telefone telefone) {
        Telefone tel = new Telefone();
        tel.setTelefone(telefone.getTelefone());
        tel.setPessoaTelefone(telefone.getPessoaTelefone());
        return iTelefoneRepository.save(tel);
    }

    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.TELEFONE);
        Page<?> page = datatables.getSearch().isEmpty() ? iTelefoneRepository.findAll(datatables.getPageable())
                : iTelefoneRepository.findAllByTelefoneOrPessoa(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public Telefone buscarPorNumero(String numero) {
        return iTelefoneRepository.findByNumero(numero).get();
    }

}
