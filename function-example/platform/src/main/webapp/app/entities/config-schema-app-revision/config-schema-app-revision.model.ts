import { ProdutoAppVersao } from '../produto-app-versao';
import { ConfigSchema } from '../config-schema';
export class ConfigSchemaAppRevision {
    constructor(
        public id?: string,
        public dataUpdated?: any,
        public produtoAppVersao?: ProdutoAppVersao,
        public configSchema?: ConfigSchema,
    ) {
    }
}
