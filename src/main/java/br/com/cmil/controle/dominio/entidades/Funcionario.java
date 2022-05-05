package br.com.cmil.controle.dominio.entidades;

import br.com.cmil.controle.dominio.enuns.EstadoCivil;
import br.com.cmil.controle.dominio.enuns.Genero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author ti
 */
@Entity
@Table(name = "tbl_funcionarios")
@PrimaryKeyJoinColumn(name="funcionario_id")
public class Funcionario extends PessoaFisica {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admissao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate demissao;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    @Column(nullable = false)
    private BigDecimal salario;
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "funcionario_empresa_id", referencedColumnName = "empresa_id")
    private Empresa empresa;
    
   // @Column(nullable = false)
   
   // private String foto;
    
    //@ManyToOne
   // private Set<Departamento> departamento;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cargo_id", referencedColumnName = "idCargo")
    private Cargo cargo;
   // @OneToOne(cascade = CascadeType.REMOVE)
   // @JoinColumn(name = "id_usuario")
   // private Usuario usuario;

    public Funcionario() {
    }

    public Funcionario(LocalDate admissao, LocalDate demissao, BigDecimal salario, Empresa empresa, Cargo cargo) {
        this.admissao = admissao;
        this.demissao = demissao;
        this.salario = salario;
        this.empresa = empresa;
        this.cargo = cargo;
    }

    public Funcionario(LocalDate admissao, LocalDate demissao, BigDecimal salario, Empresa empresa, Cargo cargo, String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil ec, String naturalidade) {
        super(cpf, rg, mae, pai, passaporte, genero, ec, naturalidade);
        this.admissao = admissao;
        this.demissao = demissao;
        this.salario = salario;
        this.empresa = empresa;
        this.cargo = cargo;
    }

    public Funcionario(LocalDate admissao, LocalDate demissao, BigDecimal salario, Empresa empresa, Cargo cargo, String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil ec, String naturalidade, String nome, String sobrenome, Date nascimento) {
        super(cpf, rg, mae, pai, passaporte, genero, ec, naturalidade, nome, sobrenome, nascimento);
        this.admissao = admissao;
        this.demissao = demissao;
        this.salario = salario;
        this.empresa = empresa;
        this.cargo = cargo;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public LocalDate getDemissao() {
        return demissao;
    }

    public void setDemissao(LocalDate demissao) {
        this.demissao = demissao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "admissao=" + admissao + ", demissao=" + demissao + ", salario=" + salario + ", empresa=" + empresa + ", cargo=" + cargo + '}';
    }

    
  

}
