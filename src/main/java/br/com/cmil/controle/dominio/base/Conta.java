package br.com.cmil.controle.dominio.base;

import br.com.cmil.controle.dominio.entidades.Usuario;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author cmilseg
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Conta extends Entidade {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate emissao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vencimento;
    private String documento;
    
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal valor;
   
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
     @Column(nullable = true, length = 64)
    private String arquivo;

    public Conta() {
    }

    public Conta(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conta(LocalDate emissao, LocalDate vencimento, String documento, BigDecimal valor, Usuario usuario, String arquivo) {
        this.emissao = emissao;
        this.vencimento = vencimento;
        this.documento = documento;
        this.valor = valor;
        this.usuario = usuario;
        this.arquivo = arquivo;
    }

    public LocalDate getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDate emissao) {
        this.emissao = emissao;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

   
   
    public String getArquivoPath() {
        if (arquivo == null || id == null) return null;
         
        return "/user-photos/" + id + "/" + arquivo;
    }

    @Override
    public String toString() {
        return "Conta{" + "emissao=" + emissao + ", vencimento=" + vencimento + ", documento=" + documento + ", valor=" + valor + ", usuario=" + usuario + ", arquivo=" + arquivo + '}';
    }

    

}
