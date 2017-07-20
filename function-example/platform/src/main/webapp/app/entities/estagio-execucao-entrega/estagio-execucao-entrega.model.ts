import { PlanoClassificador } from '../plano-classificador';
import { Entrega } from '../entrega';
import { EntregaAlvo } from '../entrega-alvo';
export class EstagioExecucaoEntrega {
    constructor(
        public id?: string,
        public planoClassificador?: PlanoClassificador,
        public entrega?: Entrega,
        public alvos?: EntregaAlvo,
    ) {
    }
}
