package py.com.datapar.maestro.domain.commons.model.vo;

public enum Situation implements BaseEnum {

    INATIVO(0, "Inactivo"),
    ATIVO(1, "Activo"),
    BLOQUEADO(2, "Bloqueado");

    private final String descricao;
    private final int id;

    Situation(int id, String descricao) {
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
