import { Plano } from '../plano';
export class PlanoClassificador {
    constructor(
        public id?: string,
        public queryEmpresas?: string,
        public ordemExecucao?: number,
        public nomeClassificador?: string,
        public plano?: Plano,
    ) {
    }
}
