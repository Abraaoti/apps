package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Departamento;
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
public interface IDepartamentoRepository extends JpaRepository<Departamento, Long> {

    @Query("select d from Departamento d where d.depa like :search%")
    Page<?> findAllByTitulo(String search, Pageable pageable);

    @Query("select d.depa from Departamento d where d.depa like :termo%")
    List<String> findByDepartamentoTermo(String termo);

    @Query("select d from Departamento d where d.depa IN :titulos")
    Set<Departamento> findByDepartamentoDepa(String[] titulos);

    //  @Query("select d from Departamento d "
    //        + "join d.funcionario f"
    //        + " where f.id  =:id")
    // public Page<?> findAByIdFuncionario(Long id, Pageable pageable);
}
