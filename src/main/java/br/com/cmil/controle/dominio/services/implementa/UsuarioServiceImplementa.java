package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.Perfil;
import br.com.cmil.controle.dominio.entidades.Usuario;
import br.com.cmil.controle.dominio.enuns.PerfilTipo;
import br.com.cmil.controle.dominio.repositorys.IPerfilRepository;
import br.com.cmil.controle.dominio.repositorys.IUsuarioRepository;
import br.com.cmil.controle.dominio.services.interfaces.IUsuarioService;
import br.com.cmil.controle.exception.AcessoNegadoException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 *
 * @author cmilseg
 */
@Service
@Transactional
public class UsuarioServiceImplementa implements IUsuarioService {

    private final IUsuarioRepository iUsuarioRepo;
    private final EmailService emailService;
    private final IPerfilRepository iPerfilRepo;
    private final Datatables datatables;

    @Autowired
    public UsuarioServiceImplementa(IUsuarioRepository iUsuarioRepo, EmailService emailService, IPerfilRepository iPerfilRepo, Datatables datatables) {
        this.iUsuarioRepo = iUsuarioRepo;
        this.emailService = emailService;
        this.iPerfilRepo = iPerfilRepo;
        this.datatables = datatables;
    }

    @Override
    @Transactional(readOnly = false)
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            return addUsuario(usuario);
        }

        return null;

    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return iUsuarioRepo.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return iUsuarioRepo.findByEmail(email);
    }

    @Override
    public void delete(Long usuarioId) {
        Usuario usuario = iUsuarioRepo.findById(usuarioId).get();
        usuario.getPerfis().forEach((Perfil role) -> {
            usuario.getPerfis().remove(role.getDesc());
        });
        iUsuarioRepo.delete(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        Optional<Usuario> usua = iUsuarioRepo.findById(usuario.getId());
        if (usua.isEmpty()) {
            return null;
        } else {
            Usuario us = usua.get();
            us.setDataCadastro(LocalDateTime.now());
            us.setEmail(usuario.getEmail());
            us.setAtivo(usuario.isAtivo());
            us.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
            List<Perfil> roles = new ArrayList<>();
            for (Perfil roleId : usuario.getPerfis()) {
                Perfil rol = iPerfilRepo.findById(roleId.getId()).get();
                roles.add(rol);
            }
            us.setPerfis(roles);
            return iUsuarioRepo.save(us);
        }
    }

    protected Usuario addUsuario(Usuario usuario) {
        Usuario us = new Usuario();

        us.setDataCadastro(LocalDateTime.now());
        us.setEmail(usuario.getEmail());
        us.setAtivo(usuario.isAtivo());
        us.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        List<Perfil> perfils = new ArrayList<>();
        for (Perfil roleId : usuario.getPerfis()) {
            Perfil perfil = iPerfilRepo.findById(roleId.getId()).get();
            perfils.add(perfil);
        }
        us.setPerfis(perfils);
        if (us.getPerfis().size() > 2 || us.getPerfis().containsAll(Arrays.asList(new Perfil(1L), new Perfil(3L)))
                || us.getPerfis().containsAll(Arrays.asList(new Perfil(2L), new Perfil(3L)))) {
            return null;

        } else {

            return iUsuarioRepo.save(us);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = buscarPorEmailEAtivo(username).orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " não encontrado"));
        return new User(usuario.getEmail(), usuario.getPassword(), AuthorityUtils.createAuthorityList(getAuthorityUtils(usuario.getPerfis())));

    }

    protected String[] getAuthorityUtils(List<Perfil> perfis) {
        String[] authority = new String[perfis.size()];
        for (int i = 0; i < perfis.size(); i++) {
            authority[i] = perfis.get(i).getDesc();
        }
        return authority;
    }

    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.USUARIOS);
        Page<?> page = datatables.getSearch().isEmpty() ? iUsuarioRepo.findAll(datatables.getPageable())
                : iUsuarioRepo.findAllByEmailOrPerfil(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorIdEPerfil(Long usuarioId, Long[] perfisId) {
        return iUsuarioRepo.findByIdAndPerfil(usuarioId, perfisId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente!"));
    }

    @Override
    @Transactional(readOnly = false)
    public void alterarSenha(Usuario usuario, String senha) {
        usuario.setPassword(new BCryptPasswordEncoder().encode(senha));
        iUsuarioRepo.save(usuario);
    }

    @Override
    public void saveCadastroFinanceiro(Usuario usuario) throws MessagingException {
        Usuario us = new Usuario();
        us.setDataCadastro(LocalDateTime.now());
        us.setEmail(usuario.getEmail());
        us.setAtivo(usuario.isAtivo());
        us.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        us.setPerfis(Arrays.asList(new Perfil(PerfilTipo.FINANCEIRO.getCod())));
        us.setVerificador(usuario.getVerificador());        
        Usuario usuarioEmail = iUsuarioRepo.save(us);
        
        emailDeConfirmacaoDeCadastro(usuarioEmail.getEmail());
    }

    public void emailDeConfirmacaoDeCadastro(String email) throws MessagingException {        
        String codigo = Base64Utils.encodeToString(email.getBytes());
        emailService.enviarPedidoDeConfirmacaoDeCadastro(email, codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorEmailEAtivo(String email) {
        return iUsuarioRepo.findByEmailAndAtivo(email);
    }

    @Transactional(readOnly = false)
    @Override
    @SuppressWarnings("empty-statement")
    public void pedidoRedefinicaoDeSenha(String email) throws MessagingException {

        Usuario usuario = buscarPorEmailEAtivo(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario " + email + " não encontrado."));;

        String verificador = RandomStringUtils.randomAlphanumeric(6);

        usuario.setVerificador(verificador);

        emailService.enviarPedidoRedefinicaoSenha(email, verificador);

    }

    @Transactional(readOnly = false)
    @Override
    public void ativarCadastroFuncionario(String codigo) {
        String email = new String(Base64Utils.decodeFromString(codigo));
        Usuario usuario = buscarPorEmail(email);
        if (usuario.getId() == null) {
            throw new AcessoNegadoException("Não foi possível ativar seu cadastro. Entre em "
                    + "contato com o suporte.");
        }
        usuario.setAtivo(true);
    }

}
