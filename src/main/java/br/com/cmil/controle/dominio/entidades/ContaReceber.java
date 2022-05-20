package br.com.cmil.controle.dominio.entidades;

import br.com.cmil.controle.dominio.base.Conta;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author cmilseg
 */
@Entity
@Table(name = "tbl_contareceber")
public class ContaReceber extends Conta {

    @OneToOne
    private Cliente cliente;

    public ContaReceber() {
    }

   

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ContaReceber(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "ContaReceber{" + "cliente=" + cliente + '}';
    }
    

}
