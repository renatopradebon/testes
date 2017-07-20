import { Empresa } from '../empresa';
import { ConfigSchema } from '../config-schema';
export class Filial {
    constructor(
        public id?: string,
        public codigo?: number,
        public nome?: string,
        public empresa?: Empresa,
        public configSchemas?: ConfigSchema,
    ) {
    }
}
