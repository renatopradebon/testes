import { Empresa } from '../empresa';
import { DeliveryOrder } from '../delivery-order';
import { EstagioExecucaoEntrega } from '../estagio-execucao-entrega';
export class EntregaAlvo {
    constructor(
        public id?: string,
        public requerAutorizacao?: boolean,
        public autorizado?: boolean,
        public autorizadoPor?: string,
        public dataAgendamento?: any,
        public empresa?: Empresa,
        public deliveryOrder?: DeliveryOrder,
        public estagio?: EstagioExecucaoEntrega,
    ) {
        this.requerAutorizacao = false;
        this.autorizado = false;
    }
}
