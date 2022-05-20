package br.com.cmil.controle.dominio.entidades;

import br.com.cmil.controle.dominio.base.Entidade;
import br.com.cmil.controle.dominio.enuns.PerfilTipo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author cmilseg
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_usuarios", indexes = {
    @Index(name = "idx_usuario_email", columnList = "email")})
public class Usuario extends Entidade {

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "senha", nullable = false)
    private String password;  
    @Column(name = "data_cadastro")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dataCadastro;
    @Column(name = "ativo", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean ativo;
    @Column(name = "verificador", length = 6)
    private String verificador;
    @ManyToMany
    @JoinTable(name = "tbl_usuario_perfis",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id"))
    private List<Perfil> perfis;

    public Usuario() {
    }

    public Usuario(Long id) {
        super.setId(id);
    }

    public Usuario(String email, String senha, LocalDateTime dataCadastro, boolean ativo, String verificador) {
        this.email = email;
        this.password = senha;
       
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
        this.verificador = verificador;
    }

    public Usuario(String email, String senha,  LocalDateTime dataCadastro, boolean ativo, String verificador, List<Perfil> perfis) {
        this.email = email;
        this.password = senha;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
        this.verificador = verificador;
        this.perfis = perfis;
    }

    public Usuario(String email) {
        this.email = email;
    }

    public void addPerfil(PerfilTipo tipo) {
        if (tipo != null ) {
           this.perfis = new ArrayList<>();           
        }
        this.perfis.add(new Perfil(tipo.getCod()));

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getVerificador() {
        return verificador;
    }

    public void setVerificador(String verificador) {
        this.verificador = verificador;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", senha=" + password + ", dataCadastro=" + dataCadastro + ", ativo=" + ativo + ", verificador=" + verificador + ", perfis=" + perfis + '}';
    }

    

   

}
