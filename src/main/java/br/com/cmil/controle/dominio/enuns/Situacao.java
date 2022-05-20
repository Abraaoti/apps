package br.com.cmil.controle.dominio.enuns;

/**
 *
 * @author cmilseg
 */
public enum Situacao {
    ABERTO(1, "ABERTO"),
    PAGO(2, "PAGO"),
    CANCELADA(3, "CANCELADA"),
    VENCIDA(4, "VENCIDA");
    private final long cod;
    private final String descr;

    private Situacao(long cod, String descr) {
        this.cod = cod;
        this.descr = descr;
    }

    public long getCod() {
        return cod;
    }

    public String getDescr() {
        return descr;
    }

}
