
package br.com.cmil.controle.dominio.entidades;
import br.com.cmil.controle.dominio.base.Entidade;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author cmilseg
 */
@Entity
@Table(name = "tbl_estados")
public class Estado extends Entidade {

    @Column(length = 3, nullable = false)
    private String sigla;
    @Column(length = 50, nullable = false)
    private String estado;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Estado{" + "sigla=" + sigla + ", estado=" + estado + '}';
    }

   

}