package br.com.cmil.controle.dominio.enuns;

/**
 *
 * @author cmilseg
 */
public enum Banco {
    ITAU(1),
    BB(2),
    CE(3),
    BANESTES(4),
    NUBANK(5),
    INTER(6),
    SANTANDER(7);
    private final int banco;

    private Banco(int banco) {
        this.banco = banco;
    }

    public int getBanco() {
        return banco;
    }

    @Override
    public String toString() {
        return "Banco{" + "banco=" + banco + '}';
    }

}
