package br.com.cmil.controle.dominio.enuns;

/**
 *
 * @author cmilseg
 */
public enum EstadoCivil {
    SOLTEIRO(1,"SOLTEIRO(A)"),CASADO(2,"CASADO(A)"),DIVORCIADO(3,"DIVORCIADO(A)"),VIUVO(4,"VIUVO(A)");
    private final long cod;
    private final String desc;

    private EstadoCivil(long cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public static EstadoCivil getSOLTEIRO() {
        return SOLTEIRO;
    }

    public static EstadoCivil getCASADO() {
        return CASADO;
    }

    public static EstadoCivil getDIVORCIADO() {
        return DIVORCIADO;
    }

    public static EstadoCivil getVIUVO() {
        return VIUVO;
    }

    public long getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }

   
    
}
