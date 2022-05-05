package br.com.cmil.controle.dominio.enuns;

public enum PerfilTipo {
    ADMIN(1, "ADMIN")
    , ADMINISTRATIVO(2, "ADMINISTRATIVO")
    , DIRETORIA(3, "DIRETORIA")
    , ENGENHEIRO(4, "ENGENHEIRO")
    , FINANCEIRO(5, "FINANCEIRO")
    , FUNCIONARIO(6, "FUNCIONARIO")
    , RH(7, "RH")
    , TECNICO(8, "TECNICO")
    ;

    private final long cod;
    private final String desc;

    private PerfilTipo(long cod, String desc) {
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
