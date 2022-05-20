
package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Funcionario;
import java.util.List;
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
public interface IFuncionarioRepository extends JpaRepository<Funcionario, Long>{
    @Query("select f from Funcionario f "
            + " join  f.cargo c "            
            + " join  f.empresa e "            
            + " where f.nome like :search%")
    public Page<?> findAllFuncionarios(String search, Pageable pageable);

    @Query("select f.cpf from Funcionario f where f.cpf IN :titulo")
    public Set<Funcionario> findByFuncionarioTitulo(String[] titulo);

    @Query("select f from Funcionario f where f.nome like :termo%")
    public List<String> findByFuncionarioTermo(String termo);
    
}
