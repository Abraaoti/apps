package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Endereco;
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
public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {
    @Query("select e from Endereco e JOIN  e.pessoaEndereco p where e.cep like :search% OR p.nome like :search%")
    public Page<?> findAllByEnderecoOrPessoa(String search, Pageable pageable);

    @Query("select e from Endereco e  where e.cep =:cep")
    Optional<Endereco> findByCep(@Param("cep")String cep);

}
