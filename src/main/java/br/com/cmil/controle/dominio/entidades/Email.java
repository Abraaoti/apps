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

/**
 *
 * @author cmilin
 */
@Entity
@Table(name = "tbl_emails")
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long emailId;
    @Column(length = 100)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email_pessoa_id", referencedColumnName = "id", unique = true)
    private Pessoa pessoaEmail;

    public Email() {

    }

    public Email(Pessoa pessoaEmail) {
        this.pessoaEmail = pessoaEmail;
    }

    public Email(Long emailId, String email, Pessoa pessoaEmail) {
        this.emailId = emailId;
        this.email = email;
        this.pessoaEmail = pessoaEmail;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa getPessoaEmail() {
        return pessoaEmail;
    }

    public void setPessoaEmail(Pessoa pessoaEmail) {
        this.pessoaEmail = pessoaEmail;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.emailId);
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
        final Email other = (Email) obj;
        return Objects.equals(this.emailId, other.emailId);
    }

}
