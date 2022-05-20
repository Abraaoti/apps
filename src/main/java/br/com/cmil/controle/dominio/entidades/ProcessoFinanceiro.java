package br.com.cmil.controle.dominio.entidades;

import br.com.cmil.controle.dominio.base.Conta;
import br.com.cmil.controle.dominio.enuns.FormaPagamento;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
/**
 *
 * @author cmilseg
 */
@Entity
@Table(name = "tbl_processos_financeiros")
@PrimaryKeyJoinColumn(name = "processoFinanceiroid")
public class ProcessoFinanceiro extends Conta {

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "fornecedorId")
    private Fornecedor fornecedor;

    private Integer qtdparcela = 1;
    //@NotBlank
  
    @OneToOne
    @JoinColumn(name = "centroCusto_id", referencedColumnName = "id")
    private CentroCusto centroCusto;
    @Enumerated(EnumType.STRING)
    private FormaPagamento forma_pagamento;

    public ProcessoFinanceiro() {
    }

    public ProcessoFinanceiro(Fornecedor fornecedor, CentroCusto centroCusto, FormaPagamento forma_pagamento) {
        this.fornecedor = fornecedor;
        this.centroCusto = centroCusto;
        this.forma_pagamento = forma_pagamento;
    }

    public ProcessoFinanceiro(Fornecedor fornecedor, CentroCusto centroCusto, FormaPagamento forma_pagamento, Usuario usuario) {
        super(usuario);
        this.fornecedor = fornecedor;
        this.centroCusto = centroCusto;
        this.forma_pagamento = forma_pagamento;
    }

    public ProcessoFinanceiro(Fornecedor fornecedor, CentroCusto centroCusto, FormaPagamento forma_pagamento, Long id, LocalDate emissao, LocalDate vencimento, String documento, BigDecimal valor, Usuario usuario, String arquivo) {
        super(id, emissao, vencimento, documento, valor, usuario, arquivo);
        this.fornecedor = fornecedor;
        this.centroCusto = centroCusto;
        this.forma_pagamento = forma_pagamento;
    }

   

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Integer getQtdparcela() {
        return qtdparcela;
    }

    public void setQtdparcela(Integer qtdparcela) {
        this.qtdparcela = qtdparcela;
    }

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public FormaPagamento getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(FormaPagamento forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

   
    @Override
    public String toString() {
        return "ProcessoFinanceiro{" + "fornecedor=" + fornecedor + ", qtdparcela=" + qtdparcela + ", centroCusto=" + centroCusto + ", forma_pagamento=" + forma_pagamento + '}';
    }

  

}
