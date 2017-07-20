import { Produto } from '../produto';
import { Artefato } from '../artefato';
export class ProdutoAppVersao {
    constructor(
        public id?: string,
        public versaoProduto?: string,
        public revisaoMinimaDB?: string,
        public ordemExecucao?: number,
        public produto?: Produto,
        public artefatos?: Artefato,
    ) {
    }
}
