
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
@Table(name = "tbl_centro_custos")
public class CentroCusto extends Entidade{

   @Column(name = "centro_custo", length = 50)
    private String centro;

    public CentroCusto() {
    }

    public CentroCusto(String centro) {
        this.centro = centro;
    }

   

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    @Override
    public String toString() {
        return "CentroCusto{" + "centro_custo=" + centro + '}';
    }

    
}
