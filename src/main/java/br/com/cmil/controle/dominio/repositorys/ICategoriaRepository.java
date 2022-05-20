package br.com.cmil.controle.dominio.repositorys;

import br.com.cmil.controle.dominio.entidades.Categoria;
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
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select c from Categoria c where c.cate like :search%")
    public Page<?> findAllByTitulo(String search, Pageable pageable);

    @Query("select c.cate from Categoria c where c.cate IN :titulo")
    public Set<Categoria> findByCategoriaTitulo(String[] titulo);

    @Query("select c from Categoria c where c.cate like :termo%")
    public List<String> findByCategoriaTermo(String termo);

}
