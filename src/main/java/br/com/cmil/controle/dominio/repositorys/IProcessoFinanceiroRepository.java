package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.ProcessoFinanceiro;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cmilseg
 */
@Repository
public interface IProcessoFinanceiroRepository extends JpaRepository<ProcessoFinanceiro, Long> {

    @Query("select p from ProcessoFinanceiro p where p.emissao like :search%")
    Page<?> findAllBySearch(String search, Pageable pageable);
// @Query("select p.valorPagar from ProcessoFinanceiro p INNER JOIN FETCH p.fornecedor f INNER JOIN FETCH p.centroCusto c where p.valorPagar like :termo%")

    @Query("select p.valor from ProcessoFinanceiro p where p.valor like :termo%")
    List<String> findProcessoFinanceiroByTermo(String termo);

    @Query("select p from ProcessoFinanceiro p  where p.documento IN :documento")
    Set<ProcessoFinanceiro> findByProcessoFinanceiroDocumento(String[] documento);

    @Query("select p from ProcessoFinanceiro p where p.emissao >=:inicio and p.vencimento <=:fim")
    List<ProcessoFinanceiro> buscarByData(Date inicio, Date fim);

    @Query("select p from ProcessoFinanceiro p where p.valor =: valor")
    List<String> findProcessoFinanceiroByValor(BigDecimal valor);

    @Query("select p from ProcessoFinanceiro p "
            + " join p.fornecedor f"
            + " where f.id  =:id")
    Page<?> findAByIdFornecedor(Long id, Pageable pageable);

    @Query("select p from ProcessoFinanceiro p  where  p.usuario.email like :email")
    Optional<ProcessoFinanceiro> findByUsuarioEmail(String email);

}
