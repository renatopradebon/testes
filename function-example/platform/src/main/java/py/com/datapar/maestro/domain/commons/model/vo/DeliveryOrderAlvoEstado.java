/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.datapar.maestro.domain.commons.model.vo;

public enum DeliveryOrderAlvoEstado implements BaseEnum {

    NONE(0, "Nenhum"),
    INICIADA(1, "Iniciada"),
    EM_EXECUCAO(2, "Em execução"),
    CONCLUIDA(3, "Concluída"),
    CANCELADA(4, "Cancelada");

    private final String descricao;
    private final int id;

    DeliveryOrderAlvoEstado(int id, String descricao) {
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
