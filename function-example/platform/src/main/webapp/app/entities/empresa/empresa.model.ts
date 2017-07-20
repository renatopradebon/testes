import { Cliente } from '../cliente';
import { Filial } from '../filial';
import { DeliveryOrder } from '../delivery-order';
export class Empresa {
    constructor(
        public id?: string,
        public nome?: string,
        public codigo?: number,
        public cliente?: Cliente,
        public filiais?: Filial,
        public deliveryOrders?: DeliveryOrder,
    ) {
    }
}
