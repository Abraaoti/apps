package br.com.cmil.controle.dominio.services.interfaces;

import br.com.cmil.controle.dominio.entidades.OrdemCompra;

/**
 *
 * @author ti
 */
public interface IOrdemCompraService {

    OrdemCompra save(OrdemCompra ordemCompra);

    Iterable<OrdemCompra> listAllOrdemCompra();

    OrdemCompra getOrdemCompraById(Long id);

    void delete(Long id);

    OrdemCompra update(OrdemCompra ordemCompra);

}
