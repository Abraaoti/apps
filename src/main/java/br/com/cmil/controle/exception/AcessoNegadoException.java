package br.com.cmil.controle.exception;

/**
 *
 * @author kalele
 */
@SuppressWarnings("serial")
public class AcessoNegadoException extends RuntimeException {

    public AcessoNegadoException(String message) {
        super(message);
    }
}
