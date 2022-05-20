
package br.com.cmil.controle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author cmil
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContaPagarNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContaPagarNotFoundException(String message) {
		super(message);
	}

	public ContaPagarNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
