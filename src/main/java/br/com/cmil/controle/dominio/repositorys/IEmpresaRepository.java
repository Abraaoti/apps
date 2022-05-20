package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Empresa;
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
public interface IEmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("select e from Empresa e  where e.nome like :search%")
    public Page<?> findAllEmpresa(String search, Pageable pageable);

    @Query("select e.cnpj from Empresa e where e.cnpj IN :titulo")
    public Set<Empresa> findByEmpresaTitulo(String[] titulo);

    @Query("select e from Empresa e where e.nome like :termo%")
    public List<String> findByEmpresaTermo(String termo);

}
