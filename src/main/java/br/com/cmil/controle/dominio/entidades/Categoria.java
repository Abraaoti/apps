
package br.com.cmil.controle.dominio.entidades;

import br.com.cmil.controle.dominio.base.Entidade;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author cmilin
 */
@Entity
@Table(name = "tbl_categorias")
public class Categoria extends Entidade{

    
    @Column(length = 40)
    private String cate;

   

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    @Override
    public String toString() {
        return "Categoria{" + "categoria=" + cate + '}';
    }

    

}
