import { ProdutoDbRevision } from '../produto-db-revision';
export class ConteudoDb {
    constructor(
        public id?: string,
        public revisao?: ProdutoDbRevision,
    ) {
    }
}
