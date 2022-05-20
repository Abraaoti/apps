package br.com.cmil.controle.dominio.entidades;

import br.com.cmil.controle.dominio.base.Entidade;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cmilin
 */
@Entity
@Table(name = "tbl_departamentos")
public class Departamento implements Serializable{
private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    public Long id;

    @Column(nullable = true, length = 80)
    private String depa;
    //@ManyToOne
    //@JoinColumn(name = "id_medico")
   // private Funcionario funcionario;

    public Departamento() {
    }

    public Departamento(String depa) {
        this.depa = depa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepa() {
        return depa;
    }

    public void setDepa(String depa) {
        this.depa = depa;
    }

    @Override
    public String toString() {
        return "Departamento{" + "departamento=" + depa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departamento other = (Departamento) obj;
        return Objects.equals(this.id, other.id);
    }

}
