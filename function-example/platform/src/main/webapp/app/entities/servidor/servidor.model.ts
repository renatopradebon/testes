import { Cliente } from '../cliente';
import { Servico } from '../servico';
export class Servidor {
    constructor(
        public id?: string,
        public hostIP?: string,
        public contato?: string,
        public observacao?: string,
        public cliente?: Cliente,
        public servicos?: Servico,
    ) {
    }
}
