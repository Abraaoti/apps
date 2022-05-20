package br.com.cmil.controle.dominio.services.implementa;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 *
 * @author ti
 */
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine template;

    @Autowired
    public EmailService(JavaMailSender mailSender, SpringTemplateEngine template) {
        this.mailSender = mailSender;
        this.template = template;
    }

    public void enviarPedidoDeConfirmacaoDeCadastro(String destino, String codigo) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

        Context context = new Context();
        context.setVariable("titulo", "Bem vindo a Plataforma CMIL");
        context.setVariable("texto", "Precisamos que confirme seu cadastro, clicando no link abaixo");
        context.setVariable("linkConfirmacao", "http://localhost:8080/u/confirmacao/cadastro?codigo=" + codigo);

        String html = template.process("email/confirmacao", context);
        helper.setTo(destino);
        helper.setText(html, true);
        helper.setSubject("Confirmacao de Cadastro");
        helper.setFrom("elavokokassinda@gmail.com");

        helper.addInline("logo", new ClassPathResource("/static/image/spring-security.png"));

        mailSender.send(message);
    }

    public void enviarPedidoRedefinicaoSenha(String destino, String verificador) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");

        Context context = new Context();
        context.setVariable("titulo", "Redefinição de Senha");
        context.setVariable("texto", "Para redefinir sua senha use o código de verficação "
                + "quando exigido no formulário.");
        context.setVariable("verificador", verificador);

        String html = template.process("email/confirmacao", context);
        helper.setTo(destino);
        helper.setText(html, true);
        helper.setSubject("Redefinição de Senha");
        helper.setFrom("elavokokassinda@gmail.com");

        helper.addInline("logo", new ClassPathResource("/static/image/spring-security.png"));

        mailSender.send(message);
    }
}
