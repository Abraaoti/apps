
package br.com.cmil.controle.dominio.enuns;

/**
 *
 * @author cmilseg
 */
public enum Genero {
    FEMININO(1,"FEMININO"),MASCULINO(2,"MASCULINO"),OUTROS(3,"OUTROS");
    private final long cod;
	private final String desc;

    private Genero(long cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public long getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }
        
}
