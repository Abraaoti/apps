package br.com.cmil.controle.dominio.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author cmilin
 */
@Entity
@Table(name = "tbl_cargos")
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idCargo;
    @Column(length = 50, nullable = false, unique = true)
    private String titulo;
    @ManyToMany
    @JoinTable(name = "tbl_cargos_departamentos",
            joinColumns = @JoinColumn(name = "cargo_id", referencedColumnName = "idCargo"),
            inverseJoinColumns = @JoinColumn(name = "departamento_id", referencedColumnName = "id_departamento"))
    private List<Departamento> departamentos;

    public void addDepartamento(Departamento departamento) {
        if (departamento != null) {
            this.departamentos = new ArrayList<>();
        }
        this.departamentos.add(departamento);

    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    @Override
    public String toString() {
        return "Cargo{" + "titulo=" + titulo + ", departamento=" + departamentos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idCargo);
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
        final Cargo other = (Cargo) obj;
        return Objects.equals(this.idCargo, other.idCargo);
    }

}
