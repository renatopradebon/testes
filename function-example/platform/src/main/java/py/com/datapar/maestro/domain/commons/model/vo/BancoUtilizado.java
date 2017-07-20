package py.com.datapar.maestro.domain.commons.model.vo;

public enum BancoUtilizado implements BaseEnum {

    NONE(0, "Nenhum"),
    ORACLE(1, "Oracle Database"),
    MYSQL(2, "MySQL"),
    POSTGRESQL(3, "PostgreSQL"),
    SQLSERVER(4, "SQL Server"),
    MONGO(5, "MongoDB");

    private final String descricao;
    private final int id;

    BancoUtilizado(int id, String descricao) {
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
