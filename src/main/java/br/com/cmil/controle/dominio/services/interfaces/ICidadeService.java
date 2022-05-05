package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.Cidade;

/**
 *
 * @author ti
 */
public interface ICidadeService {

    Iterable<Cidade> listAllCidade();

    Cidade getCidadeById(Long id);

    void delete(Long id);

    Cidade updateCidade(Cidade cidade);

    Cidade saveCidade(Cidade cidade);

}
