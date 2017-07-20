package py.com.datapar.maestro.domain.commons.model.vo;

public enum ProtudoTipo implements BaseEnum {

    NONE(0, "Nenhum"),
    DESKTOP(1, "Aplicação Desktop"),
    DESKTOP_DB(2, "Aplicação Desktop + DB"),
    WEB(3, "Aplicação WEB"),
    WEB_DB(4, "Aplicação WEB + DB"),
    DB_ONLY(5, "Aplicação WEB");

    private final String descricao;
    private final int id;

    ProtudoTipo(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public int getId() {
        return id;
    }

}
