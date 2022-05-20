package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.Funcionario;
import br.com.cmil.controle.dominio.enuns.EstadoCivil;
import br.com.cmil.controle.dominio.enuns.Genero;
import br.com.cmil.controle.dominio.repositorys.IFuncionarioRepository;
import br.com.cmil.controle.dominio.services.interfaces.IFuncionarioService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
public class FuncionarioServiceImplementa implements IFuncionarioService {

    private final IFuncionarioRepository iFuncionarioRepository;
    private final Datatables datatables;

    @Autowired
    public FuncionarioServiceImplementa(IFuncionarioRepository iFuncionarioRepository, Datatables datatables) {
        this.iFuncionarioRepository = iFuncionarioRepository;
        this.datatables = datatables;
    }

    @Override
    public Funcionario save(Funcionario funcionario) {
        if (funcionario.getId() == null) {
            return add(funcionario);
        }
        return update(funcionario);
    }

    @Override
    public Funcionario buscarFuncionarioPorId(Long id) {
        //Optional<PessoaFisica> funcionarioId = iFuncionarioRepository.findById(id);

        return (Funcionario) iFuncionarioRepository.findById(id).get();
    }

    @Override
    public Iterable<Funcionario> findAllFuncionario() {
        return iFuncionarioRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Funcionario funcionarioId = iFuncionarioRepository.findById(id).get();
        iFuncionarioRepository.delete(funcionarioId);
    }

    @Override
    public Funcionario update(Funcionario funcionario) {
        Optional<Funcionario> funcionarioId = iFuncionarioRepository.findById(funcionario.getId());
        if (funcionarioId.isEmpty()) {
            return null;
        }
        var f = funcionarioId.get();
        f.setNome(funcionario.getNome());
        f.setSobrenome(funcionario.getSobrenome());
        f.setNascimento(funcionario.getNascimento());
        f.setNaturalidade(funcionario.getNaturalidade());
        f.setCpf(funcionario.getCpf());
        f.setRg(funcionario.getRg());
        f.setPassaporte(funcionario.getPassaporte());
        f.setMae(funcionario.getMae());
        f.setPai(funcionario.getPai());
        // f.setMatricula(funcionario.getMatricula());
        f.setAdmissao(funcionario.getAdmissao());
        f.setSalario(funcionario.getSalario());
        f.setCargo(funcionario.getCargo());
        for (Genero value : Genero.values()) {
            if (value.equals(funcionario.getGenero())) {
                f.setGenero(value);
            }
        }

        for (EstadoCivil value : EstadoCivil.values()) {
            if (value.equals(funcionario.getEc())) {
                f.setEc(value);
            }
        }
        //f.getEndereco().setPessoaEndereco(funcionario);
        //f.getEmail().setPessoaEmail(funcionario);
        f.setEmpresa(funcionario.getEmpresa());
        f.setId(funcionario.getId());
        return iFuncionarioRepository.saveAndFlush(f);
    }

    public Funcionario add(Funcionario funcionario) {

        Funcionario f = new Funcionario();
        f.setNome(funcionario.getNome());
        f.setSobrenome(funcionario.getSobrenome());
        f.setNascimento(funcionario.getNascimento());
        f.setNaturalidade(funcionario.getNaturalidade());
        f.setCpf(funcionario.getCpf());
        f.setRg(funcionario.getRg());
        f.setPassaporte(funcionario.getPassaporte());
        f.setMae(funcionario.getMae());
        f.setPai(funcionario.getPai());
        f.setAdmissao(funcionario.getAdmissao());
        f.setSalario(funcionario.getSalario());
        // f.setDemissao(null);
        f.setCargo(funcionario.getCargo());
        for (Genero value : Genero.values()) {
            if (value.equals(funcionario.getGenero())) {
                f.setGenero(value);
            }
        }
        for (EstadoCivil value : EstadoCivil.values()) {
            if (value.equals(funcionario.getEc())) {
                f.setEc(value);
            }
        }
        //f.getEndereco().setPessoaEndereco(funcionario);
        //f.getEmail().setPessoaEmail(funcionario);
        //f.getTelefone().setPessoaTelefone(funcionario);
        f.setEmpresa(funcionario.getEmpresa());
        return iFuncionarioRepository.save(f);
    }

    @Override
    public List<String> buscarFuncionarioByTermo(String termo) {
        return iFuncionarioRepository.findByFuncionarioTermo(termo);
    }

    @Override
    public Set<Funcionario> buscarFuncionarioByTitulo(String[] titulo) {
        return iFuncionarioRepository.findByFuncionarioTitulo(titulo);
    }

    @Override
    public Map<String, Object> buscarFuncionario(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.FUNCIONARIOS);
        Page<?> page = datatables.getSearch().isEmpty() ? iFuncionarioRepository.findAll(datatables.getPageable())
                : iFuncionarioRepository.findAllFuncionarios(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

}
