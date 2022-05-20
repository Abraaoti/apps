
package br.com.cmil.controle.dominio.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author cmilseg
 */
@Entity
@Table(name = "tbl_fornecedores")
@PrimaryKeyJoinColumn(name="fornecedorId")
public class Fornecedor extends PessoaJuridica {

    public Fornecedor() {
    }

    public Fornecedor(String cnpj, String ie, String im) {
        super(cnpj, ie, im);
    }

    public Fornecedor(String cnpj, String ie, String im, String nome, String sobrenome, Date nascimento) {
        super(cnpj, ie, im, nome, sobrenome, nascimento);
    }
    
    
}
