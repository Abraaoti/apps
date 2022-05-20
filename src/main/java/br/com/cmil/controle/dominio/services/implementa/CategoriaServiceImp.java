package br.com.cmil.controle.dominio.services.implementa;

import br.com.cmil.controle.dominio.datatable.Datatables;
import br.com.cmil.controle.dominio.datatable.DatatablesColunas;
import br.com.cmil.controle.dominio.entidades.Categoria;
import br.com.cmil.controle.dominio.repositorys.ICategoriaRepository;
import br.com.cmil.controle.dominio.services.interfaces.ICategoriaService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author ti
 */
@Service
public class CategoriaServiceImp implements ICategoriaService {

    private final ICategoriaRepository iCategoriaRepository;
    private final Datatables datatables;

    @Autowired
    public CategoriaServiceImp(ICategoriaRepository iCategoriaRepository, Datatables datatables) {
        this.iCategoriaRepository = iCategoriaRepository;
        this.datatables = datatables;
    }

    @Override
    public Categoria save(Categoria categoria) {
        if (categoria.getId() == null) {
            return add(categoria);
        }
        return update(categoria);
    }

    @Override
    public Iterable<Categoria> findLista() {
        return iCategoriaRepository.findAll();
    }

    @Override
    public Categoria buscarCategoriaPorId(Long id) {
        Optional<Categoria> categoriaId = iCategoriaRepository.findById(id);
        if (categoriaId.isEmpty()) {
            return null;
        }
        return categoriaId.get();
    }

    @Override
    public List<String> buscarCategoriaByTermo(String termo) {
      return iCategoriaRepository.findByCategoriaTermo(termo);
    }

    @Override
    public Set<Categoria> buscarCategoriaByTitulo(String[] titulo) {
         return iCategoriaRepository.findByCategoriaTitulo(titulo);
    }

    @Override
    public void delete(Long id) {
        Optional<Categoria> categoriaId = iCategoriaRepository.findById(id);      
        iCategoriaRepository.delete(categoriaId.get());
    }

    @Override
    public Categoria update(Categoria categoria) {
        Optional<Categoria> categoriaId = iCategoriaRepository.findById(categoria.getId());
        if (categoriaId.isEmpty()) {
            return null;
        }
        var categor = categoriaId.get();
        categor.setCate(categoria.getCate());
        categor.setId(categoria.getId());
        return iCategoriaRepository.save(categor);
    }

    @Override
    public Map<String, Object> buscarCategoria(HttpServletRequest request) {
      datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.CATEGORIAS);
        Page<?> page = datatables.getSearch().isEmpty() ? iCategoriaRepository.findAll(datatables.getPageable())
                : iCategoriaRepository.findAllByTitulo(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    protected Categoria add(Categoria categoria) {
        Categoria cat = new Categoria();
        cat.setCate(categoria.getCate());
        return iCategoriaRepository.save(cat);
    }
}
