package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.ContaPagar;
import br.com.cmil.controle.dominio.services.interfaces.IContaPagarService;
import java.time.LocalDate;
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
public interface IContaPagarRepository extends JpaRepository<ContaPagar, Long> {

    @Query("select c from ContaPagar c JOIN c.processoFinanceiro p where c.documento like :search% OR p.documento like :search%")
    Page<?> findAllBySearch(String search, Pageable pageable);

    @Query("select sum(valorPagar) from ContaPagar c  where c.vencimento >= :dataInicial and c.vencimento <= :dataFinal")
    ContaPagar findDateInitAndDateFim(LocalDate dataInicial, LocalDate dataFinal);

}
