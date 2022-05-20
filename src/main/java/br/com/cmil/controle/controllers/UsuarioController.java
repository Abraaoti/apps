package br.com.cmil.controle.controllers;

import br.com.cmil.controle.dominio.entidades.Perfil;
import br.com.cmil.controle.dominio.entidades.Usuario;
import br.com.cmil.controle.dominio.enuns.PerfilTipo;
import br.com.cmil.controle.dominio.services.interfaces.IFuncionarioService;
import br.com.cmil.controle.dominio.services.interfaces.IPerfilService;
import br.com.cmil.controle.dominio.services.interfaces.IUsuarioService;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author cmilseg
 */
@Controller
@RequestMapping("u")
public class UsuarioController {

    private final String USUARIO = "usuario/";
    private final IUsuarioService service;
    private final IPerfilService iPerfilService;
   

    public UsuarioController(IUsuarioService service, IPerfilService iPerfilService) {
        this.service = service;
        this.iPerfilService = iPerfilService;
       
    }

   

   

    @GetMapping("/novo/cadastro/usuario")
    public ModelAndView novoUsuario() {
        Map<String, Object> model = new HashMap<>();
        model.put("perfis", iPerfilService.findAll());
        model.put("usuario", new Usuario());
        return new ModelAndView(USUARIO + "cadastro", model);
    }

    @GetMapping("/lista")
    public String listarUsuarios() {

        return "usuario/lista";
    }

    @PostMapping("/cadastro/salvar")
    public ModelAndView salvarUsuario(Usuario usuario) {
        Map<String, Object> model = new HashMap<>();
        service.save(usuario);
        model.put("sucesso", "Operação realizada com sucesso.");
       // model.put("usuario", new Usuario());
        return new ModelAndView(USUARIO + "cadastro", model);
    }

    @GetMapping("/editar/cadastro/usuario/{id}")
    public ModelAndView editarUsuario(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(USUARIO + "cadastro", model);
    }

    @GetMapping("/editar/credenciais/usuario/{id}")
    public ModelAndView preEditarCredenciais(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("perfis", iPerfilService.findAll());
        model.put("usuario", service.buscarPorId(id));
        return new ModelAndView(USUARIO + "cadastro", model);
    }

    @GetMapping("/editar/dados/usuario/{id}/perfis/{perfis}")
    public ModelAndView preEditarCadastroDadosPessoas(@PathVariable("id") Long usuarioId, @PathVariable("id") Long[] perfisId) {
        Map<String, Object> model = new HashMap<>();
        Usuario usuario = service.buscarPorIdEPerfil(usuarioId, perfisId);
        if (usuario.getPerfis().contains(new Perfil(PerfilTipo.ADMIN.getCod())) && !usuario.getPerfis().contains(new Perfil(PerfilTipo.FUNCIONARIO.getCod()))) {
            model.put("usuario", usuario);
            model.put("perfis", iPerfilService.findAll());
            return new ModelAndView(USUARIO + "cadastro", model);
        } else if(usuario.getPerfis().contains(new Perfil(PerfilTipo.TECNICO.getCod()))) {
            model.put("status", 403);
            model.put("error", "Área Restrita");
            model.put("message", "Os dados de pacientes são restrito a ele");
            return new ModelAndView("403", model);
        }

        return new ModelAndView(USUARIO + "lista", model);
    }

    @GetMapping("/editar/senha")
    public ModelAndView redefinir() {
        return new ModelAndView(USUARIO + "editar-senha");
    }

    @PostMapping("/confirmar/senha")
    public ModelAndView confirmarSenha(@RequestParam("senha1") String s1, @RequestParam("senha2") String s2, @RequestParam("senha3") String s3,
            @AuthenticationPrincipal User user) {
        Map<String, Object> model = new HashMap<>();

        if (!s1.equals(s2)) {
            model.put("falha", "Senhas não conferem, tente novamente.");
            return new ModelAndView("redirect:/u/editar/senha", model);
        }
        Usuario us = service.buscarPorEmail(user.getUsername());
        if (!IUsuarioService.isSenhaCorreta(s3, us.getPassword())) {
            model.put("falha", "Senha atual não confere, tente novamente.");
            return new ModelAndView("redirect:/u/editar/senha", model);
        }
        service.alterarSenha(us, s1);
        model.put("sucesso", "Senha alterada com sucesso.");
        return new ModelAndView("redirect:/u/editar/senha", model);
    }

    @GetMapping("/datatables/server/usuarios")
    public ResponseEntity<?> ListaUsuariosDataTables(HttpServletRequest request) {
        return ResponseEntity.ok(service.buscarTodos(request));
    }

   
     @GetMapping("/novo/cadastro")
    public String novoCadastro(Usuario usuario) {

        return "cadastrar-se";
    }

   
    @PostMapping("/cadastro/financeiro/salvar")
    public String salvarCadastroFinanceiro(Usuario usuario, BindingResult result) throws MessagingException {
        try {
            service.saveCadastroFinanceiro(usuario);
        } catch (DataIntegrityViolationException ex) {
            result.reject("email", "Ups... Este e-mail já existe na base de dados.");
            return "cadastrar-se";
        }
        return "redirect:/u/cadastro/realizado";
    }

   
    @GetMapping("/cadastro/realizado")
    public String cadastroRealizado() {

        return "fragments/mensagem";
    }

    // recebe a requisicao de confirmacao de cadastro
    @GetMapping("/confirmacao/cadastro")
    public String respostaConfirmacaoCadastroPaciente(@RequestParam("codigo") String codigo,
            RedirectAttributes attr) {
        service.ativarCadastroFuncionario(codigo);
        attr.addFlashAttribute("alerta", "sucesso");
        attr.addFlashAttribute("titulo", "Cadastro Ativado!");
        attr.addFlashAttribute("texto", "Parabéns, seu cadastro está ativo.");
        attr.addFlashAttribute("subtexto", "Singa com seu login/senha");
        return "redirect:/login";
    }

    // abre a pagina de pedido de redefinicao de senha
    @GetMapping("/p/redefinir/senha")
    public String pedidoRedefinirSenha() {

        return "usuario/pedido-recuperar-senha";
    }

    // form de pedido de recuperar senha
    @GetMapping("/p/recuperar/senha")
    public String redefinirSenha(String email, ModelMap model) throws MessagingException {
        service.pedidoRedefinicaoDeSenha(email);
        model.addAttribute("sucesso", "Em instantes você reberá um e-mail para "
                + "prosseguir com a redefinição de sua senha.");
        model.addAttribute("usuario", new Usuario(email));
        return "usuario/recuperar-senha";
    }

    // salvar a nova senha via recuperacao de senha
    @PostMapping("/p/nova/senha")
    public String confirmacaoDeRedefinicaoDeSenha(Usuario usuario, ModelMap model) {
        Usuario u = service.buscarPorEmail(usuario.getEmail());
        if (!usuario.getVerificador().equals(u.getVerificador())) {
            model.addAttribute("falha", "Código verificador não confere.");
            return "usuario/recuperar-senha";
        }
        u.setVerificador(null);
        service.alterarSenha(u, usuario.getPassword());
        model.addAttribute("alerta", "sucesso");
        model.addAttribute("titulo", "Senha redefinida!");
        model.addAttribute("texto", "Você já pode logar no sistema.");
        return "login";
    }
}
