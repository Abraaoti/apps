
package br.com.cmil.controle.dominio.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author cmilseg
 */
@Entity
@Table(name = "tbl_pessoa_juridicas")
@PrimaryKeyJoinColumn(name="pessoa_juridica_id")
public class PessoaJuridica extends Pessoa {
    @Column( unique = true, length = 20, nullable = true)
    private String cnpj;
    @Column( unique = true, length = 20, nullable = true)
    private String ie;
    @Column( unique = true, length = 20, nullable = false)
    private String im;

    public PessoaJuridica() {
    }

    public PessoaJuridica(String cnpj, String ie, String im) {
        this.cnpj = cnpj;
        this.ie = ie;
        this.im = im;
    }

    public PessoaJuridica(String cnpj, String ie, String im, String nome, String sobrenome, Date nascimento) {
        super(nome, sobrenome, nascimento);
        this.cnpj = cnpj;
        this.ie = ie;
        this.im = im;
    }       

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    @Override
    public String toString() {
        return "Fornecedor{" + "cnpj=" + cnpj + ", ie=" + ie + ", im=" + im + '}';
    }

   
    
}
