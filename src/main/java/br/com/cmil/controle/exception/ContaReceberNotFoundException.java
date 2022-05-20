
package br.com.cmil.controle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author cmil
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContaReceberNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ContaReceberNotFoundException(String message) {
		super(message);
	}

	public ContaReceberNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
