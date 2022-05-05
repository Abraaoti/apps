package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Telefone;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cmilseg
 */
@Repository
public interface ITelefoneRepository extends JpaRepository<Telefone, Long> {

    @Query("select t from Telefone t JOIN t.pessoaTelefone p where t.telefone like :search% OR p.nome like :search%")
    public Page<?> findAllByTelefoneOrPessoa(String search, Pageable pageable);

    @Query("select t from Telefone t  where t.telefone =:numero")
    Optional<Telefone> findByNumero(@Param("numero") String numero);
}
