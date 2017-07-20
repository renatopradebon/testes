import { ConfigSchema } from '../config-schema';
import { DeliveryOrder } from '../delivery-order';
export class DeliveryOrderAlvoServico {
    constructor(
        public id?: string,
        public configSchema?: ConfigSchema,
        public deliveryOrder?: DeliveryOrder,
    ) {
    }
}
