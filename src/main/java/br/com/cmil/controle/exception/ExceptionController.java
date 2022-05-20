package br.com.cmil.controle.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author kalele
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView usuarioNaoEncontradoExceptio(UsernameNotFoundException ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("status", 404);
        model.put("error", "Operação não pode ser realizada");
        model.put("message", ex.getMessage());
        return new ModelAndView("acesso-negado",model);
    }
}
