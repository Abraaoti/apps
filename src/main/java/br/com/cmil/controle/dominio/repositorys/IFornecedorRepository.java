
package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Fornecedor;
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
public interface IFornecedorRepository extends JpaRepository<Fornecedor, Long>{
    @Query("select f from Fornecedor f "           
            + " where f.nome like :search%")
    public Page<?> findAllFornecedor(String search, Pageable pageable);

    @Query("select f.cnpj from Fornecedor f where f.cnpj IN :titulo")
    public Set<Fornecedor> findByFornecedorTitulo(String[] titulo);

    @Query("select f from Fornecedor f where f.nome like :termo%")
    public List<String> findByFornecedorTermo(String termo);
    
}
