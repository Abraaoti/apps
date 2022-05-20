package br.com.cmil.controle.dominio.enuns;

/**
 *
 * @author cmilseg
 */
public enum FormaPagamento {
    VISTA("A VISTA"),
    BOLETO("BOLETO"),
    CHEQUE("CHEQUE"),
    TRANSFERENCIA("TRANSFERÊNCIA"),
    CARTAO("CARTÃO");
    private final String forma;

    private FormaPagamento(String forma) {
        this.forma = forma;
    }

    public String getForma() {
        return forma;
    }

    @Override
    public String toString() {
        return "FormaPagamento{" + "forma=" + forma + '}';
    }

}
