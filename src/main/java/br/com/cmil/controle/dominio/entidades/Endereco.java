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
@Table(name = "tbl_enderecos")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long enderecoId;
    @Column(length = 70)
    private String uf;
    @Column(length = 70)
    private String cidade;
    @Column(length = 70)
    private String bairro;
    @Column(length = 70)
    private String rua;
    @Column(length = 15)
    private String cep;
    @Column(length = 15)
    private String numero;
    @Column(length = 70)
    private String complemento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_pessoa_id", referencedColumnName = "id")
    private Pessoa pessoaEndereco;

    public Endereco() {

    }

    public Endereco(Pessoa pessoaEndereco) {
        this.pessoaEndereco = pessoaEndereco;
    }

    public Endereco(Long enderecoId, String uf, String cidade, String bairro, String rua, String cep, String numero, String complemento, Pessoa pessoaEndereco) {
        this.enderecoId = enderecoId;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
        this.pessoaEndereco = pessoaEndereco;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Pessoa getPessoaEndereco() {
        return pessoaEndereco;
    }

    public void setPessoaEndereco(Pessoa pessoaEndereco) {
        this.pessoaEndereco = pessoaEndereco;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.enderecoId);
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
        final Endereco other = (Endereco) obj;
        return Objects.equals(this.enderecoId, other.enderecoId);
    }

    @Override
    public String toString() {
        return "Endereco{" + "enderecoId=" + enderecoId + ", uf=" + uf + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", cep=" + cep + ", numero=" + numero + ", complemento=" + complemento + ", pessoaEndereco=" + pessoaEndereco + '}';
    }

}
