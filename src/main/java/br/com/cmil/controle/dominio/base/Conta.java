package br.com.cmil.controle.dominio.base;

import br.com.cmil.controle.dominio.entidades.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author cmilseg
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class Conta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conta(Long id, LocalDate emissao, LocalDate vencimento, String documento, BigDecimal valor, Usuario usuario, String arquivo) {
        this.id = id;
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
        return "Conta{" + "id=" + id + ", emissao=" + emissao + ", vencimento=" + vencimento + ", documento=" + documento + ", valor=" + valor + ", usuario=" + usuario + ", arquivo=" + arquivo + '}';
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.documento);
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
        final Conta other = (Conta) obj;
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    

}
