package py.com.datapar.maestro.domain.commons.model.vo;

public enum TipoClassificador implements BaseEnum {

    QA(0, "Calidad"),
    TESTE(1, "Teste en el Cliente"),
    PRODUCAO(2, "Producción");

    private final int id;
    private final String descricao;

    TipoClassificador(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

}
