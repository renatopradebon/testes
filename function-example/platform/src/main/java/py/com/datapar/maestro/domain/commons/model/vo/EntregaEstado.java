package py.com.datapar.maestro.domain.commons.model.vo;

public enum EntregaEstado implements BaseEnum {

    NONE(0, "Nenhum"),
    INICIADA(1, "Iniciada"),
    EM_EXECUCAO(2, "En ejecución"),
    CONCLUIDA(3, "Concluída"),
    CANCELADA(4, "Cancelada"),
    INTERROMPIDA(5, "Interrompida");

    private final String descricao;
    private final int id;

    EntregaEstado(int id, String descricao) {
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
