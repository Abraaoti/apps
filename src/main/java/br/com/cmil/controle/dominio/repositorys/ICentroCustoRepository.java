package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.CentroCusto;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ti
 */
public interface ICentroCustoRepository extends JpaRepository<CentroCusto, Long> {

    @Query("select c from CentroCusto c where c.centro like :search%")
    public Page<?> findAllByTitulo(String search, Pageable pageable);

    @Query("select c.centro from CentroCusto c where c.centro like :termo%")
    public List<String> findByCentroCustoTermo(String termo);

    @Query("select c from CentroCusto c where c.centro IN :titulos")
    public Set<CentroCusto> findByCentroCustoTitulo(String[] titulos);

}
