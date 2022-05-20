package br.com.cmil.controle.dominio.entidades;

import br.com.cmil.controle.dominio.enuns.Situacao;
import br.com.cmil.controle.dominio.enuns.Banco;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author cmilin
 */
@Entity
@Table(name = "tbl_contas_a_pagar")
public class ContaPagar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta_pagar")
    public Long id;
    private String documento;
    private String observacao;
    @Transient
    private Integer parcela = 1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vencimento;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_pagamento;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal valorPagar;
    @Transient
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    @Enumerated(EnumType.STRING)
    private Banco banco;
    @ManyToOne
    @JoinColumn(name = "processoFinanceiro_id")
    private ProcessoFinanceiro processoFinanceiro;

    public ContaPagar() {
    }

    public ContaPagar(Long id, String documento, String observacao, LocalDate vencimento, LocalDate data_pagamento, BigDecimal valorPagar, BigDecimal total, Situacao situacao, Banco banco, ProcessoFinanceiro processoFinanceiro) {
        this.id = id;
        this.documento = documento;
        this.observacao = observacao;
        this.vencimento = vencimento;
        this.data_pagamento = data_pagamento;
        this.valorPagar = valorPagar;
        this.total = total;
        this.situacao = situacao;
        this.banco = banco;
        this.processoFinanceiro = processoFinanceiro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getParcela() {
        return parcela;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public LocalDate getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(LocalDate data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public BigDecimal getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(BigDecimal valorPagar) {
        this.valorPagar = valorPagar;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public ProcessoFinanceiro getProcessoFinanceiro() {
        return processoFinanceiro;
    }

    public void setProcessoFinanceiro(ProcessoFinanceiro processoFinanceiro) {
        this.processoFinanceiro = processoFinanceiro;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final ContaPagar other = (ContaPagar) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ContaPagar{" + "id=" + id + ", documento=" + documento + ", observacao=" + observacao + ", parcela=" + parcela + ", vencimento=" + vencimento + ", data_pagamento=" + data_pagamento + ", valorPagar=" + valorPagar + ", total=" + total + ", situacao=" + situacao + ", banco=" + banco + ", processoFinanceiro=" + processoFinanceiro + '}';
    }

}
