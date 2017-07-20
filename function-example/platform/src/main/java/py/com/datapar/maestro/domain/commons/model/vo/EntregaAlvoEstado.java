package py.com.datapar.maestro.domain.commons.model.vo;

public enum EntregaAlvoEstado implements BaseEnum {

    NONE(0, "Ningún"),
    INICIADA(1, "Iniciado"),
    EM_EXECUCAO(2, "En ejecución"),
    CONCLUIDA(3, "Concluído"),
    CANCELADA(4, "Cancelado");

    private final String descricao;
    private final int id;

    EntregaAlvoEstado(int id, String descricao) {
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
