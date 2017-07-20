package py.com.datapar.maestro.domain.entrega.dto;

import py.com.datapar.maestro.domain.commons.model.vo.DeliveryOrderAlvoEstado;
import py.com.datapar.maestro.domain.entrega.model.DeliveryOrderAlvoServico;

import java.io.Serializable;

public class DeliveryOrderAlvoDTO implements Serializable {

    private static final long serialVersionUID = 8093907756755563299L;

    public DeliveryOrderAlvoDTO(DeliveryOrderAlvoServico alvoServico) {
        this.alvoServico = alvoServico;
    }

    private DeliveryOrderAlvoServico alvoServico;

    public DeliveryOrderAlvoServico getAlvoServico() {
        return alvoServico;
    }

    public void setAlvoServico(DeliveryOrderAlvoServico alvoServico) {
        this.alvoServico = alvoServico;
    }

    public String getFilialNome() {
        return getAlvoServico().getConfigSchema().getFilial().getNome();
    }

    public String getSchemaNome() {
        return getAlvoServico().getConfigSchema().getSchemaName();
    }

    public String getEstadoDescricao() {
        return getAlvoServico().getEstado().getDescricao();
    }

    public DeliveryOrderAlvoEstado getEstado() {
        return getAlvoServico().getEstado();
    }

}
