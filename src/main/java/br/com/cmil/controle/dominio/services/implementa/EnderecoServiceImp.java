package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.Endereco;
import br.com.cmil.controle.dominio.repositorys.IEnderecoRepository;
import br.com.cmil.controle.dominio.services.interfaces.IEnderecoService;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author ti
 */
@Service
public class EnderecoServiceImp implements IEnderecoService {

    private final IEnderecoRepository iEnderecoRespositoy;
    private final Datatables datatables;

    @Autowired
    public EnderecoServiceImp(IEnderecoRepository iEnderecoRespositoy, Datatables datatables) {
        this.iEnderecoRespositoy = iEnderecoRespositoy;
        this.datatables = datatables;
    }

    @Override
    public Endereco save(Endereco endereco) {
        if (endereco.getEnderecoId() == null) {
            return add(endereco);
        } else {
            return update(endereco);
        }
    }

    @Override
    public Endereco buscarEnderecoPorId(Long id) {
        Optional<Endereco> endereco = iEnderecoRespositoy.findById(id);
        if (endereco.isPresent()) {
            //Usuario us = iUsuarioRepository.save(Usuario.convert(usuarioDTO))
            return endereco.get();
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Endereco> endereco = iEnderecoRespositoy.findById(id);
        if (endereco.isPresent()) {
            iEnderecoRespositoy.delete(endereco.get());
        }
    }

    @Override
    public Endereco update(Endereco endereco) {
        Optional<Endereco> enderecoId = iEnderecoRespositoy.findById(endereco.getEnderecoId());
        if (enderecoId.isEmpty()) {
            return null;
        }
        Endereco address = enderecoId.get();
        address.setUf(endereco.getUf());
        address.setCidade(endereco.getCidade());
        address.setBairro(endereco.getBairro());
        address.setRua(endereco.getRua());
        address.setCep(endereco.getCep());
        address.setNumero(endereco.getNumero());
        address.setComplemento(endereco.getComplemento());
        address.setPessoaEndereco(endereco.getPessoaEndereco());
        address.setEnderecoId(endereco.getEnderecoId());
        return iEnderecoRespositoy.save(address);
    }

    protected Endereco add(Endereco endereco) {      
        Endereco address = new Endereco();
        address.setUf(endereco.getUf());
        address.setCidade(endereco.getCidade());
        address.setBairro(endereco.getBairro());
        address.setRua(endereco.getRua());
        address.setCep(endereco.getCep());
        address.setNumero(endereco.getNumero());
        address.setComplemento(endereco.getComplemento());
        address.setPessoaEndereco(endereco.getPessoaEndereco());
        return iEnderecoRespositoy.save(address);
    }

    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.ENDERECO);
        Page<?> page = datatables.getSearch().isEmpty() ? iEnderecoRespositoy.findAll(datatables.getPageable())
                : iEnderecoRespositoy.findAllByEnderecoOrPessoa(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public Endereco buscarPorCep(String cep) {
        return iEnderecoRespositoy.findByCep(cep).get();
    }
}
