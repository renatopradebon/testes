import { Empresa } from '../empresa';
import { Servidor } from '../servidor';
import { Produto } from '../produto';
export class Cliente {
    constructor(
        public id?: string,
        public codigo?: number,
        public nome?: string,
        public empresas?: Empresa,
        public servidores?: Servidor,
        public produtos?: Produto,
    ) {
    }
}
