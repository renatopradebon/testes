import { Empresa } from '../empresa';
import { DeliveryOrderAlvoServico } from '../delivery-order-alvo-servico';
export class DeliveryOrder {
    constructor(
        public id?: string,
        public packageKind?: string,
        public packageData?: string,
        public empresa?: Empresa,
        public alvos?: DeliveryOrderAlvoServico,
    ) {
    }
}
