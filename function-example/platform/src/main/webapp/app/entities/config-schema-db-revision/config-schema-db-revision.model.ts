import { ProdutoDbRevision } from '../produto-db-revision';
import { ConfigSchema } from '../config-schema';
export class ConfigSchemaDbRevision {
    constructor(
        public id?: string,
        public dataUpdated?: any,
        public produtoDbRevision?: ProdutoDbRevision,
        public configSchema?: ConfigSchema,
    ) {
    }
}
