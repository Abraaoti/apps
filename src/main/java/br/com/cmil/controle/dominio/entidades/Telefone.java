package br.com.cmil.controle.dominio.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 *
 * @author cmilin
 */
@Entity
@Table(name = "tbl_telefones")
public class Telefone implements Serializable{
private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    public Long telefoneId;
    @Column(length = 20)
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Número do Telefone Obrigatório")
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "telefone_pessoa_id", referencedColumnName = "id", unique = true)
    private Pessoa pessoaTelefone;

    public Telefone() {

    }

    public Telefone(Pessoa pessoaTelefone) {
        this.pessoaTelefone = pessoaTelefone;
    }

    public Telefone(Long telefoneId, String telefone, Pessoa pessoaTelefone) {
        this.telefoneId = telefoneId;
        this.telefone = telefone;
        this.pessoaTelefone = pessoaTelefone;
    }

   

    public Long getTelefoneId() {
        return telefoneId;
    }

    public void setTelefoneId(Long telefoneId) {
        this.telefoneId = telefoneId;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Pessoa getPessoaTelefone() {
        return pessoaTelefone;
    }

    public void setPessoaTelefone(Pessoa pessoaTelefone) {
        this.pessoaTelefone = pessoaTelefone;
    }

    @Override
    public String toString() {
        return "Telefone{" + "telefone=" + telefone + ", pessoaTelefone=" + pessoaTelefone + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.telefoneId);
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
        final Telefone other = (Telefone) obj;
        return Objects.equals(this.telefoneId, other.telefoneId);
    }

   

}
