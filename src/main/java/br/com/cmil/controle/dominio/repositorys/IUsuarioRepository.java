package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Usuario;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cmilseg
 */
@Repository
@Transactional
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from Usuario u where  u.email like :email")
    Usuario findByEmail(@Param("email") String email);

    @Query("select u from Usuario u  JOIN  u.perfis p  where  u.email like :search%  OR p.desc like :search%")
    public Page<?> findAllByEmailOrPerfil(String search, Pageable pageable);

    @Query("select u from Usuario u  JOIN  u.perfis p  where  u.id = :usuarioId  AND p.id IN :perfisId")
    Optional<Usuario> findByIdAndPerfil(Long usuarioId, Long[] perfisId);

    @Query("select u from Usuario u where  u.email like :email AND ativo = true")
    Optional<Usuario> findByEmailAndAtivo(String email);
}
