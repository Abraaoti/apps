package br.com.cmil.controle.dominio.entidades;

import br.com.cmil.controle.dominio.enuns.EstadoCivil;
import br.com.cmil.controle.dominio.enuns.Genero;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
/**
 *
 * @author cmilseg
 */
@Entity
@Table(name = "tbl_pessoa_fisicas")
@PrimaryKeyJoinColumn(name="pessoa_fisica_id")
public class PessoaFisica extends Pessoa {

    @Column(name = "cpf", length = 15, unique = true)
    private String cpf;
    @Column(name = "rg", length = 11, unique = true)
    private String rg;
    @Column(name = "mae", nullable = false)
    private String mae;
    @Column(name = "pai", nullable = false)
    private String pai;
    @Column(name = "passaporte", length = 15, unique = true, nullable = false)
    private String passaporte;
    //@JoinColumn(name = "genero_id", referencedColumnName = "id")
    @Enumerated(EnumType.STRING)
    private Genero genero;
    // @JoinColumn(name = "estado_civil_id", referencedColumnName = "id")
    @Enumerated(EnumType.STRING)
    private EstadoCivil ec;
    @Column(length = 50, nullable = false)
    private String naturalidade;
   

    public PessoaFisica() {
    }

    public PessoaFisica(String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil ec, String naturalidade) {
        this.cpf = cpf;
        this.rg = rg;
        this.mae = mae;
        this.pai = pai;
        this.passaporte = passaporte;
        this.genero = genero;
        this.ec = ec;
        this.naturalidade = naturalidade;
    }

    public PessoaFisica(String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil ec, String naturalidade, String nome, String sobrenome, Date nascimento) {
        super(nome, sobrenome, nascimento);
        this.cpf = cpf;
        this.rg = rg;
        this.mae = mae;
        this.pai = pai;
        this.passaporte = passaporte;
        this.genero = genero;
        this.ec = ec;
        this.naturalidade = naturalidade;
    }

   
    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public EstadoCivil getEc() {
        return ec;
    }

    public void setEc(EstadoCivil ec) {
        this.ec = ec;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" + "cpf=" + cpf + ", rg=" + rg + ", mae=" + mae + ", pai=" + pai + ", passaporte=" + passaporte + ", genero=" + genero + ", ec=" + ec + ", naturalidade=" + naturalidade + '}';
    }

   

    
}
