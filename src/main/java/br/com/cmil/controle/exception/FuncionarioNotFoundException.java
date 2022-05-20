
package br.com.cmil.controle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author cmil
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FuncionarioNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FuncionarioNotFoundException(String message) {
		super(message);
	}

	public FuncionarioNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
