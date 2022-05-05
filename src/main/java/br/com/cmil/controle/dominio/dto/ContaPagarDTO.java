
package br.com.cmil.controle.dominio.dto;

import br.com.cmil.controle.dominio.entidades.ProcessoFinanceiro;
import br.com.cmil.controle.dominio.enuns.Banco;
import br.com.cmil.controle.dominio.enuns.Situacao;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author ti
 */
public class ContaPagarDTO {
    @NotBlank
    public Long id;
    @NotBlank
    private String documento;
    @NotBlank
    private String observacao;
    @Transient
    private Integer parcela = 1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vencimento;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_pagamento;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal valorPagar;   
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal total;   
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    @Enumerated(EnumType.STRING)
    private Banco banco;
    @NotBlank
    private ProcessoFinanceiro processoFinanceiro; 
}
