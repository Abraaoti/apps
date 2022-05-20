package br.com.cmil.controle.dominio.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author cmilseg
 */
@Entity
@Table(name = "tbl_empresas")
@PrimaryKeyJoinColumn(name = "empresa_id")
public class Empresa extends PessoaJuridica {

    @Column(length = 70)
    private String socios;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal capital;

    public Empresa() {
    }

    public Empresa(String socios, BigDecimal capital) {
        this.socios = socios;
        this.capital = capital;
    }

    public Empresa(String socios, BigDecimal capital, String cnpj, String ie, String im) {
        super(cnpj, ie, im);
        this.socios = socios;
        this.capital = capital;
    }

    public Empresa(String socios, BigDecimal capital, String cnpj, String ie, String im, String nome, String sobrenome, Date nascimento) {
        super(cnpj, ie, im, nome, sobrenome, nascimento);
        this.socios = socios;
        this.capital = capital;
    }

    public String getSocios() {
        return socios;
    }

    public void setSocios(String socios) {
        this.socios = socios;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "Empresa{" + "socios=" + socios + ", capital=" + capital + '}';
    }

}
