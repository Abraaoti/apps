package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Usuario;
import java.util.Map;
import java.util.Optional;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author cmilseg
 */
public interface IUsuarioService extends UserDetailsService {

    Usuario save(Usuario usuario);

    Usuario buscarPorId(Long id);

    public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
        return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada);
    }

    Usuario buscarPorIdEPerfil(Long usuarioId, Long[] perfisId);

    Usuario buscarPorEmail(String email);

    void delete(Long id);

    void alterarSenha(Usuario usuario, String s1);

    Usuario update(Usuario usuario);

    Map<String, Object> buscarTodos(HttpServletRequest request);

    Optional<Usuario> buscarPorEmailEAtivo(String email);

    void saveCadastroFinanceiro(Usuario usuario)throws MessagingException ;

    void pedidoRedefinicaoDeSenha(String email)throws MessagingException;

    void ativarCadastroFuncionario(String codigo);

}
