package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.PessoaFisica;

/**
 *
 * @author ti
 */
public interface IPessoaFisicaService {

    PessoaFisica save(PessoaFisica pessoaFisicaDTO);

    Iterable<PessoaFisica> listAllPessoaFisicaDTO();

    PessoaFisica findByPessoaFisicaId(Long id);

    void delete(Long id);

    PessoaFisica update(PessoaFisica pessoaFisicaDTO);

}
