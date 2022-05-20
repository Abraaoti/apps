package br.com.cmil.controle.dominio.entidades;

import br.com.cmil.controle.dominio.base.Entidade;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author cmilseg
 */
@Entity
@Table(name = "tbl_pessoas")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorValue("Funcionario")
public class Pessoa extends Entidade{

   
    @Column(length = 80)
    private String nome;
    @Column(length = 120)
    private String sobrenome;
    @Column(name = "nasc")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nascimento;
    
   

    public Pessoa() {
    }

    public Pessoa(String nome, String sobrenome, Date nascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", sobrenome=" + sobrenome + ", nascimento=" + nascimento + '}';
    }

   
}
