
package br.com.cmil.controle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author cmil
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FornecedorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FornecedorNotFoundException(String message) {
		super(message);
	}

	public FornecedorNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
