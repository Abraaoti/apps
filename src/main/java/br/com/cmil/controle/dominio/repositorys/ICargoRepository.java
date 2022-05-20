package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Cargo;
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
public interface ICargoRepository extends JpaRepository<Cargo, Long> {

    @Query("select c from Cargo c where c.titulo like :search%")
    Page<?> findAllByTitulo(String search, Pageable pageable);

    @Query("select c.titulo from Cargo c where c.titulo like :termo%")
    List<String> findByCargoTermo(String termo);

    @Query("select c from Cargo c where c.departamentos IN :titulos")
    Set<Cargo> findByCargoDepa(String[] titulos);

    //  @Query("select d from Departamento d "
    //        + "join d.funcionario f"
    //        + " where f.id  =:id")
    // public Page<?> findAByIdFuncionario(Long id, Pageable pageable);
}
