package py.com.datapar.maestro.domain.commons.model.vo;

public enum EstagioExecucaoEstado implements BaseEnum {

    NONE(0, "Nenhum"),
    INICIADA(1, "Iniciada"),
    EM_EXECUCAO(2, "Em execução"),
    CONCLUIDA(3, "Concluída"),
    CANCELADA(4, "Cancelada"),
    INTERROMPIDA(5, "Interrompida");

    private final String descricao;
    private final int id;

    EstagioExecucaoEstado(int id, String descricao) {
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
