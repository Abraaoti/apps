package br.com.cmil.controle.dominio.services.interfaces;

/**
 *
 * @author ti
 */
public interface IEmailService {

    //Email save(Email email);

   // List<Email> findAllEmal();

   // Email findByEmalId(Long id);

    //void delete(Long id);

   // Email update(Email email);
    
    void enviarEmailConfirmacao(String destino, String codigo);
}
