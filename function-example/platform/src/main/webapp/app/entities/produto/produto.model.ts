import { ProdutoAppVersao } from '../produto-app-versao';
import { ProdutoDbRevision } from '../produto-db-revision';
import { ConfigSchema } from '../config-schema';
import { Plano } from '../plano';
import { Cliente } from '../cliente';
export class Produto {
    constructor(
        public id?: string,
        public nome?: string,
        public pathImagem?: string,
        public versoesApp?: ProdutoAppVersao,
        public revisoesDB?: ProdutoDbRevision,
        public configSchemas?: ConfigSchema,
        public planos?: Plano,
        public clientes?: Cliente,
    ) {
    }
}
