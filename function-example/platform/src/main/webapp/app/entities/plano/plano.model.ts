import { Produto } from '../produto';
import { PlanoClassificador } from '../plano-classificador';
export class Plano {
    constructor(
        public id?: string,
        public nome?: string,
        public produto?: Produto,
        public planoClassificadores?: PlanoClassificador,
    ) {
    }
}
