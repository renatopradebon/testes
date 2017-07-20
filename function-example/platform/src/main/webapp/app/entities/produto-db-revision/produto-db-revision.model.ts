import { Produto } from '../produto';
export class ProdutoDbRevision {
    constructor(
        public id?: string,
        public revisaoBanco?: string,
        public ordemExecucao?: number,
        public produto?: Produto,
    ) {
    }
}
