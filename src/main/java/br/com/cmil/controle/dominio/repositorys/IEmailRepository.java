package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Email;
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
public interface IEmailRepository extends JpaRepository<Email, Long> {

    @Query("select e from Email e JOIN e.pessoaEmail p where e.email like :search% OR p.nome like :search%")
    public Page<?> findAllByEmailOrPessoa(String search, Pageable pageable);
}
