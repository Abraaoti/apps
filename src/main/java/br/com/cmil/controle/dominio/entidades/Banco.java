package br.com.cmil.controle.dominio.entidades;

import br.com.cmil.controle.dominio.base.Entidade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author cmilseg
 */
@Entity
@Table(name = "tbl_bancos")
public class Banco implements Serializable{
private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    @Column(name = "id_banco")
    public Long id;

    @Column(length = 3, nullable = false)
    private String descricao;
    @Column(length = 20, nullable = false)
    private String classificacao;
    @Column(length = 20, nullable = false)
    private String conta;
    @Column(length = 20, nullable = false)
    private String agencia;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inicio;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

   
    public void setId(Long id) {
        this.id = id;
    }
    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getClassificacao() {
        return classificacao;
    }


    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Banco{" + "descricao=" + descricao + ", classificacao=" + classificacao + ", conta=" + conta + ", agencia=" + agencia + ", inicio=" + inicio + ", valor=" + valor + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Banco other = (Banco) obj;
        return Objects.equals(this.id, other.id);
    }

   

}
