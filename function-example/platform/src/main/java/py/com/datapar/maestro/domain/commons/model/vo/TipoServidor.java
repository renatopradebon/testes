package py.com.datapar.maestro.domain.commons.model.vo;

public enum TipoServidor implements BaseEnum {

    PLATFORM(0, "Maestro Platform"),
    MASTER(1, "Servidor Master"),
    SLAVE(2, "Servidor Slave"),
    UNKNOW(3, "NOVO SERVIDOR Master/Slave - SEM CONFIG");

    private final String descricao;
    private final int id;

    TipoServidor(int id, String descricao) {
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
